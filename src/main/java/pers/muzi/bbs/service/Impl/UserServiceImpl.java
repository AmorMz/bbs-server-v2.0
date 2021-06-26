package pers.muzi.bbs.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.common.utils.CommonUtils;
import pers.muzi.bbs.common.utils.JwtUtil;
import pers.muzi.bbs.dao.UserDAO;
import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.dto.LoginDTO;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.exception.ParamException;
import pers.muzi.bbs.exception.UserException;
import pers.muzi.bbs.service.UserService;

import java.util.Date;
import java.util.Objects;

/**
 * @author AmorMz
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;

    /**
     * 用户注册
     *
     * @param registerDTO 注册DTO
     */
    @Override
    public void register(RegisterDTO registerDTO) {
        // 验证账号、昵称是否存在
        User userByAccount = userDAO.getUserByAccount(registerDTO.getAccount());
        User userByNickname = userDAO.getUserByNickname(registerDTO.getNickname());

        if (userByAccount != null) {
            throw new ParamException("账号已存在");
        }
        if (userByNickname != null) {
            throw new ParamException("昵称已存在");
        }

        User user = new User();
        // 获取密码盐
        String salt = CommonUtils.getSalt();
        String password = registerDTO.getPassword() + salt;
        // 将密码加盐加密处理
        String md5Password = CommonUtils.MD5(password);
        // 写入数据库
        user
                .setAccount(registerDTO.getAccount())
                .setNickname(registerDTO.getNickname())
                .setPassword(md5Password)
                .setSalt(salt)
                // TODO 阿里云设置默认头像
                .setAvatar("http://q1.qlogo.cn/g?b=qq&nk=1309815285&s=640")
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        userDAO.insertUser(user);

    }

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userDAO.getUserByAccount(loginDTO.getAccount());
        if (user == null) {
            throw new UserException("用户不存在！请检查您的用户名");
        }
        // 获取密码盐 进行校验
        String password = loginDTO.getPassword() + user.getSalt();
        String md5Password = CommonUtils.MD5(password);
        if (Objects.equals(user.getPassword(), md5Password)) {
            throw new UserException("密码错误，请检查您的密码");
        }

        // 通过检验 签发token
        return JwtUtil
                .getToken(user.getId(), user.getRole(), loginDTO.getRememberMe());

    }
}

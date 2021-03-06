package pers.muzi.bbs.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.common.utils.CommonUtils;
import pers.muzi.bbs.common.utils.JwtUtils;
import pers.muzi.bbs.dao.UserDAO;
import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.dto.LoginDTO;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.entity.vo.user.UserInfoVO;
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

    @Autowired
    private RedisTemplate redisTemplate;

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

    /**
     * 用户登录业务
     *
     * @param loginDTO 登录DTO
     * @return token
     */
    @Override
    public String login(LoginDTO loginDTO) {
        User user = userDAO.getUserByAccount(loginDTO.getAccount());
        if (user == null) {
            throw new UserException("用户不存在！请检查您的账号");
        }
        if (user.getStatus()) {
            throw new UserException("您的账号被禁用，暂时无法登录");
        }
        // 获取密码盐 进行校验
        String password = loginDTO.getPassword() + user.getSalt();
        String md5Password = CommonUtils.MD5(password);
        if (!Objects.equals(user.getPassword(), md5Password)) {
            throw new UserException("密码错误，请检查您的密码");
        }

        // 通过检验 签发token
        return JwtUtils
                .getToken(user.getId(), user.getRole(), loginDTO.getRememberMe());

    }


    /**
     * 判断当前登录用户是否关注userid用户
     *
     * @param loginId 当前登录用户id
     * @param userId  被关注用户id
     * @return boolean 是否关注
     */
    @Override
    public Boolean validateFollow(Integer loginId, Integer userId) {
        Integer id = userDAO.validateFollow(loginId, userId);
        return id != null;
    }


    /**
     * 用户关注
     *
     * @param loginId 当前登录用户id
     * @param userId  被关注用户id
     */
    @Override
    public void follow(Integer loginId, Integer userId) {
        userDAO.insertFollow(loginId, userId);
    }


    /**
     * 取消关注
     *
     * @param loginId 当前登陆用户
     * @param userId  被取关用户
     */
    @Override
    public void unfollow(Integer loginId, Integer userId) {
        userDAO.deleteFollow(loginId, userId);
    }

    /**
     * 根据id获取用户信息
     * @param id 用户id
     * @return User
     */
    @Override
    public UserInfoVO getUserInfoById(Integer id) {
        String redisKey = "userInfo:" + id;
        // 判断redis中是否存在缓存
        if (redisTemplate.hasKey(redisKey)) {
            return (UserInfoVO) redisTemplate.opsForValue().get(redisKey);
        }

        // 不存在 查询数据库 加入缓存
        User user = userDAO.getUserById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        redisTemplate.opsForValue().set(redisKey, userInfoVO);

        return userInfoVO;

    }

    /**
     * 根据账号查询用户信息
     *
     * @param account 用户账号
     * @return 用户实体
     */
    @Override
    public User getUserByAccount(String account) {
        return userDAO.getUserByAccount(account);
    }

    /**
     * 注销登录
     */
    @Override
    public void logout(Integer id) {
        // 删除redis中的userinfo
        String redisKey = "userInfo:" + id;
        redisTemplate.delete(redisKey);
    }
}

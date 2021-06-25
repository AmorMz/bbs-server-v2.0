package pers.muzi.bbs.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.service.UserService;

/**
 * @author AmorMz
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户注册
     * @param registerDTO 注册DTO
     */
    @Override
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        // 设置默认值 写入数据库 TODO


    }
}

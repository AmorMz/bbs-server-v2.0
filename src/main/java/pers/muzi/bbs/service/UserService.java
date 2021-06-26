package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.dto.LoginDTO;
import pers.muzi.bbs.entity.dto.RegisterDTO;

/**
 * @author AmorMz
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param registerDTO 注册DTO
     */
    void register(RegisterDTO registerDTO);

    /**
     * 用户登录
     *
     * @param loginDTO 登录DTO
     * @return 用户实体
     */
    String login(LoginDTO loginDTO);
}

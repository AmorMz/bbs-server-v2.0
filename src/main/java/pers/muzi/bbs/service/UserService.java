package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.dto.RegisterDTO;

/**
 * @author AmorMz
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerDTO 注册DTO
     */
    void register(RegisterDTO registerDTO);
}

package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.dto.LoginDTO;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.entity.vo.user.AuthorVO;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 判断当前登录用户是否关注userid用户
     * @param loginId 当前登录用户id
     * @param userId 被关注用户id
     * @return boolean 是否关注
     */
    Boolean validateFollow(Integer loginId, Integer userId);

    /**
     * 用户关注
     * @param loginId 当前登录用户id
     * @param userId 被关注用户id
     */
    void follow(Integer loginId, Integer userId);

    /**
     * 取消关注
     * @param loginId 当前登陆用户
     * @param userId 被取关用户
     */
    void unfollow(Integer loginId, Integer userId);

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return User
     */
    User getUserById(Integer id);

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    User getUserByAccount(String account);
}

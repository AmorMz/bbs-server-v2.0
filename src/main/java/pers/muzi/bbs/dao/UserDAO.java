package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.muzi.bbs.entity.User;

/**
 * @author AmorMz
 * 用户DAO层
 */
@Mapper
public interface UserDAO {


    /**
     * 根据账号查询用户信息
     * 封禁的用户也需要查询
     *
     * @param account 账号
     * @return 用户实体
     */
    User getUserByAccount(@Param("account") String account);


    /**
     * 根据昵称查询用户信息 封禁的用户也需要查询
     *
     * @param nickname 昵称
     * @return 用户实体
     */
    User getUserByNickname(@Param("nickname") String nickname);


    /**
     * 新增一个用户
     *
     * @param user 用户实体
     */
    void insertUser(User user);

    /**
     * 判断当前登录用户是否关注userid用户
     * @param loginId 当前登录用户id
     * @param userId 被关注用户id
     * @return 记录的id 返回空则未关注
     */
    Integer validateFollow(@Param("loginId") Integer loginId, @Param("userId") Integer userId);

    /**
     * 关注
     * @param loginId 当前登录用户id
     * @param userId 被关注用户id
     */
    void insertFollow(@Param("loginId") Integer loginId, @Param("userId") Integer userId);

    /**
     * 取消关注
     * @param loginId 当前登录用户id
     * @param userId 被取关用户id
     */
    void deleteFollow(@Param("loginId") Integer loginId, @Param("userId") Integer userId);

    /**
     * 根据用户id查询用户 封禁的也需要查询
     * @param id 用户id
     * @return User
     */
    User getUserById(Integer id);
}

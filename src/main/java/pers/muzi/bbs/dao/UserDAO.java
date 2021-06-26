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
     * 根据昵称查询用户信息
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
}

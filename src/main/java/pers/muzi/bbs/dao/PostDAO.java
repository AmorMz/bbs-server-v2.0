package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author AmorMz
 * 帖子DAO层
 */
@Mapper
public interface PostDAO {

    /**
     * 统计所有帖子数量
     */
    Integer countPost();
}

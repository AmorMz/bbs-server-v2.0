package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.muzi.bbs.entity.vo.post.PostListVO;

import java.util.List;

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

    /**
     * 分页查询帖子 根据tab排序
     * @param tab latest 最新 hot 最热
     * @return post集合
     */
    List<PostListVO> listPosts(@Param("tab") String tab);

    /**
     * 根据帖子id获取该帖子的标签
     * @param postId 帖子id
     * @return 标签list
     */
    List<String> listPostTagsByPostId(@Param("postId") Integer postId);

}

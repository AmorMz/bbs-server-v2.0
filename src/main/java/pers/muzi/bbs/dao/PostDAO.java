package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.muzi.bbs.entity.Post;
import pers.muzi.bbs.entity.Tag;
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
     * @return 总数量
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

    /**
     * 插入一条帖子
     * @param post 帖子实体
     */
    void insertPost(Post post);

    /**
     * 根据标签名获取标签id
     * @param tag 标签名
     * @return 标签id 空为null
     */
    Integer getTagIdByTagName(@Param("tag") String tag);

    /**
     * 维护帖子 标签之间的联系
     * @param postId 帖子id
     * @param tagId 标签id
     */
    void insertPostTag(@Param("postId") Integer postId, @Param("tagId") Integer tagId);

    /**
     * 发布新帖子后 维护标签下话题数量
     * @param tagId 标签id
     */
    void updateTagPostCount(@Param("tagId") Integer tagId);

    /**
     * 新建标签 默认话题数量0
     * @param tag 标签名
     */
    void insertTag(Tag tag);
}

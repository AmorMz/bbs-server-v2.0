package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.muzi.bbs.entity.Post;
import pers.muzi.bbs.entity.Tag;
import pers.muzi.bbs.entity.vo.post.PostDetailVO;
import pers.muzi.bbs.entity.vo.post.PostListVO;
import pers.muzi.bbs.entity.vo.post.PostPersonalVO;

import java.util.List;

/**
 * @author AmorMz
 * 帖子DAO层
 */
@Mapper
public interface PostDAO {

    /**
     * 统计所有帖子数量
     *
     * @return 总数量
     */
    Integer countPost();

    /**
     * 统计账号下帖子数量
     *
     * @return 总数量
     */
    Integer countPostByAccount(@Param("account") String account);

    /**
     * 分页查询帖子 根据tab排序
     *
     * @param tab latest 最新 hot 最热
     * @return post集合
     */
    List<PostListVO> listPosts(@Param("tab") String tab);

    /**
     * 根据帖子id获取该帖子的标签
     *
     * @param postId 帖子id
     * @return 标签list
     */
    List<String> listPostsTagsByPostId(@Param("postId") Integer postId);

    /**
     * 插入一条帖子
     *
     * @param post 帖子实体
     */
    void insertPost(Post post);

    /**
     * 根据标签名获取标签id
     *
     * @param tag 标签名
     * @return 标签id 空为null
     */
    Integer getTagIdByTagName(@Param("tag") String tag);

    /**
     * 维护帖子 标签之间的联系
     *
     * @param postId 帖子id
     * @param tagId  标签id
     */
    void insertPostTag(@Param("postId") Integer postId, @Param("tagId") Integer tagId);

    /**
     * 发布新帖子后 维护标签下话题数量
     *
     * @param tagId 标签id
     */
    void updateTagPostCount(@Param("tagId") Integer tagId);

    /**
     * 新建标签 默认话题数量0
     *
     * @param tag 标签名
     */
    void insertTag(Tag tag);

    /**
     * 根据帖子id 查询
     *
     * @param postId 帖子id
     * @return PostDetailVO 帖子详情
     */
    PostDetailVO getPostDetail(@Param("postId") Integer postId);

    /**
     * 根据用户账号查询帖子
     *
     * @param account 用户账号
     * @return List<PostPersonalVO>
     */
    List<PostPersonalVO> listPostsByAccount(@Param("account") String account);

    /**
     * 置顶帖子
     * @param postId 帖子id
     */
    void setTopById(@Param("postId") Integer postId);

    /**
     * 加精帖子
     * @param postId 帖子id
     */
    void setEssenceById(@Param("postId") Integer postId);

    /**
     * 删除帖子
     * @param postId 帖子id
     */
    void deletePostById(@Param("postId") Integer postId);

    /**
     * 取消加精
     * @param postId 帖子id
     */
    void cancelTopById(@Param("postId") Integer postId);

    /**
     * 取消置顶
     * @param postId 帖子id
     */
    void cancelEssenceById(@Param("postId") Integer postId);
}

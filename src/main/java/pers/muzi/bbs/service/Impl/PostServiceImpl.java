package pers.muzi.bbs.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.muzi.bbs.dao.PostDAO;
import pers.muzi.bbs.entity.Post;
import pers.muzi.bbs.entity.Tag;
import pers.muzi.bbs.entity.dto.PostDTO;
import pers.muzi.bbs.entity.vo.post.PostDetailVO;
import pers.muzi.bbs.entity.vo.post.PostListVO;
import pers.muzi.bbs.entity.vo.post.PostPersonalVO;
import pers.muzi.bbs.exception.ParamException;
import pers.muzi.bbs.service.PostService;

import java.util.Date;
import java.util.List;

/**
 * @author AmorMz
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    /**
     * 获取帖子总数 用于统计、分页
     *
     * @return 帖子总数量
     */
    @Override
    public Integer countPost() {
        return postDAO.countPost();
    }

    /**
     * 分页查询所有帖子
     *
     * @param tab   排序规则 latest/hot 最新/最热
     * @param page  当前页
     * @param limit 每页限制条数
     * @return post集合
     */
    @Override
    public List<PostListVO> listPosts(String tab, Integer page, Integer limit) {
        // 分页
        PageHelper.startPage(page, limit);
        List<PostListVO> posts = postDAO.listPosts(tab);
        System.out.println("帖子集合: " + posts);
        if (posts == null) {
            return null;
        }
        // 为帖子添加标签
        for (PostListVO post : posts) {
            List<String> tags = postDAO.listPostsTagsByPostId(post.getId());
            post.setTags(tags);
        }
        return posts;
    }

    /**
     * 发表一篇帖子
     *
     * @param postDTO post数据实体
     * @return 返回新建帖子的id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer publishPost(PostDTO postDTO, Integer authorId) {
        // 将帖子写入数据库
        Post post = new Post();
        post
                .setTitle(postDTO.getTitle())
                .setContent(postDTO.getContent())
                // 默认不置顶 不加精
                .setTop(false)
                .setEssence(false)
                .setUserId(authorId)
                // 默认评论 收藏 点赞 浏览为0
                .setCommentCount(0)
                .setCollectCount(0)
                .setLikeCount(0)
                .setViewCount(0)
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        // 发布帖子的id
        postDAO.insertPost(post);
        Integer postId = post.getId();

        // 维护帖子标签
        List<String> tags = postDTO.getTags();
        if (tags != null) {
            for (String tag : tags) {
                // 判断是否存在该标签 返回标签id
                Integer tagId = postDAO.getTagIdByTagName(tag);
                if (tagId != null) {
                    // 存在该标签 维护标签与帖子关系
                    postDAO.insertPostTag(postId, tagId);
                } else {
                    // 该标签不存在 新建标签并 维护标签与帖子关系
                    Tag newTag = new Tag();
                    newTag
                            .setName(tag)
                            .setPostCount(0)
                            .setCreateTime(new Date());
                    postDAO.insertTag(newTag);
                    postDAO.insertPostTag(postId, newTag.getId());
                }

                // 更新标签下话题数量
                postDAO.updateTagPostCount(tagId);
            }
        }
        return postId;
    }

    /**
     * 展示帖子详情
     *
     * @param postId 帖子id
     * @return PostDetailVO
     */
    @Override
    public PostDetailVO getPostDetail(Integer postId) {
        PostDetailVO postDetail = postDAO.getPostDetail(postId);
        if (postDetail == null) {
            return null;
        }
        // 为帖子设置标签
        List<String> tags = postDAO.listPostsTagsByPostId(postDetail.getId());
        postDetail.setTags(tags);

        return postDetail;
    }

    /**
     * 个人中心查询帖子
     *
     * @param account 用户账号
     * @param page    当前页
     * @param limit   每页条数
     * @return PostPersonalVO
     */
    @Override
    public List<PostPersonalVO> listPersonalPosts(String account, Integer page, Integer limit) {
        // 分页
        PageHelper.startPage(page, limit);
        return postDAO.listPostsByAccount(account);
    }

}

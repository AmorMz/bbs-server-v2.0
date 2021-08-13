package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.dto.PostDTO;
import pers.muzi.bbs.entity.vo.post.PostDetailVO;
import pers.muzi.bbs.entity.vo.post.PostListVO;
import pers.muzi.bbs.entity.vo.post.PostPersonalVO;

import java.util.List;

/**
 * @author AmorMz
 */
public interface PostService {
    /**
     * 获取帖子总数 用于统计、分页
     * @return 帖子总数量
     */
    Integer countPost();

    /**
     * 获取账号下帖子总数
     * @param account 用户账号
     * @return 帖子总数
     */
    Integer countPostByAccount(String account);

    /**
     * 分页查询所有帖子
     * @param tab   排序规则 latest/hot 最新/最热
     * @param page  当前页
     * @param limit 每页限制条数
     * @return post集合
     */
    List<PostListVO> listPosts(String tab, Integer page, Integer limit);

    /**
     * 发表一篇帖子
     * @param postDTO post数据实体
     * @return 影响行数
     */
    Integer publishPost(PostDTO postDTO, Integer authorId);

    /**
     * 根据帖子id展示帖子详细信息
     * @param postId 帖子id
     * @return 帖子详情实体
     */
    PostDetailVO getPostDetail(Integer postId);

    /**
     * 个人中心展示帖子 根据用户账号查询
     * @param account 用户账号
     * @return List<PostPersonalVO>
     */
    List<PostPersonalVO> listPersonalPosts(String account, Integer page, Integer limit);

    /**
     * 初始化本周热议
     */
    void initWeekRank();

    /**
     * 统计浏览数量
     */
    void viewCount();
}

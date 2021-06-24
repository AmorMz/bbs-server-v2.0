package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.vo.post.PostListVO;

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
     * 分页查询所有帖子
     * @param tab   排序规则 latest/hot 最新/最热
     * @param page  当前页
     * @param limit 每页限制条数
     * @return post集合
     */
    List<PostListVO> listPosts(String tab, Integer page, Integer limit);
}

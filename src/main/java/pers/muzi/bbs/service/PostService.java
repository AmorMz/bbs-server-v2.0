package pers.muzi.bbs.service;

/**
 * @author AmorMz
 */
public interface PostService {
    /**
     * 获取帖子总数 用于统计、分页
     * @return 帖子总数量
     */
    Integer countPost();
}

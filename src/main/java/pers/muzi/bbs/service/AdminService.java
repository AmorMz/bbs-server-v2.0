package pers.muzi.bbs.service;

/**
 * @author AmorMz
 * 管理员相关操作
 */
public interface AdminService {

    /**
     * 置顶帖子
     * @param postId 帖子id
     */
    void topPost(Integer postId);

    /**
     * 删除帖子
     * @param postId 帖子id
     */
    void deletePost(Integer postId);

    /**
     * 加精帖子
     * @param postId 帖子id
     */
    void essencePost(Integer postId);

    /**
     * 取消置顶
     * @param postId 帖子id
     */
    void cancelTop(Integer postId);

    /**
     * 取消加精
     * @param postId 帖子id
     */
    void cancelEssence(Integer postId);
}

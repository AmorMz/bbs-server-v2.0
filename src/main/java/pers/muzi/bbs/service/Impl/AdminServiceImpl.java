package pers.muzi.bbs.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.dao.PostDAO;
import pers.muzi.bbs.service.AdminService;

/**
 * @author AmorMz
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PostDAO postDAO;

    /**
     * 置顶帖子
     * @param postId 帖子id
     */
    @Override
    public void topPost(Integer postId) {
        postDAO.setTopById(postId);
    }

    /**
     * 删除帖子
     * @param postId 帖子id
     */
    @Override
    public void deletePost(Integer postId) {
        postDAO.deletePostById(postId);
    }

    /**
     * 加精帖子
     * @param postId 帖子id
     */
    @Override
    public void essencePost(Integer postId) {
        postDAO.setEssenceById(postId);
    }

    /**
     * 取消置顶
     * @param postId 帖子id
     */
    @Override
    public void cancelTop(Integer postId) {
        postDAO.cancelTopById(postId);
    }

    /**
     * 取消加精
     * @param postId 帖子id
     */
    @Override
    public void cancelEssence(Integer postId) {
        postDAO.cancelEssenceById(postId);
    }
}

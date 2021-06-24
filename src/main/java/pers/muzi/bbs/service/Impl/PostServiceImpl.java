package pers.muzi.bbs.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.dao.PostDAO;
import pers.muzi.bbs.service.PostService;

/**
 * @author AmorMz
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    /**
     * 获取帖子总数 用于统计、分页
     * @return 帖子总数量
     */
    @Override
    public Integer countPost() {
        return postDAO.countPost();
    }
}

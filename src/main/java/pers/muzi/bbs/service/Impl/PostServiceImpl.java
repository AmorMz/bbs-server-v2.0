package pers.muzi.bbs.service.Impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.dao.PostDAO;
import pers.muzi.bbs.entity.vo.post.PostListVO;
import pers.muzi.bbs.service.PostService;

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
     * @return 帖子总数量
     */
    @Override
    public Integer countPost() {
        return postDAO.countPost();
    }

    /**
     * 分页查询所有帖子
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

        // 为帖子添加标签
        for (PostListVO post : posts) {
            List<String> tags = postDAO.listPostTagsByPostId(post.getId());
            post.setTags(tags);
        }
        return postDAO.listPosts(tab);
    }
}

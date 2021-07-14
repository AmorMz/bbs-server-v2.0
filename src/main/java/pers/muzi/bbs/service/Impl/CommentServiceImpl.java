package pers.muzi.bbs.service.Impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.dao.CommentDAO;
import pers.muzi.bbs.entity.Comment;
import pers.muzi.bbs.entity.dto.CommentDTO;
import pers.muzi.bbs.entity.vo.comment.CommentVO;
import pers.muzi.bbs.entity.vo.comment.ReplyVO;
import pers.muzi.bbs.service.CommentService;

import java.util.Date;
import java.util.List;

/**
 * @author AmorMz
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    /**
     * 获取帖子下评论总数量 楼中楼回复不计入 分页
     *
     * @param postId 帖子id
     * @return 评论总数
     */
    @Override
    public Integer getCommentCountByPostId(Integer postId) {
        return commentDAO.countCommentByPostId(postId);
    }


    /**
     * 获取评论 分页
     *
     * @param postId 帖子id
     * @param page   当前页
     * @param limit  每页条数
     * @return 评论VO
     */
    @Override
    public List<CommentVO> getComments(Integer postId, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<CommentVO> comments = commentDAO.listCommentByPostId(postId);
        // 为一级回复添加楼中楼回复
        for (CommentVO comment : comments) {
            if (comment != null) {
                System.out.println(comment);
                List<ReplyVO> reply = commentDAO.listReplyByCommentId(comment.getId());
                comment.setReply(reply);
            }
        }
        return comments;
    }


    /**
     * 发表评论
     *
     * @param commentDTO 评论DTO
     */
    @Override
    public void publishComment(CommentDTO commentDTO, Integer userId) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment
                .setUserId(userId)
                .setCreateTime(new Date());
        commentDAO.insertComment(comment);
    }
}

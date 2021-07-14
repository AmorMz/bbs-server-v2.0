package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.dto.CommentDTO;
import pers.muzi.bbs.entity.vo.comment.CommentVO;

import java.util.List;

/**
 * @author AmorMz
 * 评论业务层
 */
public interface CommentService {

    /**
     * 获取帖子下评论总数量 楼中楼回复不计入 分页
     * @param postId 帖子id
     * @return 评论总数
     */
    Integer getCommentCountByPostId(Integer postId);


    /**
     * 获取评论 分页
     * @param postId 帖子id
     * @param page 当前页
     * @param limit 每页条数
     * @return 评论VO
     */
    List<CommentVO> getComments(Integer postId, Integer page, Integer limit);

    /**
     * 发表评论
     * @param commentDTO 评论DTO
     */
    void publishComment(CommentDTO commentDTO, Integer userId);
}

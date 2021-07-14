package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.muzi.bbs.entity.Comment;
import pers.muzi.bbs.entity.dto.CommentDTO;
import pers.muzi.bbs.entity.vo.comment.CommentVO;
import pers.muzi.bbs.entity.vo.comment.ReplyVO;

import java.util.List;

/**
 * @author AmorMz
 */
@Mapper
public interface CommentDAO {

    /**
     * 获取帖子下一级评论数量
     * @param postId 帖子id
     * @return 总数量
     */
    Integer countCommentByPostId(@Param("postId") Integer postId);

    /**
     * 获取帖子下一级评论
     * @param postId 帖子id
     * @return 评论
     */
    List<CommentVO> listCommentByPostId(@Param("postId") Integer postId);

    /**
     * 获取评论下楼中楼回复
     * @param commentId 评论id
     * @return 楼中楼
     */
    List<ReplyVO> listReplyByCommentId(@Param("commentId") Integer commentId);

    /**
     * 插入一条评论
     * @param comment 评论实体
     */
    void insertComment(Comment comment);
}

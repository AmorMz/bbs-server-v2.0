package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.entity.vo.comment.CommentVO;
import pers.muzi.bbs.service.CommentService;

import java.util.List;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取帖子下评论总数", notes = "用于分页")
    @GetMapping("/count/{postId}")
    public Resp count(@PathVariable("postId") Integer postId) {
        Integer commentCount = commentService.getCommentCountByPostId(postId);
        return Resp.ok().data("total", commentCount);
    }

    @ApiOperation("获取帖子下评论")
    @GetMapping("/{postId}")
    public Resp getComments(@PathVariable("postId") Integer postId,
                            @RequestParam("page") Integer page,
                            @RequestParam("limit") Integer limit) {

        List<CommentVO> comments = commentService.getComments(postId, page, limit);
        return Resp.ok().data("comments", comments);
    }


}

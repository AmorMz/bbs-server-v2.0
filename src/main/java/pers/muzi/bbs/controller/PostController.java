package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.entity.dto.PostDTO;
import pers.muzi.bbs.entity.vo.post.PostListVO;
import pers.muzi.bbs.service.PostService;
import java.util.List;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/post")
@Api(tags = "帖子接口")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 查询所有帖子的数量 用于分页、统计等
     * @return total 帖子总数量
     */
    @ApiOperation("获取帖子总数量")
    @GetMapping
    public Resp count() {
        Integer countPost = postService.countPost();
        return Resp.ok().data("total", countPost);
    }


    /**
     * 分页查询所有帖子
     * @param tab   排序规则 latest/hot 最新/最热
     * @param page  当前页
     * @param limit 每页限制条数
     * @return post集合
     */
    @ApiOperation("分页查询帖子")
    @GetMapping("/{tab}")
    public Resp listPost(@PathVariable String tab,
                         @RequestParam("page") Integer page,
                         @RequestParam("limit") Integer limit) {

        List<PostListVO> listPost = postService.listPosts(tab, page, limit);
        return Resp.ok().data("posts", listPost);
    }


    @ApiOperation("发表一篇帖子")
    @PostMapping
    public Resp publish(@RequestBody @Validated PostDTO postDTO) {
        // 获取当前登录用户id
        Integer authorId = 1;
        Integer postId = postService.publishPost(postDTO, authorId);
        return Resp.ok().message("发表成功!即将跳转至详情界面");
    }

}

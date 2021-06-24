package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.service.PostService;

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


}

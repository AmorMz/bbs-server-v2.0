package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.annotation.AdminRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.service.AdminService;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员接口")
@CrossOrigin
@AdminRequired
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/top/{postId}")
    @ApiOperation("置顶帖子")
    public Resp topPost(@PathVariable("postId") Integer postId) {
        adminService.topPost(postId);
        return Resp.ok();
    }

    @GetMapping("/untop/{postId}")
    @ApiOperation("取消置顶帖子")
    public Resp CancelTopPost(@PathVariable("postId") Integer postId) {
        adminService.cancelTop(postId);
        return Resp.ok();
    }

    @DeleteMapping("/{postId}")
    @ApiOperation("删除帖子")
    public Resp delPost(@PathVariable("postId") Integer postId) {
        adminService.deletePost(postId);
        return Resp.ok();
    }

    @GetMapping("/essence/{postId}")
    @ApiOperation("加精帖子")
    public Resp essencePost(@PathVariable("postId") Integer postId) {
        adminService.essencePost(postId);
        return Resp.ok();
    }

    @GetMapping("/unessence/{postId}")
    @ApiOperation("取消加精")
    public Resp CancelEssencePost(@PathVariable("postId") Integer postId) {
        adminService.cancelEssence(postId);
        return Resp.ok();
    }


}

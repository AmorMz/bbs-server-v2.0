package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.interceptor.AuthInterceptor;
import pers.muzi.bbs.service.UserService;

import java.util.Objects;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/relationship")
@Api(tags = "用户关系")
@CrossOrigin
public class RelationshipController {

    @Autowired
    private UserService userService;

    @ApiOperation("判断用户是否关注")
    @GetMapping("/validate/{userId}")
    @LoginRequired
    public Resp hasFollow(@PathVariable("userId") Integer userId) {
        // 当前登录用户id
        Integer loginId = AuthInterceptor.getId();
        Boolean hasFollow = userService.validateFollow(loginId, userId);
        return Resp.ok().data("hasFollow", hasFollow);
    }


    @ApiOperation("关注")
    @GetMapping("/follow/{userId}")
    @LoginRequired
    public Resp follow(@PathVariable("userId") Integer userId) {
        // 当前登录用户id
        Integer loginId = AuthInterceptor.getId();
        if (Objects.equals(userId, loginId)) {
            return Resp.ok().message("不能关注自己哦~");
        }

        if (userService.validateFollow(loginId, userId)) {
            return Resp.ok().message("您已经关注他了~不能重复关注哦");
        }

        userService.follow(loginId, userId);
        return Resp.ok().message("关注成功!");
    }


    @ApiOperation("取消关注")
    @GetMapping("/unfollow/{userId}")
    @LoginRequired
    public Resp unFollow(@PathVariable("userId") Integer userId) {
        // 当前登录用户id
        Integer loginId = AuthInterceptor.getId();
        if (!Objects.equals(userId, loginId)) {
            return Resp.ok().message("您还没有关注他哦~");
        }

        userService.unfollow(loginId, userId);
        return Resp.ok().message("取消关注成功!");
    }


}

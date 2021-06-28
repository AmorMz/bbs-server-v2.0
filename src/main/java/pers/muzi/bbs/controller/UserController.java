package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.vo.user.AuthorVO;
import pers.muzi.bbs.entity.vo.user.UserInfoVO;
import pers.muzi.bbs.interceptor.AuthInterceptor;
import pers.muzi.bbs.service.UserService;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    @LoginRequired
    public Resp getUserInfo() {
        // 当前登录用户id
        Integer id = AuthInterceptor.getId();
        User user = userService.getUserById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return Resp.ok().data("userInfo", userInfoVO);
    }
}

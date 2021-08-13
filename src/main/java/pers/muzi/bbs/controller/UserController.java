package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.entity.User;
import pers.muzi.bbs.entity.vo.user.AuthorVO;
import pers.muzi.bbs.entity.vo.user.UserCenterVO;
import pers.muzi.bbs.entity.vo.user.UserInfoVO;
import pers.muzi.bbs.exception.ParamException;
import pers.muzi.bbs.exception.UserException;
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
        UserInfoVO userInfoVO = userService.getUserInfoById(id);
        return Resp.ok().data("userInfo", userInfoVO);
    }

    @ApiOperation("个人中心获取用户信息")
    @GetMapping("/info/{account}")
    public Resp getUserInfo(@PathVariable String account) {
        User user = userService.getUserByAccount(account);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        UserCenterVO userCenterVO = new UserCenterVO();
        BeanUtils.copyProperties(user, userCenterVO);
        return Resp.ok().data("userInfo", userCenterVO);
    }
}

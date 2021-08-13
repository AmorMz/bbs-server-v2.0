package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.common.utils.JwtUtils;
import pers.muzi.bbs.entity.dto.LoginDTO;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.interceptor.AuthInterceptor;
import pers.muzi.bbs.service.UserService;
import pers.muzi.bbs.service.VerifyCodeService;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "认证接口")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Resp register(@RequestBody @Validated RegisterDTO registerDTO, @RequestParam String UUID) {
        // 校验验证码
        if (!verifyCodeService.verify(UUID, registerDTO.getVerifyCode())) {
            // 验证码错误
            return Resp.error().message("验证码输入错误或已过期，请重新输入");
        }
        // 注册
        userService.register(registerDTO);
        return Resp.ok().message("注册成功，即将跳转至登陆界面");
    }


    @ApiOperation("登录")
    @PostMapping("/login")
    public Resp login(@RequestBody @Validated LoginDTO loginDTO, @RequestParam String UUID) {
        // 校验验证码
        if (!verifyCodeService.verify(UUID, loginDTO.getVerifyCode())) {
            // 验证码错误
            return Resp.error().message("验证码输入错误或已过期，请重新输入");
        }
        // 登录
        String token = userService.login(loginDTO);
        // token过期时间
        long exp = loginDTO.getRememberMe() ? JwtUtils.getRememberExpirationTime() : JwtUtils.getExpirationTime();
        // 过期时间转换成天返回给前端
        return Resp.ok()
                .data("token", token)
                .data("exp", exp / 1000 / 60 / 60 / 24);
    }


    @ApiOperation("退出登录")
    @GetMapping("/logout")
    @LoginRequired
    public Resp logout() {
        Integer id = AuthInterceptor.getId();
        userService.logout(id);
        return Resp.ok().message("退出成功");
    }

}

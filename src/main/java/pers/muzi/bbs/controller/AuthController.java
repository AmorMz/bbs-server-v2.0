package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.common.utils.JwtUtil;
import pers.muzi.bbs.entity.dto.LoginDTO;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.exception.ParamException;
import pers.muzi.bbs.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "认证接口")
public class AuthController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Resp register(@RequestBody @Validated RegisterDTO registerDTO, HttpServletRequest request) {
        // 判断验证码输入是否正确
        String code = (String) request.getSession().getAttribute("code");
        if (!registerDTO.getVerifyCode().equalsIgnoreCase(code)) {
            throw new ParamException("验证码错误或已过期，请重新获取");
        }

        // 校验两次密码输入是否一致
        if (!Objects.equals(registerDTO.getPassword(), registerDTO.getCheckPass())) {
            throw new ParamException("两次密码输入不一致，请检查");
        }

        // 注册
        userService.register(registerDTO);
        return Resp.ok().message("注册成功，即将跳转至登陆界面");
    }


    @ApiOperation("登录")
    @PostMapping("/login")
    public Resp login(@RequestBody @Validated LoginDTO loginDTO, HttpServletRequest request) {
        // 判断验证码输入是否正确
        String code = (String) request.getSession().getAttribute("code");
        if (!loginDTO.getVerifyCode().equalsIgnoreCase(code)) {
            throw new ParamException("验证码错误或已过期，请重新获取");
        }
        // 登录
        String token = userService.login(loginDTO);
        // token过期时间
        long exp = loginDTO.getRememberMe() ? JwtUtil.getRememberExpirationTime() : JwtUtil.getExpirationTime();
        // 过期时间转换成天返回给前端
        return Resp.ok()
                .data("token", token)
                .data("exp", exp / 1000 / 60 / 60 / 24);
    }


    @ApiOperation("退出登录")
    @GetMapping("/logout")
    @LoginRequired
    public Resp logout() {
        return Resp.ok().message("退出成功");
    }

}

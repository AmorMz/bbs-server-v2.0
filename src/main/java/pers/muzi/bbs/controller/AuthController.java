package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.entity.dto.RegisterDTO;
import pers.muzi.bbs.service.UserService;

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
    public Resp register(@RequestBody @Validated RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Resp.ok();
    }
}

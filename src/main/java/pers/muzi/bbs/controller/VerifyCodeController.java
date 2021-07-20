package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.service.VerifyCodeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/verify")
@Api(tags = "获取验证码")
@CrossOrigin
public class VerifyCodeController {


    @Autowired
    private VerifyCodeService verifyCodeService;

    @ApiOperation("获取验证码")
    @GetMapping("/code")
    public Resp verifyCode() {
        Map<String, String> map = verifyCodeService.getVerifyCodeBase64();
        return Resp.ok()
                .data("code", map.get("codeBase64"))
                .data("uuid", map.get("uuid"));
    }

}

package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.common.utils.VerifyCodeUtils;
import pers.muzi.bbs.entity.VerifyCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/verify")
@Api(tags = "获取验证码")
@CrossOrigin
public class VerifyCodeController {

    @Autowired
    private VerifyCodeUtils verifyCodeUtils;

    @ApiOperation("获取验证码")
    @GetMapping("/code")
    public Resp verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置长宽
        VerifyCode verifyCode = verifyCodeUtils.generate(80, 36);
        String code = verifyCode.getCode();
        // 放入session
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        // 设置过期时间 5分钟
        session.setMaxInactiveInterval(5 * 60);
        // 图片编码为Base64
        String codeBase64 = "data:image/png;base64," + VerifyCodeUtils.getVerifyCodeBase64(verifyCode.getImgBytes());
        return Resp.ok().data("verifyCode", codeBase64);
    }

}

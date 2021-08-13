package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.service.OssService;

/**
 * @author AmorMz
 * 阿里云oss 文件上传统一接口
 */
@RestController
@RequestMapping("/oss")
@CrossOrigin
@Api(tags = "文件上传")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传文件")
    @PostMapping("/test")
    public Resp uploadOssFile(MultipartFile file) {
        String uploadUrl = ossService.uploadImage(file);
        return Resp.ok().data("url", uploadUrl);
    }

}

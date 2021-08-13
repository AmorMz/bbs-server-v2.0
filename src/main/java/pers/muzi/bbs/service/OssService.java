package pers.muzi.bbs.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author AmorMz
 */
public interface OssService {

    /**
     * 文章图片上传接口
     * @param file 文件
     * @return 上传的url
     */
    String uploadImage(MultipartFile file);
}

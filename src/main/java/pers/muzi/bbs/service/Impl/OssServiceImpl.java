package pers.muzi.bbs.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.muzi.bbs.common.utils.CommonUtils;
import pers.muzi.bbs.exception.UploadException;
import pers.muzi.bbs.service.OssService;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * @author AmorMz
 */
@Service
public class OssServiceImpl implements OssService {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    /**
     * 文件上传最大大小 单位 byte
     * 默认 300KB
     */
    private static final long MAX_SIZE = 300 * 1024;

    /**
     * 允许上传的文件格式
     */
    private HashSet<String> allowUploadSet = new HashSet<>();

    /**
     * 初始化允许文件上传格式
     */
    {
        allowUploadSet.add("jpg");
        allowUploadSet.add("jpeg");
        allowUploadSet.add("png");
        allowUploadSet.add("gif");
        allowUploadSet.add("bmp");
        allowUploadSet.add("webp");

    }

    /**
     * 上传图片
     * @param file 文件
     * @return 上传图片外部访问url
     */
    @Override
    public String uploadImage(MultipartFile file) {

        // 边界检查
        if (file == null || file.isEmpty()) {
            throw new UploadException("文件内容为空或者丢失，请重新上传");
        }

        // 获取文件类型 判断是否可以上传
        String fileType = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        if (!allowUploadSet.contains(fileType)) {
            throw new UploadException("文件格式不支持，请重新选择");
        }

        try {
            // 建立连接
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取输入流
            InputStream inputStream = file.getInputStream();
            // 随机文件名 相同文件名上传后会被覆盖
            String fileName = CommonUtils.UUID().replace("-", "") +
                    file.getOriginalFilename();

            // 根据日期分类
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = sdf.format(date);
            fileName = datePath + "/" + fileName;
            // 上传文件
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            // 把上传之后文件的路径返回
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

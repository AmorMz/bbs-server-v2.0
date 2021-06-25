package pers.muzi.bbs.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author AmorMz
 * 验证码实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class VerifyCode {

    @ApiModelProperty("验证码内容")
    private String code;

    @ApiModelProperty("图片二进制数组")
    private byte[] imgBytes;

    @ApiModelProperty("过期时间 毫秒")
    private long expireTime;
}

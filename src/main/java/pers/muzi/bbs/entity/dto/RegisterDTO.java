package pers.muzi.bbs.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author AmorMz
 * 注册DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class RegisterDTO {

    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @Size(min = 5, max = 10, message = "账号长度限制为5到10位")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "账号只能包含字母、数字、_和-")
    private String account;

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空")
    @Size(min = 2, max = 8, message = "昵称长度限制为2到8位")
    private String nickname;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度限制为6到16位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "密码只能包含字母、数字")
    private String password;

    @ApiModelProperty("确定密码")
    private String checkPass;

    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    @Size(min = 4, max = 4, message = "验证码长度为4位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "验证码只能为字母、数字")
    private String verifyCode;

}

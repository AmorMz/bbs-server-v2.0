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

/**
 * @author AmorMz
 * 登录DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class LoginDTO {
    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @Size(min = 5, max = 10, message = "账号长度限制为5到10位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号只能包含字母、数字")
    private String account;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度限制为6到16位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "密码只能包含字母、数字")
    private String password;

    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    @Size(min = 4, max = 4, message = "验证码长度为4位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "验证码只能为字母、数字")
    private String verifyCode;

    @ApiModelProperty("记住我")
    private Boolean rememberMe;
}

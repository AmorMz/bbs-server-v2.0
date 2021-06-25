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
    @Pattern(regexp = "[0-9a-zA-Z]", message = "账号只能包含字母、数字")
    private String account;

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空")
    @Size(min = 2, max = 8, message = "昵称长度限制为2到8位")
    private String nickname;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度限制为6到16位")
    @Pattern(regexp = "[0-9a-zA-Z]", message = "密码只能包含字母、数字")
    private String password;

    @ApiModelProperty("确定密码")
    private String checkPass;

}

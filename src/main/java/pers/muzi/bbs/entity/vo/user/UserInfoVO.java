package pers.muzi.bbs.entity.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author AmorMz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class UserInfoVO implements Serializable {
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像 url")
    private String avatar;

    @ApiModelProperty("用户角色 0-普通用户 1-admin")
    private Integer role;
}

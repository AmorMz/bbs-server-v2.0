package pers.muzi.bbs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author AmorMz
 * 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class User {
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("密码盐")
    private String salt;

    @ApiModelProperty("头像 url")
    private String avatar;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("积分")
    private Integer score;

    @ApiModelProperty("个人简介")
    private String about;

    @ApiModelProperty("是否封禁")
    private Boolean status;

    @ApiModelProperty("用户角色 0-普通用户 1-admin")
    private Integer role;

    @ApiModelProperty("逻辑删除 是否注销")
    private Boolean delete;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

}

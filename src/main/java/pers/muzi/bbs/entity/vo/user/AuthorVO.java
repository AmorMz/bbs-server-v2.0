package pers.muzi.bbs.entity.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author AmorMz
 * 展示作者信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class AuthorVO {
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像 url")
    private String avatar;

    @ApiModelProperty("发布文章数")
    private Integer postCount;

    @ApiModelProperty("粉丝数量")
    private Integer followerCount;

}

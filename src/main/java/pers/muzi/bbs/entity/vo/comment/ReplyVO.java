package pers.muzi.bbs.entity.vo.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author AmorMz
 * 楼中楼回复VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class ReplyVO {
    @ApiModelProperty("评论id")
    private Integer id;

    @ApiModelProperty("发布者昵称")
    private String fromNickname;

    @ApiModelProperty("发布账号")
    private String fromAccount;

    @ApiModelProperty("接收者昵称")
    private String toNickname;

    @ApiModelProperty("接收者账号")
    private String toAccount;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;


}

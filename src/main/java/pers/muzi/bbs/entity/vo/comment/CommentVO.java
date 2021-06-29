package pers.muzi.bbs.entity.vo.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author AmorMz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class CommentVO {
    @ApiModelProperty("评论id")
    private Integer id;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("作者头像")
    private String avatar;

    @ApiModelProperty("作者账号")
    private String account;

    @ApiModelProperty("作者昵称")
    private String nickname;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("楼中楼回复")
    private List<ReplyVO> reply;
}

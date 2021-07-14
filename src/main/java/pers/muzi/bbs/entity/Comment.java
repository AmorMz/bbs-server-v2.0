package pers.muzi.bbs.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author AmorMz
 * 评论实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class Comment {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("发表者id")
    private Integer userId;

    @ApiModelProperty("所属帖子id")
    private Integer postId;

    @ApiModelProperty("目标id 一级评论为0")
    private Integer targetId;

    @ApiModelProperty("所属评论id 一级评论为0")
    private Integer commentId;

    @ApiModelProperty("逻辑删除")
    private Boolean delete;

    @ApiModelProperty("创建时间")
    private Date createTime;

}

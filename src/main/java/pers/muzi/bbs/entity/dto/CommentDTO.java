package pers.muzi.bbs.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author AmorMz
 * 发表评论DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class CommentDTO {

    @ApiModelProperty("评论内容")
    @NotBlank(message = "评论内容不能为空")
    private String content;

    @ApiModelProperty("所属帖子id")
    @NotNull(message = "参数有误")
    private Integer postId;

    @ApiModelProperty("目标id 一级评论为0")
    @NotNull(message = "参数有误")
    private Integer targetId;

    @ApiModelProperty("所属评论id 0-一级评论")
    @NotNull(message = "参数有误")
    private Integer commentId;
}

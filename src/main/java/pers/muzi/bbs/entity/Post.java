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
 * 帖子/文章实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class Post {
    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("帖子内容 markdown")
    private String content;

    @ApiModelProperty("作者id")
    private Integer userId;

    @ApiModelProperty("评论数量")
    private Integer commentCount;

    @ApiModelProperty("收藏数量")
    private Integer collectCount;

    @ApiModelProperty("浏览数量")
    private Integer viewCount;

    @ApiModelProperty("是否置顶")
    private Boolean top;

    @ApiModelProperty("是否加精")
    private Boolean essence;

    @ApiModelProperty("点赞数量")
    private Integer likeCount;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("逻辑删除")
    private Boolean delete;
}

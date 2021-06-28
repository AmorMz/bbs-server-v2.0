package pers.muzi.bbs.entity.vo.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import pers.muzi.bbs.entity.vo.user.AuthorVO;

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
public class PostDetailVO {
    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("作者信息")
    private AuthorVO author;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("帖子内容")
    private String content;

    @ApiModelProperty("帖子标签")
    private List<String> tags;

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

    @ApiModelProperty("最后修改时间")
    private Date modifyTime;
}

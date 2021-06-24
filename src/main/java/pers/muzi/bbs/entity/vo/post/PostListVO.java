package pers.muzi.bbs.entity.vo.post;

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
public class PostListVO {
    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("帖子标题")
    private String title;

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

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("作者id")
    private Integer userId;

    @ApiModelProperty("作者账号")
    private String account;

    @ApiModelProperty("作者昵称")
    private String nickname;

    @ApiModelProperty("作者头像url")
    private String avatar;


}

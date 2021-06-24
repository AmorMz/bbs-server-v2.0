package pers.muzi.bbs.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author AmorMz
 * 发布帖子DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class PostDTO {
    @ApiModelProperty("帖子标题")
    @NotNull(message = "标题不能为空")
    @Size(min = 1, max = 30, message = "标题长度在1到30之间")
    private String title;

    @ApiModelProperty("帖子内容 markdown")
    @NotBlank(message = "正文内容不能为空")
    private String content;

    @ApiModelProperty("帖子标签")

    private List<String> tags;
}

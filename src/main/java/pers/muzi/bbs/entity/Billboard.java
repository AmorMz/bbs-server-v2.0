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
 * 系统公告实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class Billboard {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("公告内容")
    private String content;

    @ApiModelProperty("逻辑删除")
    private Boolean delete;

    @ApiModelProperty("发布时间")
    private Date createTime;
}

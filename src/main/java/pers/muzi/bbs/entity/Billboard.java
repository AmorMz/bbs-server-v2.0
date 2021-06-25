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

    /**
     * ID主键
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 公告内容
     */
    @ApiModelProperty("公告内容")
    private String content;

    /**
     * 逻辑删除
     */
    @ApiModelProperty("逻辑删除")
    private Boolean delete;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date createTime;
}

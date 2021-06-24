package pers.muzi.bbs.entity.vo.billboard;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author AmorMz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class BillboardVO {

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
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date createTime;
}

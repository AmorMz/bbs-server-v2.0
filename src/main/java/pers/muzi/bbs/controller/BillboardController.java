package pers.muzi.bbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.entity.Billboard;
import pers.muzi.bbs.entity.vo.billboard.BillboardVO;
import pers.muzi.bbs.service.BillboardService;

import java.util.Date;

/**
 * @author AmorMz
 */
@RestController
@RequestMapping("/billboard")
@Api(tags = "系统公告接口")
@CrossOrigin
public class BillboardController {

    @Autowired
    private BillboardService billboardService;

    @ApiOperation(value = "获取最新系统公告")
    @GetMapping
    public Resp getBillboard() {
        Billboard billboard = billboardService.getBillboard();
        // 暂无公告返回默认公告
        if (billboard == null) {
            BillboardVO defaultBillboard = new BillboardVO();
            defaultBillboard
                    .setId(-1)
                    .setContent("暂无公告")
                    .setCreateTime(new Date());
            return Resp.ok().data("billboard", defaultBillboard);
        }

        BillboardVO billboardVO = new BillboardVO();
        BeanUtils.copyProperties(billboard, billboardVO);
        return Resp.ok().data("billboard", billboard);
    }
}

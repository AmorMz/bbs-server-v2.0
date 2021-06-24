package pers.muzi.bbs.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.dao.BillboardDAO;
import pers.muzi.bbs.entity.Billboard;
import pers.muzi.bbs.service.BillboardService;

/**
 * @author AmorMz
 */
@Service
public class BillboardServiceImpl implements BillboardService {

    @Autowired
    private BillboardDAO billboardDAO;

    /**
     * 获取最新系统公告
     *
     * @return 公告实体类
     */
    @Override
    public Billboard getBillboard() {
        return billboardDAO.getBillboard();
    }
}

package pers.muzi.bbs.service;

import pers.muzi.bbs.entity.Billboard;

/**
 * @author AmorMz
 */
public interface BillboardService {

    /**
     * 获取最新系统公告
     *
     * @return 公告实体类
     */
    Billboard getBillboard();
}

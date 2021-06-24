package pers.muzi.bbs.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.muzi.bbs.entity.Billboard;

/**
 * @author AmorMz
 * 系统公告DAO层
 */
@Mapper
public interface BillboardDAO {

    /**
     * 获取最新系统公告
     *
     * @return 公告实体类
     */
    Billboard getBillboard();
}

package top.auok.cbps.ts.user.dao;

import top.auok.cbps.ts.core.dao.BaseDao;
import top.auok.cbps.ts.user.entity.RpUserPayInfo;

/**
 * 用户第三方支付信息dao
 */
public interface RpUserPayInfoDao  extends BaseDao<RpUserPayInfo> {

    /**
     * 通过商户编号获取商户第三方支付信息
     * @param userNo
     * @param payWayCode
     * @return
     */
    public  RpUserPayInfo getByUserNo(String userNo, String payWayCode);

}
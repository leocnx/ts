package top.auok.cbps.ts.trade.dao;

import top.auok.cbps.ts.core.dao.BaseDao;
import top.auok.cbps.ts.trade.entity.RpTradePaymentOrder;

/**
 * 商户支付订单,dao层接口
 */
public interface RpTradePaymentOrderDao  extends BaseDao<RpTradePaymentOrder>{

    /**
     * 根据商户编号及商户订单号获取支付订单信息
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    RpTradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo);

}

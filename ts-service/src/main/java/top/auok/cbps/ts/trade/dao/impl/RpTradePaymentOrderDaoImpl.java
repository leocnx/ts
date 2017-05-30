package top.auok.cbps.ts.trade.dao.impl;

import top.auok.cbps.ts.core.dao.impl.BaseDaoImpl;
import top.auok.cbps.ts.trade.dao.RpTradePaymentOrderDao;
import top.auok.cbps.ts.trade.entity.RpTradePaymentOrder;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 商户支付订单,dao层实现类
 */
@Repository("rpTradePaymentOrderDao")
public class RpTradePaymentOrderDaoImpl extends BaseDaoImpl<RpTradePaymentOrder> implements RpTradePaymentOrderDao{


    /**
     * 根据商户编号及商户订单号获取支付订单信息
     * @param merchantNo    商户编号
     * @param merchantOrderNo   商户订单号
     * @return
     */
    @Override
    public RpTradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("merchantNo",merchantNo);
        paramMap.put("merchantOrderNo",merchantOrderNo);
        return super.getBy(paramMap);
    }

}

package top.auok.cbps.ts.trade.dao;

import java.util.List;
import java.util.Map;

import top.auok.cbps.ts.core.dao.BaseDao;
import top.auok.cbps.ts.trade.entity.RpTradePaymentRecord;

/**
 * 商户支付记录,dao层接口
 */
public interface RpTradePaymentRecordDao extends BaseDao<RpTradePaymentRecord>{

    /**
     * 根据银行订单号获取支付信息
     * @param bankOrderNo
     * @return
     */
    RpTradePaymentRecord getByBankOrderNo(String bankOrderNo);

    /**
     * 根据商户编号及商户订单号获取支付成功的结果
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    RpTradePaymentRecord getSuccessRecordByMerchantNoAndMerchantOrderNo(String merchantNo , String merchantOrderNo);

    /**
	 * 根据支付流水号查询支付记录
	 * 
	 * @param trxNo
	 * @return
	 */
	RpTradePaymentRecord getByTrxNo(String trxNo);

	List<Map<String, String>> getPaymentReport(String merchantNo);

	List<Map<String, String>> getPayWayReport(String merchantNo);

}

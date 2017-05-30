package top.auok.cbps.ts.trade.dao.impl;

import top.auok.cbps.ts.core.dao.impl.BaseDaoImpl;
import top.auok.cbps.ts.trade.dao.RpTradePaymentRecordDao;
import top.auok.cbps.ts.trade.entity.RpTradePaymentRecord;

import top.auok.cbps.ts.trade.enums.TradeStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户支付记录,dao层实现类
 */
@Repository("rpTradePaymentRecordDao")
public class RpTradePaymentRecordDaoImpl extends BaseDaoImpl<RpTradePaymentRecord> implements RpTradePaymentRecordDao {

	/**
	 * 根据银行订单号获取支付信息
	 *
	 * @param bankOrderNo
	 * @return
	 */
	@Override
	public RpTradePaymentRecord getByBankOrderNo(String bankOrderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bankOrderNo", bankOrderNo);
		return super.getBy(paramMap);
	}

	/**
	 * 根据商户编号及商户订单号获取支付结果
	 *
	 * @param merchantNo
	 * @param merchantOrderNo
	 * @return
	 */
	@Override
	public RpTradePaymentRecord getSuccessRecordByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		paramMap.put("merchantOrderNo", merchantOrderNo);
		return super.getBy(paramMap);
	}

	/**
	 * 根据支付流水号查询支付记录
	 * 
	 * @param trxNo
	 * @return
	 */
	public RpTradePaymentRecord getByTrxNo(String trxNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("trxNo", trxNo);
		return super.getBy(paramMap);
	}
	
	public List<Map<String, String>> getPaymentReport(String merchantNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		return super.getSqlSession().selectList(getStatement("getPaymentReport"),paramMap);
	}

	public List<Map<String, String>> getPayWayReport(String merchantNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		return super.getSqlSession().selectList(getStatement("getPayWayReport"),paramMap);
	}

}

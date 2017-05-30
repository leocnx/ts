package top.auok.cbps.ts.account.service;

import java.math.BigDecimal;

import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.core.exception.BizException;

/**
 *  账户操作service接口
 */
public interface RpAccountTransactionService {

	/** 加款:有银行流水 **/
	RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo,String bankTrxNo, String trxType, String remark) throws BizException;

	/** 减款 :有银行流水**/
	RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo,String bankTrxNo, String trxType, String remark) throws BizException;
	
	/** 加款 **/
	RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;

	/** 减款 **/
	RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;


	/** 冻结 **/
	RpAccount freezeAmount(String userNo, BigDecimal freezeAmount) throws BizException;

	/** 结算成功：解冻+减款 **/
	RpAccount unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;
	
	/** 结算失败：解冻 **/
	RpAccount unFreezeSettAmount(String userNo, BigDecimal amount) throws BizException;

	/** 更新账户历史中的结算状态，并且累加可结算金额 **/
	void settCollectSuccess(String accountNo, String collectDate, int riskDay, BigDecimal totalAmount) throws BizException;

}
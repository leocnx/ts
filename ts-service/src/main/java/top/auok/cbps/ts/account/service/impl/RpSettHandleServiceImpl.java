package top.auok.cbps.ts.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.auok.cbps.ts.account.dao.RpSettDailyCollectDao;
import top.auok.cbps.ts.account.dao.RpSettRecordDao;
import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.account.entity.RpSettDailyCollect;
import top.auok.cbps.ts.account.entity.RpSettRecord;
import top.auok.cbps.ts.account.enums.SettDailyCollectStatusEnum;
import top.auok.cbps.ts.account.enums.SettDailyCollectTypeEnum;
import top.auok.cbps.ts.account.enums.SettModeTypeEnum;
import top.auok.cbps.ts.account.enums.SettRecordStatusEnum;
import top.auok.cbps.ts.account.exception.AccountBizException;
import top.auok.cbps.ts.account.exception.SettBizException;
import top.auok.cbps.ts.account.service.RpAccountQueryService;
import top.auok.cbps.ts.account.service.RpAccountTransactionService;
import top.auok.cbps.ts.account.service.RpSettHandleService;
import top.auok.cbps.ts.account.utils.AccountConfigUtil;
import top.auok.cbps.ts.account.vo.DailyCollectAccountHistoryVo;
import top.auok.cbps.ts.core.exception.BizException;
import top.auok.cbps.ts.core.utils.DateUtils;
import top.auok.cbps.ts.trade.enums.TrxTypeEnum;
import top.auok.cbps.ts.user.entity.RpUserBankAccount;
import top.auok.cbps.ts.user.entity.RpUserInfo;
import top.auok.cbps.ts.user.enums.BankAccountTypeEnum;
import top.auok.cbps.ts.user.exception.UserBizException;
import top.auok.cbps.ts.user.service.RpUserBankAccountService;
import top.auok.cbps.ts.user.service.RpUserInfoService;

/**
 * 结算核心业务处理实现类
 */
@Service("rpSettHandleService")
public class RpSettHandleServiceImpl implements RpSettHandleService {
	@Autowired
	private RpSettDailyCollectDao rpSettDailyCollectDao;
	@Autowired
	private RpSettRecordDao rpSettRecordDao;
	@Autowired
	private RpAccountTransactionService rpAccountTransactionService;
	@Autowired
	private RpAccountQueryService rpAccountQueryService;
	@Autowired
	private RpUserInfoService rpUserInfoService;
	@Autowired
	private RpUserBankAccountService rpUserBankAccountService;

	/**
	 * 按单个商户发起每日待结算数据统计汇总.<br/>
	 * 
	 * @param userNo
	 *            用户编号.
	 * @param endDate
	 *            汇总结束日期.
	 * @param riskDay
	 *            风险预存期.
	 * @param userName
	 *            用户名称
	 * @param codeNum
	 *            企业代号
	 */
	@Transactional(rollbackFor = Exception.class)
	public void dailySettlementCollect(String userNo, Date endDate, int riskDay, String userName){
		// 根据用户编号查询账户
		RpAccount account = rpAccountQueryService.getAccountByUserNo(userNo);
		// 汇总日期
		String endDateStr = DateUtils.formatDate(endDate, "yyyy-MM-dd");
		// 汇总账户历史
		List<DailyCollectAccountHistoryVo> accountHistoryList = rpAccountQueryService.listDailyCollectAccountHistoryVo(account.getAccountNo(), endDateStr, riskDay, null);
		// 遍历统计
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (DailyCollectAccountHistoryVo collectVo : accountHistoryList) {
			// 累加可结算金额
			totalAmount = totalAmount.add(collectVo.getTotalAmount());
			// 保存结算汇总记录
			RpSettDailyCollect dailyCollect = new RpSettDailyCollect();
			dailyCollect.setAccountNo(collectVo.getAccountNo());
			dailyCollect.setUserName(userName);
			dailyCollect.setCollectDate(collectVo.getCollectDate());
			dailyCollect.setCollectType(SettDailyCollectTypeEnum.ALL.name());
			dailyCollect.setTotalAmount(collectVo.getTotalAmount());
			dailyCollect.setTotalCount(collectVo.getTotalNum());
			dailyCollect.setSettStatus(SettDailyCollectStatusEnum.SETTLLED.name());
			dailyCollect.setRiskDay(collectVo.getRiskDay());
			dailyCollect.setRemark("");
			dailyCollect.setEditTime(new Date());
			rpSettDailyCollectDao.insert(dailyCollect);
		}

		// 更新账户历史中的结算状态，并且累加可结算金额
		rpAccountTransactionService.settCollectSuccess(userNo, endDateStr, riskDay, totalAmount);
	}
	
	/**
	 * 发起结算--对应与接口
	 * 
	 * @param userNo
	 * @param accountNo
	 * @param settAmount
	 * @param bankAccount
	 */
	public void launchSett(String userNo, BigDecimal settAmount){
		RpAccount account = rpAccountQueryService.getAccountByUserNo(userNo);
		RpUserInfo userInfo = rpUserInfoService.getDataByMerchentNo(userNo);
		RpUserBankAccount rpUserBankAccount = rpUserBankAccountService.getByUserNo(userNo);
		BigDecimal availableAmount = account.getAvailableSettAmount();
		if (settAmount.compareTo(availableAmount) > 0) {
			// 金额超限
			throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
		}
		if (rpUserBankAccount == null) {
			throw UserBizException.USER_BANK_ACCOUNT_IS_NULL;
			
		}
		
		String settType = SettModeTypeEnum.SELFHELP_SETTLE.name();
		this.launchSett(userNo, userInfo.getUserName(), account.getAccountNo(), settAmount, rpUserBankAccount, settType);
		
	}

	/**
	 * 发起结算
	 * 
	 * @param userNo
	 * @param accountNo
	 * @param settAmount
	 * @param bankAccount
	 * @param settType 发起结算方式:手动、自动
	 */

	@Transactional(rollbackFor = Exception.class)
	private void launchSett(String userNo, String userName, String accountNo, BigDecimal settAmount, RpUserBankAccount bankAccount,String settType) {
		
		// 所行查询账户
		RpSettRecord settRecord = new RpSettRecord();
		settRecord.setAccountNo(accountNo);
		settRecord.setCountry("中国");
		settRecord.setProvince(bankAccount.getProvince());
		settRecord.setCity(bankAccount.getCity());
		settRecord.setAreas(bankAccount.getAreas());
		settRecord.setBankAccountAddress(bankAccount.getStreet());
		settRecord.setBankAccountName(bankAccount.getBankAccountName());
		settRecord.setBankCode(bankAccount.getBankCode());
		settRecord.setBankName(bankAccount.getBankName());
		settRecord.setBankAccountNo(bankAccount.getBankAccountNo());
		settRecord.setBankAccountType(bankAccount.getBankAccountType());
		settRecord.setOperatorLoginname("");
		settRecord.setOperatorRealname("");
		settRecord.setRemitAmount(settAmount);
		settRecord.setRemitRequestTime(new Date());
		settRecord.setSettAmount(settAmount);
		settRecord.setSettFee(BigDecimal.ZERO);
		settRecord.setSettMode(settType);
		settRecord.setSettStatus(SettRecordStatusEnum.WAIT_CONFIRM.name());
		settRecord.setUserName(userName);
		settRecord.setUserNo(userNo);
		settRecord.setMobileNo(bankAccount.getMobileNo());
		settRecord.setEditTime(new Date());
		rpSettRecordDao.insert(settRecord);

		// 冻结准备结算出去的资金
		rpAccountTransactionService.freezeAmount(userNo, settAmount);
	}

	/**
	 * 发起自动结算
	 * 
	 * @param userNo
	 */
	public void launchAutoSett(String userNo){
		RpUserInfo userInfo = rpUserInfoService.getDataByMerchentNo(userNo);
		RpAccount account = rpAccountQueryService.getAccountByUserNo(userNo);
		BigDecimal settAmount = account.getAvailableSettAmount();
		String settMinAmount = AccountConfigUtil.readConfig("sett_min_amount");
		if (settAmount.compareTo(new BigDecimal(settMinAmount)) == -1) {
			throw new BizException("每次发起结算的金额必须大于:" + settMinAmount);
		}

		RpUserBankAccount rpUserBankAccount = rpUserBankAccountService.getByUserNo(userNo);
		if (rpUserBankAccount == null) {
			throw new BizException("没有结算银行卡信息，请先绑定结算银行卡");
		}

		// 根据银行卡信息判断收款账户的类型
		String bankType = rpUserBankAccount.getBankAccountType();

		// 如果是对私账户，需要控制 1.单笔上线是否小于5W 
		if (bankType.equals(BankAccountTypeEnum.PRIVATE_DEBIT_ACCOUNT.name())) {
			// 结算的金额最大值
			String settMaxAmount = AccountConfigUtil.readConfig("sett_max_amount");
			if (settAmount.compareTo(new BigDecimal(settMaxAmount)) == 1) {
				throw new BizException("每次发起结算的金额必须小于:" + settMaxAmount);
			}
		}
		// 结算记录中的userNo存企业表中企业代号
		String userName = userInfo.getUserName();
		String accountNo = account.getAccountNo();
		String settType = SettModeTypeEnum.REGULAR_SETTLE.name();
		this.launchSett(userNo, userName, accountNo, settAmount, rpUserBankAccount,settType);
	}

	/**
	 * 结算审核
	 */
	public void audit(String settId, String settStatus, String remark){
		RpSettRecord settRecord = rpSettRecordDao.getById(settId);
		if(!settRecord.getSettStatus().equals(SettRecordStatusEnum.WAIT_CONFIRM.name())){
			throw SettBizException.SETT_STATUS_ERROR;
		}
		settRecord.setSettStatus(settStatus);
		settRecord.setEditTime(new Date());
		settRecord.setRemark(remark);
		rpSettRecordDao.update(settRecord);
		
		if(settStatus.equals(SettRecordStatusEnum.CANCEL.name())){//审核不通过
			//解冻金额
			rpAccountTransactionService.unFreezeSettAmount(settRecord.getUserNo(), settRecord.getSettAmount());
		}
	}
	
	/**
	 * 打款
	 */
	@Transactional(rollbackFor = Exception.class)
	public void remit(String settId, String settStatus, String remark){
		RpSettRecord settRecord = rpSettRecordDao.getById(settId);
		if(!settRecord.getSettStatus().equals(SettRecordStatusEnum.CONFIRMED.name())){
			throw SettBizException.SETT_STATUS_ERROR;
		}
		settRecord.setSettStatus(settStatus);
		settRecord.setEditTime(new Date());
		settRecord.setRemitRemark(remark);
		settRecord.setRemitAmount(settRecord.getSettAmount());
		settRecord.setRemitConfirmTime(new Date());
		settRecord.setRemitRequestTime(new Date());
		rpSettRecordDao.update(settRecord);
		
		if(settStatus.equals(SettRecordStatusEnum.REMIT_FAIL.name())){//打款失败
			//解冻金额
			rpAccountTransactionService.unFreezeSettAmount(settRecord.getUserNo(), settRecord.getSettAmount());
		}else if(settStatus.equals(SettRecordStatusEnum.REMIT_SUCCESS.name())){
			rpAccountTransactionService.unFreezeAmount(settRecord.getUserNo(), settRecord.getSettAmount(), settRecord.getId(), TrxTypeEnum.REMIT.name(), remark);
		}
	}
}

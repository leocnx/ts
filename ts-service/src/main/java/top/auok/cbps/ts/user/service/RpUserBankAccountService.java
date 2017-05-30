package top.auok.cbps.ts.user.service;

import top.auok.cbps.ts.user.entity.RpUserBankAccount;

/**
 * 用户银行账户service接口
 */
public interface RpUserBankAccountService{
	
	/**
	 * 保存
	 */
	void saveData(RpUserBankAccount rpUserBankAccount);

	/**
	 * 更新
	 */
	void updateData(RpUserBankAccount rpUserBankAccount);

	/**
	 * 根据用户编号获取银行账户
	 */
	RpUserBankAccount getByUserNo(String userNo);

	/**
	 * 创建或更新
	 * @param rpUserBankAccount
	 */
	void createOrUpdate(RpUserBankAccount rpUserBankAccount);
}
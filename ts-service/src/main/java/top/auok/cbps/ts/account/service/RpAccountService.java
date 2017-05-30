package top.auok.cbps.ts.account.service;

import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;

/**
 *  账户service接口
 */
public interface RpAccountService{
	
	/**
	 * 保存
	 */
	void saveData(RpAccount rpAccount);

	/**
	 * 更新
	 */
	void updateData(RpAccount rpAccount);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpAccount getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpAccount rpAccount);
	
	/**
	 * 根据userNo获取数据
	 * 
	 * @param userNo
	 * @return
	 */
	RpAccount getDataByUserNo(String userNo);
	
}
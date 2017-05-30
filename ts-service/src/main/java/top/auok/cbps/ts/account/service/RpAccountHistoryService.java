package top.auok.cbps.ts.account.service;

import top.auok.cbps.ts.account.entity.RpAccountHistory;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;

/**
 * 账户历史service接口
 */
public interface RpAccountHistoryService{
	
	/**
	 * 保存
	 */
	void saveData(RpAccountHistory rpAccountHistory);

	/**
	 * 更新
	 */
	void updateData(RpAccountHistory rpAccountHistory);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpAccountHistory getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpAccountHistory rpAccountHistory);
	
}
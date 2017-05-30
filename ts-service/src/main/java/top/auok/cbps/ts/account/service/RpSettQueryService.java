package top.auok.cbps.ts.account.service;

import java.util.Map;

import top.auok.cbps.ts.account.entity.RpSettRecord;
import top.auok.cbps.ts.core.exception.BizException;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;

/**
 * 结算查询service接口
 */
public interface RpSettQueryService {

	/**
	 * 根据参数分页查询结算记录
	 * 
	 * @param pageParam
	 * @param params
	 *            ：map的key为 ：accountNo、userNo、settStatus...可以参考实体
	 * 
	 * @return
	 * @throws BizException
	 */
	public PageBean querySettRecordListPage(PageParam pageParam, Map<String, Object> params) throws BizException;

	/**
	 * 根据参数分页查询结算日汇总记录
	 * 
	 * @param pageParam
	 * @param params
	 *            ：map的key为 ：accountNo...可以参考实体
	 * 
	 * @return
	 * @throws BizException
	 */
	public PageBean querySettDailyCollectListPage(PageParam pageParam, Map<String, Object> params) throws BizException;
	
	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	public RpSettRecord getDataById(String id);
}
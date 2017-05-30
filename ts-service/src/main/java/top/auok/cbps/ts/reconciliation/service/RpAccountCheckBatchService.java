package top.auok.cbps.ts.reconciliation.service;

import java.util.List;
import java.util.Map;

import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.reconciliation.entity.RpAccountCheckBatch;

/**
 * 对账批次接口 .
 */
public interface RpAccountCheckBatchService {

	/**
	 * 保存
	 */
	void saveData(RpAccountCheckBatch rpAccountCheckBatch);

	/**
	 * 更新
	 */
	void updateData(RpAccountCheckBatch rpAccountCheckBatch);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpAccountCheckBatch getDataById(String id);

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

	/**
	 * 根据条件查询实体
	 * 
	 * @param paramMap
	 */
	public List<RpAccountCheckBatch> listBy(Map<String, Object> paramMap);

}
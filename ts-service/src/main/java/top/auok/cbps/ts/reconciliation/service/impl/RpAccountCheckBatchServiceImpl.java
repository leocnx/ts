package top.auok.cbps.ts.reconciliation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.reconciliation.dao.RpAccountCheckBatchDao;
import top.auok.cbps.ts.reconciliation.entity.RpAccountCheckBatch;
import top.auok.cbps.ts.reconciliation.service.RpAccountCheckBatchService;

/**
 * 对账批次接口实现 .
 */
@Service("rpAccountCheckBatchService")
public class RpAccountCheckBatchServiceImpl implements RpAccountCheckBatchService {

	@Autowired
	private RpAccountCheckBatchDao rpAccountCheckBatchDao;

	@Override
	public void saveData(RpAccountCheckBatch rpAccountCheckBatch) {
		rpAccountCheckBatchDao.insert(rpAccountCheckBatch);
	}

	@Override
	public void updateData(RpAccountCheckBatch rpAccountCheckBatch) {
		rpAccountCheckBatchDao.update(rpAccountCheckBatch);
	}

	@Override
	public RpAccountCheckBatch getDataById(String id) {
		return rpAccountCheckBatchDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {

		return rpAccountCheckBatchDao.listPage(pageParam, paramMap);
	}

	/**
	 * 根据条件查询实体
	 * 
	 * @param paramMap
	 */
	public List<RpAccountCheckBatch> listBy(Map<String, Object> paramMap) {
		return rpAccountCheckBatchDao.listBy(paramMap);
	}

}
package top.auok.cbps.ts.account.service.impl;

import top.auok.cbps.ts.account.dao.RpAccountHistoryDao;
import top.auok.cbps.ts.account.entity.RpAccountHistory;
import top.auok.cbps.ts.account.service.RpAccountHistoryService;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户历史service实现类
 */
@Service("rpAccountHistoryService")
public class RpAccountHistoryServiceImpl implements RpAccountHistoryService{

	@Autowired
	private RpAccountHistoryDao rpAccountHistoryDao;
	
	@Override
	public void saveData(RpAccountHistory rpAccountHistory) {
		rpAccountHistoryDao.insert(rpAccountHistory);
	}

	@Override
	public void updateData(RpAccountHistory rpAccountHistory) {
		rpAccountHistoryDao.update(rpAccountHistory);
	}

	@Override
	public RpAccountHistory getDataById(String id) {
		return rpAccountHistoryDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpAccountHistory rpAccountHistory) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", rpAccountHistory.getAccountNo());
		return rpAccountHistoryDao.listPage(pageParam, paramMap);
	}
}
package top.auok.cbps.ts.reconciliation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.reconciliation.dao.RpAccountCheckMistakeDao;
import top.auok.cbps.ts.reconciliation.entity.RpAccountCheckMistake;
import top.auok.cbps.ts.reconciliation.service.RpAccountCheckMistakeService;

/**
 * 对账批次接口实现 .
 */
@Service("rpAccountCheckMistakeService")
public class RpAccountCheckMistakeServiceImpl implements RpAccountCheckMistakeService {

	@Autowired
	private RpAccountCheckMistakeDao rpAccountCheckMistakeDao;

	@Override
	public void saveData(RpAccountCheckMistake rpAccountCheckMistake) {
		rpAccountCheckMistakeDao.insert(rpAccountCheckMistake);
	}

	@Override
	public void updateData(RpAccountCheckMistake rpAccountCheckMistake) {
		rpAccountCheckMistakeDao.update(rpAccountCheckMistake);
	}

	@Override
	public RpAccountCheckMistake getDataById(String id) {
		return rpAccountCheckMistakeDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return rpAccountCheckMistakeDao.listPage(pageParam, paramMap);
	}

	/**
	 * 批量保存差错记录
	 * 
	 * @param mistakeList
	 */
	public void saveListDate(List<RpAccountCheckMistake> mistakeList) {
		for (RpAccountCheckMistake mistake : mistakeList) {
			rpAccountCheckMistakeDao.insert(mistake);
		}

	}
}
package top.auok.cbps.ts.account.service.impl;

import top.auok.cbps.ts.account.dao.RpAccountDao;
import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.account.service.RpAccountService;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户service实现类
 */
@Service("rpAccountService")
public class RpAccountServiceImpl implements RpAccountService{

	@Autowired
	private RpAccountDao rpAccountDao;
	
	@Override
	public void saveData(RpAccount rpAccount) {
		rpAccountDao.insert(rpAccount);
	}

	@Override
	public void updateData(RpAccount rpAccount) {
		rpAccountDao.update(rpAccount);
	}

	@Override
	public RpAccount getDataById(String id) {
		return rpAccountDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpAccount rpAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", rpAccount.getAccountNo());
		return rpAccountDao.listPage(pageParam, paramMap);
	}
	
	@Override
	public RpAccount getDataByUserNo(String userNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo", userNo);
		return rpAccountDao.getBy(paramMap);
	}
}
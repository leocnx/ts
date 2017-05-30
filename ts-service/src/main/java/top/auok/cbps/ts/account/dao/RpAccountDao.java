package top.auok.cbps.ts.account.dao;

import java.util.Map;

import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.core.dao.BaseDao;

/**
 * 账户dao
 */
public interface RpAccountDao  extends BaseDao<RpAccount> {
	RpAccount getByAccountNo(String accountNo);

	RpAccount getByUserNo(Map<String, Object> map);
}
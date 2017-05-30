package top.auok.cbps.ts.account.dao.impl;

import top.auok.cbps.ts.account.dao.RpAccountDao;
import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.core.dao.impl.BaseDaoImpl;
import top.auok.cbps.ts.core.enums.PublicStatusEnum;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;


/**
 * 账户dao实现类
 */
@Repository
public class RpAccountDaoImpl  extends BaseDaoImpl<RpAccount> implements RpAccountDao{
	public RpAccount getByAccountNo(String accountNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", accountNo);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return this.getBy(paramMap);
	}

	public RpAccount getByUserNo(Map<String, Object> map){
		return this.getSessionTemplate().selectOne(getStatement("getByUserNo"), map);
	}
}
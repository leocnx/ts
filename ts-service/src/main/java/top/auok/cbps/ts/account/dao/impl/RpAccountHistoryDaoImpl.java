package top.auok.cbps.ts.account.dao.impl;

import top.auok.cbps.ts.account.dao.RpAccountHistoryDao;
import top.auok.cbps.ts.account.entity.RpAccountHistory;
import top.auok.cbps.ts.account.vo.DailyCollectAccountHistoryVo;
import top.auok.cbps.ts.core.dao.impl.BaseDaoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


/**
 * 账户历史dao实现类
 */
@Repository
public class RpAccountHistoryDaoImpl  extends BaseDaoImpl<RpAccountHistory> implements RpAccountHistoryDao{
	
	public List<RpAccountHistory> listPageByParams(Map<String, Object> params){
		return this.listBy(params);
	}
	
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params){
		return this.getSessionTemplate().selectList(getStatement("listDailyCollectAccountHistoryVo"), params);
	}

	/** 更新账户风险预存期外的账户历史记录记为结算完成 **/
	public void updateCompleteSettTo100(Map<String, Object> params){
		this.getSessionTemplate().update(getStatement("updateCompleteSettTo100"), params);
	}
}
package top.auok.cbps.ts.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.auok.cbps.ts.account.dao.RpSettDailyCollectDao;
import top.auok.cbps.ts.account.dao.RpSettRecordDao;
import top.auok.cbps.ts.account.entity.RpSettRecord;
import top.auok.cbps.ts.account.service.RpSettQueryService;
import top.auok.cbps.ts.core.exception.BizException;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;

/**
 * 结算查询service实现类
 */
@Service("rpSettQueryService")
public class RpSettQueryServiceImpl implements RpSettQueryService {

	@Autowired
	private RpSettRecordDao rpSettRecordDao;
	@Autowired
	private RpSettDailyCollectDao settDailyCollectMapper;

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
	public PageBean querySettRecordListPage(PageParam pageParam, Map<String, Object> params) throws BizException{
		return rpSettRecordDao.listPage(pageParam, params);
	}

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
	public PageBean querySettDailyCollectListPage(PageParam pageParam, Map<String, Object> params) throws BizException{
		return settDailyCollectMapper.listPage(pageParam, params);
	}
	
	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	public RpSettRecord getDataById(String id){
		return rpSettRecordDao.getById(id);
	}
}

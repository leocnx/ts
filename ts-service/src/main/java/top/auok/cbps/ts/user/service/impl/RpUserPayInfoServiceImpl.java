package top.auok.cbps.ts.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.user.dao.RpUserPayInfoDao;
import top.auok.cbps.ts.user.entity.RpUserPayInfo;
import top.auok.cbps.ts.user.service.RpUserPayInfoService;

/**
 * 用户第三方支付信息service实现类
 */
@Service("rpUserPayInfoService")
public class RpUserPayInfoServiceImpl implements RpUserPayInfoService{

	@Autowired
	private RpUserPayInfoDao rpUserPayInfoDao;
	
	@Override
	public void saveData(RpUserPayInfo rpUserPayInfo) {
		rpUserPayInfoDao.insert(rpUserPayInfo);
	}

	@Override
	public void updateData(RpUserPayInfo rpUserPayInfo) {
		rpUserPayInfoDao.update(rpUserPayInfo);
	}

	@Override
	public RpUserPayInfo getDataById(String id) {
		return rpUserPayInfoDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpUserPayInfo rpUserPayInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return rpUserPayInfoDao.listPage(pageParam, paramMap);
	}

	/**
	 * 通过商户编号获取商户支付配置信息
	 *
	 * @param userNO
	 * @return
	 */
	@Override
	public RpUserPayInfo getByUserNo(String userNo, String payWayCode) {
		return rpUserPayInfoDao.getByUserNo(userNo, payWayCode);
	}
}
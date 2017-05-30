package top.auok.cbps.ts.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.auok.cbps.ts.core.enums.PayTypeEnum;
import top.auok.cbps.ts.core.enums.PayWayEnum;
import top.auok.cbps.ts.core.enums.PublicEnum;
import top.auok.cbps.ts.core.enums.PublicStatusEnum;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.core.utils.StringUtil;
import top.auok.cbps.ts.user.dao.RpPayWayDao;
import top.auok.cbps.ts.user.entity.RpPayProduct;
import top.auok.cbps.ts.user.entity.RpPayWay;
import top.auok.cbps.ts.user.exception.PayBizException;
import top.auok.cbps.ts.user.service.RpPayProductService;
import top.auok.cbps.ts.user.service.RpPayWayService;

/**
 * 支付方式service实现类
 */
@Service("rpPayWayService")
public class RpPayWayServiceImpl implements RpPayWayService{

	@Autowired
	private RpPayWayDao rpPayWayDao;
	
	@Autowired
	private RpPayProductService rpPayProductService;
	
	@Override
	public void saveData(RpPayWay rpPayWay) {
		rpPayWayDao.insert(rpPayWay);
	}

	@Override
	public void updateData(RpPayWay rpPayWay) {
		rpPayWayDao.update(rpPayWay);
	}

	@Override
	public RpPayWay getDataById(String id) {
		return rpPayWayDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpPayWay rpPayWay) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("payProductCode", rpPayWay.getPayProductCode());
		paramMap.put("payWayName", rpPayWay.getPayWayName());
		paramMap.put("payTypeName", rpPayWay.getPayTypeName());
		return rpPayWayDao.listPage(pageParam, paramMap);
	}
	
	@Override
	public RpPayWay getByPayWayTypeCode(String payProductCode, String payWayCode, String payTypeCode){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payProductCode", payProductCode);
		paramMap.put("payTypeCode", payTypeCode);
		paramMap.put("payWayCode", payWayCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpPayWayDao.getBy(paramMap);
	}
	
	/**
	 * 绑定支付费率
	 * @param payWayCode
	 * @param payTypeCode
	 * @param payRate
	 */
	@Override
	public void createPayWay(String payProductCode, String payWayCode, String payTypeCode, Double payRate) throws PayBizException {
		RpPayWay payWay = getByPayWayTypeCode(payProductCode,payWayCode,payTypeCode);
		if(payWay!=null){
			throw new PayBizException(PayBizException.PAY_TYPE_IS_EXIST,"支付渠道已存在");
		}
		
		RpPayProduct rpPayProduct = rpPayProductService.getByProductCode(payProductCode, null);
		if(rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"支付产品已生效，无法绑定！");
		}
		
		RpPayWay rpPayWay = new RpPayWay();
		rpPayWay.setPayProductCode(payProductCode);
		rpPayWay.setPayRate(payRate);
		rpPayWay.setPayWayCode(payWayCode);
		rpPayWay.setPayWayName(PayWayEnum.getEnum(payWayCode).getDesc());
		rpPayWay.setPayTypeCode(payTypeCode);
		rpPayWay.setPayTypeName(PayTypeEnum.getEnum(payTypeCode).getDesc());
		rpPayWay.setStatus(PublicStatusEnum.ACTIVE.name());
		rpPayWay.setCreateTime(new Date());
		rpPayWay.setId(StringUtil.get32UUID());
		saveData(rpPayWay);
	}
	
	/**
	 * 根据支付产品获取支付方式
	 */
	@Override
	public List<RpPayWay> listByProductCode(String payProductCode){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payProductCode", payProductCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpPayWayDao.listBy(paramMap);
	}
	
	/**
	 * 获取所有支付方式
	 */
	@Override
	public List<RpPayWay> listAll(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpPayWayDao.listBy(paramMap);
	}
}
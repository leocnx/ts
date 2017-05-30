package top.auok.cbps.ts.controller.pay;

import javax.servlet.http.HttpServletRequest;

import top.auok.cbps.ts.core.enums.SecurityRatingEnum;
import top.auok.cbps.ts.core.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import top.auok.cbps.ts.core.dwz.DWZ;
import top.auok.cbps.ts.core.dwz.DwzAjax;
import top.auok.cbps.ts.core.enums.PayWayEnum;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.user.entity.RpUserBankAccount;
import top.auok.cbps.ts.user.entity.RpUserInfo;
import top.auok.cbps.ts.user.entity.RpUserPayConfig;
import top.auok.cbps.ts.user.entity.RpUserPayInfo;
import top.auok.cbps.ts.user.enums.BankAccountTypeEnum;
import top.auok.cbps.ts.user.enums.BankCodeEnum;
import top.auok.cbps.ts.user.enums.CardTypeEnum;
import top.auok.cbps.ts.user.enums.FundInfoTypeEnum;
import top.auok.cbps.ts.user.service.RpUserBankAccountService;
import top.auok.cbps.ts.user.service.RpUserInfoService;
import top.auok.cbps.ts.user.service.RpUserPayConfigService;
import top.auok.cbps.ts.user.service.RpUserPayInfoService;

/**
 * 用户支付设置管理
 */
@Controller
@RequestMapping("/pay/config")
public class UserPayConfigController {
	
	
	@Autowired
	private RpUserPayConfigService rpUserPayConfigService;
	@Autowired
	private RpUserInfoService rpUserInfoService;
	@Autowired
	private RpUserPayInfoService rpUserPayInfoService;
	@Autowired
	private RpUserBankAccountService rpUserBankAccountService;


	/**
	 * 函数功能说明 ： 查询分页数据
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list", method ={RequestMethod.POST,RequestMethod.GET})
	public String list(RpUserPayConfig rpUserPayConfig, PageParam pageParam, Model model) {
		PageBean pageBean = rpUserPayConfigService.listPage(pageParam, rpUserPayConfig);
		model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpUserPayConfig", rpUserPayConfig);
		return "pay/config/list";
	}
	
	/**
	 * 函数功能说明 ：跳转添加
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:add")
	@RequestMapping(value = "/addUI", method = RequestMethod.GET)
	public String addUI(Model model) {
		model.addAttribute("FundInfoTypeEnums", FundInfoTypeEnum.toList());
		model.addAttribute("SecurityRatingEnum", SecurityRatingEnum.toList());
		return "pay/config/add";
	}
	
	/**
	 * 函数功能说明 ： 保存
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, Model model, RpUserPayConfig rpUserPayConfig, DwzAjax dwz) {
		String userNo = request.getParameter("user.userNo");
		String userName = request.getParameter("user.userName");
		String productCode = request.getParameter("product.productCode");
		String productName = request.getParameter("product.productName");
		String securityRating = request.getParameter("securityRating");
		String merchantServerIp = request.getParameter("merchantServerIp");
		String we_appId = request.getParameter("we_appId");
		String we_merchantId = request.getParameter("we_merchantId");
		String we_partnerKey = request.getParameter("we_partnerKey");
		String ali_partner = request.getParameter("ali_partner");
		String ali_key = request.getParameter("ali_key");
		String ali_sellerId = request.getParameter("ali_sellerId");
		String ali_appid = request.getParameter("ali_appid");
		String ali_rsaPrivateKey = request.getParameter("ali_rsaPrivateKey");
		String ali_rsaPublicKey = request.getParameter("ali_rsaPublicKey");

		// 如果是商户且安全等级是MD5+IP白名单 , 则 IP白名单不能为空
		if (SecurityRatingEnum.MD5_IP.name().equals(securityRating)) {
			if (StringUtil.isEmpty(merchantServerIp)) {
				dwz.setStatusCode(DWZ.ERROR);
				dwz.setMessage("商户IP白名单不能为空");
				model.addAttribute("dwz", dwz);
				return DWZ.AJAX_DONE;
			}
		}
		rpUserPayConfigService.createUserPayConfig(userNo, userName, productCode, productName, 
				rpUserPayConfig.getRiskDay(), rpUserPayConfig.getFundIntoType(), rpUserPayConfig.getIsAutoSett(), we_appId
				, we_merchantId, we_partnerKey, ali_partner, ali_sellerId, ali_key, ali_appid, ali_rsaPrivateKey, ali_rsaPublicKey , securityRating , merchantServerIp);
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage(DWZ.SUCCESS_MSG);
		model.addAttribute("dwz", dwz);
		return DWZ.AJAX_DONE;
	}

	/**
	 * 函数功能说明 ：跳转编辑
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:edit")
	@RequestMapping(value = "/editUI", method = RequestMethod.GET)
	public String editUI(Model model,@RequestParam("userNo") String userNo) {
		RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByUserNo(userNo,null);
		RpUserPayInfo wxUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.WEIXIN.name());
		RpUserPayInfo aliUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.ALIPAY.name());
		model.addAttribute("FundInfoTypeEnums", FundInfoTypeEnum.toList());
		model.addAttribute("rpUserPayConfig", rpUserPayConfig);
		model.addAttribute("wxUserPayInfo", wxUserPayInfo);
		model.addAttribute("aliUserPayInfo", aliUserPayInfo);
		model.addAttribute("SecurityRatingEnum", SecurityRatingEnum.toList());
		return "pay/config/edit";
	}
	
	/**
	 * 函数功能说明 ： 更新
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, Model model, RpUserPayConfig rpUserPayConfig,DwzAjax dwz) {
		String productCode = request.getParameter("product.productCode");
		String productName = request.getParameter("product.productName");
		String securityRating = request.getParameter("securityRating");
		String merchantServerIp = request.getParameter("merchantServerIp");
		String we_appId = request.getParameter("we_appId");
		String we_merchantId = request.getParameter("we_merchantId");
		String we_partnerKey = request.getParameter("we_partnerKey");
		String ali_partner = request.getParameter("ali_partner");
		String ali_key = request.getParameter("ali_key");
		String ali_sellerId = request.getParameter("ali_sellerId");
		String ali_appid = request.getParameter("ali_appid");
		String ali_rsaPrivateKey = request.getParameter("ali_rsaPrivateKey");
		String ali_rsaPublicKey = request.getParameter("ali_rsaPublicKey");

		// 如果是商户且安全等级是MD5+IP白名单 , 则 IP白名单不能为空
		if (SecurityRatingEnum.MD5_IP.name().equals(securityRating)) {
			if (StringUtil.isEmpty(merchantServerIp)) {
				dwz.setStatusCode(DWZ.ERROR);
				dwz.setMessage("商户IP白名单不能为空");
				model.addAttribute("dwz", dwz);
				return DWZ.AJAX_DONE;
			}
		}

		rpUserPayConfigService.updateUserPayConfig(rpUserPayConfig.getUserNo(), productCode, productName, 
				rpUserPayConfig.getRiskDay(), rpUserPayConfig.getFundIntoType(), rpUserPayConfig.getIsAutoSett(),
				we_appId, we_merchantId, we_partnerKey, ali_partner, ali_sellerId, ali_key, ali_appid, ali_rsaPrivateKey, ali_rsaPublicKey , securityRating , merchantServerIp);
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage(DWZ.SUCCESS_MSG);
		model.addAttribute("dwz", dwz);
		return DWZ.AJAX_DONE;
	}
	
	/**
	 * 函数功能说明 ： 删除
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:delete")
	@RequestMapping(value = "/delete", method ={RequestMethod.POST,RequestMethod.GET})
	public String delete(Model model, DwzAjax dwz, @RequestParam("userNo") String userNo) {
		rpUserPayConfigService.deleteUserPayConfig(userNo);
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage(DWZ.SUCCESS_MSG);
		model.addAttribute("dwz", dwz);
		return DWZ.AJAX_DONE;
	}
	
	/**
	 * 函数功能说明 ： 审核
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:add")
	@RequestMapping(value = "/audit", method ={RequestMethod.POST,RequestMethod.GET})
	public String audit(Model model, DwzAjax dwz, @RequestParam("userNo") String userNo
			, @RequestParam("auditStatus") String auditStatus) {
		rpUserPayConfigService.audit(userNo, auditStatus);
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage(DWZ.SUCCESS_MSG);
		model.addAttribute("dwz", dwz);
		return DWZ.AJAX_DONE;
	}
	
	/**
	 * 函数功能说明 ：跳转编辑银行卡信息
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:edit")
	@RequestMapping(value = "/editBankUI", method = RequestMethod.GET)
	public String editBankUI(Model model,@RequestParam("userNo") String userNo) {
		RpUserBankAccount rpUserBankAccount = rpUserBankAccountService.getByUserNo(userNo);
		RpUserInfo rpUserInfo = rpUserInfoService.getDataByMerchentNo(userNo);
		model.addAttribute("BankCodeEnums", BankCodeEnum.toList());
		model.addAttribute("BankAccountTypeEnums", BankAccountTypeEnum.toList());
		model.addAttribute("CardTypeEnums", CardTypeEnum.toList());
		model.addAttribute("rpUserBankAccount", rpUserBankAccount);
		model.addAttribute("rpUserInfo", rpUserInfo);
		return "pay/config/editBank";
	}
	
	/**
	 * 函数功能说明 ： 更新银行卡信息
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("pay:config:edit")
	@RequestMapping(value = "/editBank", method = RequestMethod.POST)
	public String editBank(Model model, RpUserBankAccount rpUserBankAccount,DwzAjax dwz) {
		rpUserBankAccountService.createOrUpdate(rpUserBankAccount);
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage(DWZ.SUCCESS_MSG);
		model.addAttribute("dwz", dwz);
		return DWZ.AJAX_DONE;
	}
}

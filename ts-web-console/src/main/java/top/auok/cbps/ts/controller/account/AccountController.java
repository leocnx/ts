package top.auok.cbps.ts.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.auok.cbps.ts.account.entity.RpAccount;
import top.auok.cbps.ts.account.entity.RpAccountHistory;
import top.auok.cbps.ts.account.service.RpAccountHistoryService;
import top.auok.cbps.ts.account.service.RpAccountService;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.user.entity.RpUserInfo;
import top.auok.cbps.ts.user.service.RpUserInfoService;

/**
 * 账户信息
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private RpAccountService rpAccountService;
	@Autowired
	private RpUserInfoService rpUserInfoService;
	@Autowired
	private RpAccountHistoryService rpAccountHistoryService;

	/**
	 * 函数功能说明 ： 查询账户信息
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list", method ={RequestMethod.POST,RequestMethod.GET})
	public String list(RpAccount rpAccount,PageParam pageParam, Model model) {
		PageBean pageBean = rpAccountService.listPage(pageParam, rpAccount);
		List<Object> recordList = pageBean.getRecordList();
		for(Object obj : recordList){
			RpAccount account = (RpAccount)obj;
			RpUserInfo userInfo = rpUserInfoService.getDataByMerchentNo(account.getUserNo());
			account.setUserName(userInfo.getUserName());
		}
		model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpAccount",rpAccount);
		return "account/list";
	}

	/**
	 * 函数功能说明 ： 查询账户历史信息
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/historyList", method ={RequestMethod.POST,RequestMethod.GET})
	public String historyList(RpAccountHistory rpAccountHistory,PageParam pageParam, Model model) {
		PageBean pageBean = rpAccountHistoryService.listPage(pageParam, rpAccountHistory);
		List<Object> recordList = pageBean.getRecordList();
		for(Object obj : recordList){
			RpAccountHistory history = (RpAccountHistory)obj;
			RpUserInfo userInfo = rpUserInfoService.getDataByMerchentNo(history.getUserNo());
			history.setUserName(userInfo.getUserName());
		}
		model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpAccountHistory",rpAccountHistory);
		return "account/historyList";
	}
}

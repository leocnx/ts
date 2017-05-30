package top.auok.cbps.ts.controller.trade;

import javax.servlet.http.HttpServletRequest;

import top.auok.cbps.ts.trade.vo.PaymentOrderQueryParam;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.auok.cbps.ts.core.enums.PayTypeEnum;
import top.auok.cbps.ts.core.enums.PayWayEnum;
import top.auok.cbps.ts.core.page.PageBean;
import top.auok.cbps.ts.core.page.PageParam;
import top.auok.cbps.ts.trade.enums.TradeStatusEnum;
import top.auok.cbps.ts.trade.enums.TrxTypeEnum;
import top.auok.cbps.ts.trade.service.RpTradePaymentQueryService;
import top.auok.cbps.ts.user.enums.FundInfoTypeEnum;

/**
 * 交易管理
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private RpTradePaymentQueryService rpTradePaymentQueryService;
    @RequiresPermissions("trade:order:view")
    @RequestMapping(value = "/listPaymentOrder", method ={RequestMethod.POST,RequestMethod.GET})
    public String listPaymentOrder(HttpServletRequest request,PaymentOrderQueryParam paymentOrderQueryParam,PageParam pageParam, Model model) {
        PageBean pageBean = rpTradePaymentQueryService.listPaymentOrderPage(pageParam, paymentOrderQueryParam);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("paymentOrderQueryParam", paymentOrderQueryParam);//查询条件

        model.addAttribute("statusEnums", TradeStatusEnum.toMap());//状态
        model.addAttribute("payWayNameEnums", PayWayEnum.toMap());//支付方式
        model.addAttribute("payTypeNameEnums", PayTypeEnum.toMap());//支付类型
        model.addAttribute("fundIntoTypeEnums", FundInfoTypeEnum.toMap());//支付类型

        return "trade/listPaymentOrder";
    }

    @RequiresPermissions("trade:record:view")
    @RequestMapping(value = "/listPaymentRecord", method ={RequestMethod.POST,RequestMethod.GET})
    public String listPaymentRecord(HttpServletRequest request,PaymentOrderQueryParam paymentOrderQueryParam,PageParam pageParam, Model model) {
        PageBean pageBean = rpTradePaymentQueryService.listPaymentRecordPage(pageParam, paymentOrderQueryParam);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("paymentOrderQueryParam", paymentOrderQueryParam);

        model.addAttribute("statusEnums", TradeStatusEnum.toMap());//状态
        model.addAttribute("payWayNameEnums", PayWayEnum.toMap());//支付方式
        model.addAttribute("payTypeNameEnums", PayTypeEnum.toMap());//支付类型
        model.addAttribute("fundIntoTypeEnums", FundInfoTypeEnum.toMap());//支付类型
        model.addAttribute("trxTypeEnums", TrxTypeEnum.toMap());//支付类型
        return "trade/listPaymentRecord";
    }
}

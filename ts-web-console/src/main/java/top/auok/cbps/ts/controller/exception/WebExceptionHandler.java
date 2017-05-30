package top.auok.cbps.ts.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import top.auok.cbps.ts.core.dwz.DWZ;
import top.auok.cbps.ts.core.dwz.DwzAjax;
import top.auok.cbps.ts.core.exception.BizException;
import top.auok.cbps.ts.permission.exception.PermissionException;

/**
 * Spring异常拦截器.
 */
@ControllerAdvice
public class WebExceptionHandler {

	private static final Log LOG = LogFactory.getLog(WebExceptionHandler.class);

	/**
	 * shiro权限 异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processUnauthorizedException(HttpServletRequest request, UnauthorizedException e) {
		LOG.error("UnauthorizedException", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage("你没有操作权限");
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

	/**
	 * 业务异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ BizException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processBizException(HttpServletRequest request, BizException e) {
		LOG.error("BizException", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage(e.getMsg());
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

	/**
	 * 总异常
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(Exception e, HttpServletRequest request) {
		LOG.error("Exception", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage("系统异常");
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

}

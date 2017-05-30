package top.auok.cbps.ts.reconciliation.fileDown.service;

import java.io.File;
import java.util.Date;

/**
 * 该接口是用来对外开放的银行相关业务接口
 */
public interface ReconciliationFactory {

	/**
	 * 对账文件下载
	 * 
	 * @param payInterface
	 * @param fileDate
	 * @return
	 */
	File fileDown(String payInterface, Date billDate) throws Exception;

}

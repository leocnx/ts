package top.auok.cbps.ts.reconciliation.fileDown.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

import top.auok.cbps.ts.reconciliation.fileDown.service.FileDown;
import top.auok.cbps.ts.reconciliation.fileDown.service.ReconciliationFactory;
import top.auok.cbps.ts.reconciliation.utils.ReconciliationConfigUtil;

/**
 * 文件下载factory
 */
@Service("reconciliationFactory")
public class ReconciliationFactoryImpl implements ReconciliationFactory, BeanFactoryAware {

	private BeanFactory beanFactory;

	/**
	 * 去Spring容器中根据beanName获取对象（也可以直接根据名字创建实例，可以参考后面流程中的parser）
	 * 
	 * @param payInterface
	 * @return
	 */
	public Object getService(String payInterface) {
		return beanFactory.getBean(payInterface);
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * 账单下载
	 * 
	 * @param payInterface
	 *            支付渠道
	 * 
	 * @param billDate
	 *            账单日
	 */
	public File fileDown(String payInterface, Date billDate) throws Exception {
		// 找到具体的FileDown实现，做向上转型
		FileDown fileDown = (FileDown) this.getService(payInterface);
		// 加载配置文件，获取下载的对账文件保存路径
		String dir = ReconciliationConfigUtil.readConfig("dir") + payInterface.toLowerCase();
		return fileDown.fileDown(billDate, dir);
	}

}

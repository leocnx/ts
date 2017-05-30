package top.auok.cbps.ts.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AutoLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 4462570118068722600L;

	@Override
	public void init() throws ServletException {
		System.out.println("ts starting ...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(ClassLoader.getSystemResource("log4j.properties"));
		System.out.println(ClassLoader.getSystemResource("log4j.xml"));
		
//		org.apache.log4j.LogManager.resetConfiguration();
//	    org.apache.log4j.PropertyConfigurator.configure("WEB-INF\\classes\\log4j.properties");
		super.init();
	}

}

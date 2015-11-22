package org.occ.csp.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;

import base.util.BaseLogger;

public class InitializeListener implements ServletContextListener {
	private Logger logger = new BaseLogger(getClass());
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//TODO some global web and spring integration code here. 
		logger.info("-----------------------------------------------------------------------");
		logger.info("   CSP-Server(Church Service Platform - Server) initialized.    ");
		logger.info("-----------------------------------------------------------------------");
		
	}

}

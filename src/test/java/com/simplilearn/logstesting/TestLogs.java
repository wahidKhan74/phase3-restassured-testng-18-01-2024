package com.simplilearn.logstesting;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TestLogs {

	static final Logger logger = Logger.getLogger(TestLogs.class);
	
	@Test
	public void testInfoLogs() {
		logger.info("This is info logs.");
	}
	
	@Test
	public void testWarnLogs() {
		logger.warn("This is warn logs.");
	}
	
	@Test
	public void testDebugLogs() {
		logger.debug("This is debug logs.");
	}
	
	@Test
	public void testErrorLogs() {
		logger.error("This is error logs.");
	}
}

package com.qtz.sm.common.service.impl;


import org.springframework.stereotype.Component;


import cache.BaseProperties;


public class SystemInitServiceImpl {
	
	public SystemInitServiceImpl() {
		BaseProperties.writePidToFile();
		BaseProperties.initMap("config/base.properties");
	}
	
}

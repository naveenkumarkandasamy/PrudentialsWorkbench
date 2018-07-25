package com.activiti.pru;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AcceptanceService implements JavaDelegate{

    
	private static final Logger logger = LogManager.getLogger(AcceptanceService.class);
	public void execute(DelegateExecution execution){
		 logger.error("Doing AcceptanceService ...");		
	}

	

}

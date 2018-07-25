package com.activiti.pru;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TaskListener implements org.activiti.engine.delegate.TaskListener {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger logger = LogManager.getLogger(TaskListener.class);

	public void notify(DelegateTask delegateTask) {
		logger.error("Doing TaskListener ..."+delegateTask.getVariableNames());		
		
	}

	

}

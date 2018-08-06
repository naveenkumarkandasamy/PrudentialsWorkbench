package com.activiti.pru;

import java.util.List;
import java.util.logging.Logger;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.IdentityLinkType;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class NotificationListener implements TaskListener {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(NotificationListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		TaskEntity task = (TaskEntity) delegateTask;
		String assignee = task.getAssignee();
		List<IdentityLinkEntity> identityLinks = task.getIdentityLinks();

		if (assignee != null && assignee.length() > 0) {
			User user = Context.getProcessEngineConfiguration().getIdentityService().createUserQuery().userId(assignee)
					.singleResult();
			sendNotification(user,task.getName());
		}

		for (IdentityLinkEntity link : identityLinks) {
			if (link.getType().equals(IdentityLinkType.CANDIDATE)) {
				if (link.isUser()) {
					User user = Context.getProcessEngineConfiguration().getIdentityService().createUserQuery()
							.userId(link.getUserId()).singleResult();
					sendNotification(user,task.getName());
				}
				if (link.isGroup()) {
					List<User> users = Context.getProcessEngineConfiguration().getIdentityService().createUserQuery()
							.memberOfGroup(link.getGroupId()).list();
					for (User user : users) {
						sendNotification(user,task.getName());
					}
				}
			}
		}

	}

	protected void sendNotification(User user,String taskName) {

		HtmlEmail email = new HtmlEmail();
		StringBuffer message = new StringBuffer("");
		message.append("Hi ");
		message.append(user.getFirstName()+" "+user.getLastName()+" ,");
		message.append("<br/><br/>");
		message.append("A Task has been assigned to you in Work Bench. Please click the below link to complete the task. <br/><br/>");
		message.append("<a href=\"http://localhost:8080/workbench-app/workflow/#/tasks/");
		message.append("\">");
		message.append(taskName);
		message.append("</a>");
		try {
			
			email.setHtmlMsg(message.toString());
			email.addTo(user.getEmail());
			email.setSubject("Workbench Assignment Notification");
			email.setFrom("pruafrica@gmail.com");
			setMailServerProperties(email);

			email.send();
		} catch (EmailException e) {
			throw new ActivitiException("Could not send e-mail:" + e.getMessage(), e);
		}
	}

	protected void setMailServerProperties(Email email) {
		ProcessEngineConfigurationImpl processEngineConfiguration = Context.getProcessEngineConfiguration();

		String host = processEngineConfiguration.getMailServerHost();
		if (host == null) {
			throw new ActivitiException("Could not send email: no SMTP host is configured");
		}
		email.setTLS(processEngineConfiguration.getMailServerUseTLS());
		email.setSSL(processEngineConfiguration.getMailServerUseSSL());
		email.setHostName(host);

		int port = processEngineConfiguration.getMailServerPort();
		email.setSmtpPort(port);

		String user = processEngineConfiguration.getMailServerUsername();
		String password = processEngineConfiguration.getMailServerPassword();
		if (user != null && password != null) {
			email.setAuthentication(user, password);
		}
	}

}

package com.activiti.pru;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AcceptanceTaskListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(AcceptanceTaskListener.class);

	static Properties prop = new Properties();
	InputStream input = null;
	public void notify(DelegateExecution execution){
		logger.error("Doing AcceptanceTaskListener ..." + execution.getVariables());
		String policyNumber=(String) execution.getVariable("chdrsel");
		String date=new SoapEnquiryRequest().sendSoapEnquiryRequest("http://10.163.177.100:9081/LIFEWebSrv/NBSService",policyNumber);
		sendSoapAcceptanceRequest("http://10.163.177.100:9081/LiFEWebServices/NBSService",execution,date,policyNumber);
	}
	
	
	
	
	public void sendSoapAcceptanceRequest(String url,DelegateExecution execution,String dateTime,String policyNumber) {
		try {
			try {
				
				input = this.getClass().getResourceAsStream("/META-INF/activiti-app/activiti-app.properties");
				prop.load(input);
				SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
				SOAPConnection connection = sfc.createConnection();

				MessageFactory mf = MessageFactory.newInstance();
				SOAPMessage sm = mf.createMessage();

				SOAPEnvelope env = sm.getSOAPPart().getEnvelope();
				env.addNamespaceDeclaration("nbs","http://www.csc.smart/bo/schemas/NBSISSUEI");
				env.addNamespaceDeclaration("msp", "http://www.csc.smart/msp/schemas/MSPContext");
				SOAPHeader sh = sm.getSOAPHeader();
				SOAPBody sb = sm.getSOAPBody();
				sh.detachNode();
				
				QName bodyName = env.createQName("NBSISSUEI_REC","nbs");
				SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
				QName bodyName1=env.createQName("MSPContext","msp");
				SOAPElement soapElement = bodyElement.addChildElement(bodyName1);
				QName bodyName2=env.createQName("UserId","msp");
				SOAPElement soapElement1 = soapElement.addChildElement(bodyName2);
				soapElement1.setValue( prop.getProperty("UserId"));
				QName bodyName3=env.createQName("UserPassword","msp");
				SOAPElement soapElement3 = soapElement.addChildElement(bodyName3);
				soapElement3.setValue( prop.getProperty("Password"));
				QName bodyName4=env.createQName("RequestParameters","msp");
				SOAPElement soapElement4 = soapElement.addChildElement(bodyName4);
				QName bodyName5=env.createQName("RequestParameter","msp");
				SOAPElement soapElement5 =soapElement4.addChildElement(bodyName5);
				soapElement5.setAttribute("name", prop.getProperty("AttributeKey"));
				soapElement5.setAttribute("value", prop.getProperty("AttributeValue"));
				QName qn = new QName("CHDRSEL");
				SOAPElement soapElement6 = bodyElement.addChildElement(qn);
				soapElement6.addTextNode(policyNumber);
				
				QName date = new QName("DATIME");
				SOAPElement bodyElement7 = bodyElement.addChildElement(date);
				QName datetime = new QName("CHDR_DATIME");
				SOAPElement quotation = bodyElement7.addChildElement(datetime);
				quotation.addTextNode(dateTime);

				logger.error("\n Soap Request:\n");
				sm.writeTo(System.out);
				System.out.println();

				URL endpoint = new URL(url);
				SOAPMessage response = connection.call(sm, endpoint);
				response.writeTo(System.out);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*public static void main(String[] args) {
		AcceptanceTaskListener t=new AcceptanceTaskListener();
		String date=new SoapEnquiryRequest().sendSoapEnquiryRequest("http://10.163.177.100:9081/LIFEWebSrv/NBSService","00002198");
		t.sendSoapAcceptanceRequest("http://10.163.177.100:9081/LiFEWebServices/NBSService",null,date);

	}*/
}

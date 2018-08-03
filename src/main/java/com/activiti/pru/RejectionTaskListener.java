package com.activiti.pru;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class RejectionTaskListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(RejectionTaskListener.class);
	Properties prop = new Properties();
	InputStream input = null;
	public void notify(DelegateExecution execution){
		logger.error("Doing RejectionTaskListener ..." + execution.getVariables());
		String policyNumber=(String) execution.getVariable("chdrsel");
		String date=new SoapEnquiryRequest().sendSoapEnquiryRequest("http://10.163.177.100:9081/LIFEWebSrv/NBSService",policyNumber);
		sendSoapRejectionRequest("http://10.163.177.100:9081/LIFEWebSrv/PRPService",execution,date,policyNumber);

	}

	public void sendSoapRejectionRequest(String url,DelegateExecution execution,String dateTime,String policyNumber) {
		try {
			input = this.getClass().getResourceAsStream("/META-INF/activiti-app/activiti-app.properties");
			prop.load(input);
			SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = sfc.createConnection();

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage sm = mf.createMessage();

			SOAPEnvelope env = sm.getSOAPPart().getEnvelope();
			env.addNamespaceDeclaration("prp", "http://www.csc.smart/bo/schemas/PRPDECI");
			env.addNamespaceDeclaration("msp", "http://www.csc.smart/msp/schemas/MSPContext");
			SOAPHeader sh = sm.getSOAPHeader();
			SOAPBody sb = sm.getSOAPBody();
			sh.detachNode();
			
			QName bodyName = env.createQName("PRPDECI_REC","prp");
			SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
			QName bodyName1=env.createQName("MSPContext","msp");
			SOAPElement soapElement = bodyElement.addChildElement(bodyName1);
			QName bodyName2=env.createQName("UserId","msp");
			SOAPElement soapElement1 = soapElement.addChildElement(bodyName2);
			soapElement1.setValue(prop.getProperty("UserId"));
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
			QName reasoncd = new QName("REASONCD");
			SOAPElement soapElement7 = bodyElement.addChildElement(reasoncd);
			soapElement7.addTextNode(prop.getProperty("REASONCD"));
			QName resndesc = new QName("RESNDESC");
			SOAPElement soapElement8 = bodyElement.addChildElement(resndesc);
			soapElement8.addTextNode(prop.getProperty("RESNDESC"));
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
		RejectionTaskListener t=new RejectionTaskListener();
		String date=new SoapEnquiryRequest().sendSoapEnquiryRequest("http://10.163.177.100:9081/LIFEWebSrv/NBSService","00002290");
		t.sendSoapRejectionRequest("http://10.163.177.100:9081/LIFEWebSrv/PRPService",null,date);

	}*/
}

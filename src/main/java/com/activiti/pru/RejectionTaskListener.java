package com.activiti.pru;

import java.net.URL;

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

	public void notify(DelegateExecution execution){
		logger.error("Doing RejectionTaskListener ..." + execution.getVariables());
		sendSoapRejectionRequest("http://20.138.253.26:7688/PRPWebService/PRPService",execution);

	}

	public void sendSoapRejectionRequest(String url,DelegateExecution execution) {
		try {
			SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = sfc.createConnection();

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage sm = mf.createMessage();

			SOAPEnvelope env = sm.getSOAPPart().getEnvelope();
			env.addNamespaceDeclaration("pay", "http://www.csc.smart/bo/schemas/PAYDELI");
			env.addNamespaceDeclaration("msp", "http://www.csc.smart/msp/schemas/MSPContext");
			SOAPHeader sh = sm.getSOAPHeader();
			SOAPBody sb = sm.getSOAPBody();
			sh.detachNode();
			
			QName bodyName = env.createQName("PAYDELI_REC","pay");
			SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
			QName bodyName1=env.createQName("MSPContext","msp");
			SOAPElement soapElement = bodyElement.addChildElement(bodyName1);
			QName bodyName2=env.createQName("UserId","msp");
			SOAPElement soapElement1 = soapElement.addChildElement(bodyName2);
			soapElement1.setValue("TCHAN");
			QName bodyName3=env.createQName("UserPassword","msp");
			SOAPElement soapElement3 = soapElement.addChildElement(bodyName3);
			QName bodyName4=env.createQName("RequestParameters","msp");
			SOAPElement soapElement4 = soapElement.addChildElement(bodyName4);
			QName bodyName5=env.createQName("RequestParameter","msp");
			SOAPElement soapElement5 =soapElement4.addChildElement(bodyName5);
			soapElement5.setAttribute("name", "USRTYPE");
			soapElement5.setAttribute("value", "C");
			QName qn = new QName("PAYDEL_CHDRNUM");
			SOAPElement soapElement6 = bodyElement.addChildElement(qn);
			soapElement6.addTextNode((String) execution.getVariable("chdrsel"));
			logger.error("\n Soap Request:\n");
			sm.writeTo(System.out);
			System.out.println();

			URL endpoint = new URL(url);
			SOAPMessage response = connection.call(sm, endpoint);
			logger.error("Response is :"+response.getSOAPPart().getEnvelope().getBody().getFault().getFaultCode());
			response.writeTo(System.out);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getLocalizedMessage());
		}
	}

}

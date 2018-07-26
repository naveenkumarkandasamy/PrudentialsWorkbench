package com.activiti.pru;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public void notify(DelegateExecution execution){
		logger.error("Doing AcceptanceTaskListener ..." + execution.getVariables());
		sendSoapAcceptanceRequest("http://20.138.253.26:7688/PRPWebService/PRPService/WEB-INF/wsdl/PRPService.wsdl",execution);

	}

	public void sendSoapAcceptanceRequest(String url,DelegateExecution execution) {
		try {
			try {
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
				soapElement1.setValue("Nitin Bhasin");
				QName bodyName3=env.createQName("UserPassword","msp");
				SOAPElement soapElement3 = soapElement.addChildElement(bodyName3);
				QName bodyName4=env.createQName("RequestParameters","msp");
				SOAPElement soapElement4 = soapElement.addChildElement(bodyName4);
				QName bodyName5=env.createQName("RequestParameter","msp");
				SOAPElement soapElement5 =soapElement4.addChildElement(bodyName5);
				soapElement5.setAttribute("name", "USRTYPE");
				soapElement5.setAttribute("value", "C");
				QName qn = new QName("CHDRSEL");
				SOAPElement soapElement6 = bodyElement.addChildElement(qn);
				soapElement6.addTextNode((String) execution.getVariable("chdrsel"));
				
				QName date = new QName("Date");
				SOAPElement bodyElement7 = bodyElement.addChildElement(date);
				QName datetime = new QName("CHDR_DATIME");
				SOAPElement quotation = bodyElement7.addChildElement(datetime);
				DateFormat formatter1;
				formatter1 = new SimpleDateFormat("yyyy-mm-dd-hh.mm.ss.SSSSSS");
				quotation.addTextNode(formatter1.format(new Date() ));

				logger.error("\n Soap Request:\n");
				sm.writeTo(System.out);
				System.out.println();

				URL endpoint = new URL(url);
				SOAPMessage response = connection.call(sm, endpoint);
				logger.error(response.getContentDescription());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
	}

	/*public static void main(String[] args) {
		AcceptanceTaskListener t=new AcceptanceTaskListener();
		t.sendSoapAcceptanceRequest(null,null);
	}*/
}

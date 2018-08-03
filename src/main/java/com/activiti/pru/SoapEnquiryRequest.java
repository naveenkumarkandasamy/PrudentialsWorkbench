package com.activiti.pru;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;



public class SoapEnquiryRequest {

	
	private static final Logger logger = LogManager.getLogger(AcceptanceTaskListener.class);

	 Properties prop = new Properties();
	 InputStream input = null;
	public  String sendSoapEnquiryRequest(String url,String policyNum)
	{
		try {
			
			input = this.getClass().getResourceAsStream("/META-INF/activiti-app/activiti-app.properties");
			prop.load(input);
			
			SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = sfc.createConnection();

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage sm = mf.createMessage();
			SOAPEnvelope env = sm.getSOAPPart().getEnvelope();
			env.addNamespaceDeclaration("nbs","http://www.csc.smart/bo/schemas/NBSENQI");
			env.addNamespaceDeclaration("msp", "http://www.csc.smart/msp/schemas/MSPContext");
			SOAPHeader sh = sm.getSOAPHeader();
			SOAPBody sb = sm.getSOAPBody();
			sh.detachNode();
			
			QName bodyName = env.createQName("NBSENQI_REC","nbs");
			SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
			QName bodyName1=env.createQName("MSPContext","msp");
			SOAPElement soapElement = bodyElement.addChildElement(bodyName1);
			QName bodyName2=env.createQName("UserId","msp");
			SOAPElement soapElement1 = soapElement.addChildElement(bodyName2);
			soapElement1.setValue(prop.getProperty("UserId"));
			QName bodyName3=env.createQName("UserPassword","msp");
			SOAPElement soapElement3 = soapElement.addChildElement(bodyName3);
			soapElement3.setValue(prop.getProperty("Password"));
			QName bodyName4=env.createQName("RequestParameters","msp");
			SOAPElement soapElement4 = soapElement.addChildElement(bodyName4);
			QName bodyName5=env.createQName("RequestParameter","msp");
			SOAPElement soapElement5 =soapElement4.addChildElement(bodyName5);
			soapElement5.setAttribute("name", prop.getProperty("AttributeKey"));
			soapElement5.setAttribute("value", prop.getProperty("AttributeValue"));
			QName qn = new QName("CHDRNUM");
			SOAPElement soapElement6 = bodyElement.addChildElement(qn);
			soapElement6.addTextNode(policyNum);


			logger.error("\n Soap Request:\n");
			sm.writeTo(System.out);
			System.out.println();

			URL endpoint = new URL(url);
			SOAPMessage response = connection.call(sm, endpoint);
			//response.writeTo(System.out);
			logger.info("Response Is"+response);
			//body = response.getSOAPBody();
            Iterator updates = response.getSOAPBody().getChildElements();
            while (updates.hasNext()) {
                System.out.println();
                // The listing and its ID
                SOAPElement update = (SOAPElement)updates.next();
                String status = update.getAttribute("id");
                logger.info("Status: "+status );
                
                QName name=new QName("CONTRACT_HEADER_DETAILS");
                Iterator i = update.getChildElements(name);
                while(i.hasNext())
                {
                	SOAPElement e = (SOAPElement)i.next();
                	if(e.hasChildNodes())
                	{
                		QName dateTimeStamp=new QName("dateTimeStamp");
                		Iterator it = e.getChildElements(dateTimeStamp);
                        while(it.hasNext())
                        {
                        	ElementImpl date = (ElementImpl)it.next();
                        	System.out.println(date.getValue());
                        	return date.getValue();
                        }
                	}
                }
		
            }
		}catch(Exception e)
		{
			e.printStackTrace();
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
		return null;
	}
}

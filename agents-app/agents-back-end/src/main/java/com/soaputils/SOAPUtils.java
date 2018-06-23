package com.soaputils;

import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
@Transactional
public class SOAPUtils {
	public static void createSoapEnvelope(SOAPMessage soapMessage, String request) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //String myNamespace = "myns";
        //String myNamespaceURI = "model";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:GetInfoByCity>
                        <myNamespace:USCity>New York</myNamespace:USCity>
                    </myNamespace:GetInfoByCity>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        if(request!=null)
        	soapBody.addDocument(convertStringToDocument(request));
        //SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", myNamespace);
        //soapBodyElem1.addTextNode("Sam");
    }
	

	
	public static SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, String request) {
		SOAPMessage soapResponse = null;
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            System.out.println("U call Action je: " + soapAction);
            //System.out.println("U call Request je: " + request);
            System.out.println("U call SoapEndPoint je: " + soapEndpointUrl);
            soapResponse = soapConnection.call(createSOAPRequest(soapAction, request), soapEndpointUrl);

            // Print the SOAP Response
            //System.out.println("Response SOAP Message:" + soapResponse);
            //soapResponse.writeTo(System.out);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapResponse.writeTo(baos);
            
            System.out.println(prettyFormat(new String(baos.toByteArray(),  java.nio.charset.StandardCharsets.UTF_8)));

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        
        return soapResponse;
    }

	public static SOAPMessage createSOAPRequest(String soapAction, String request) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage, request);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("*************************************************************************************");
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        soapMessage.writeTo(baos);
        System.out.println(prettyFormat(new String(baos.toByteArray(),  java.nio.charset.StandardCharsets.UTF_8)));
        System.out.println("*************************************************************************************");
        System.out.println("\n");

        return soapMessage;
    }
	
	private static Document convertStringToDocument(String xmlStr) {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder;
	    try {
	        builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
	        return doc;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static String prettyFormat(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}

	public static String prettyFormat(String input) {
	    return prettyFormat(input, 4);
	}
}


package net.codejava.ws.binary.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ImageWebServiceImpl", targetNamespace = "http://impl.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ImageWebServiceImpl {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "upload", targetNamespace = "http://impl.com/", className = "net.codejava.ws.binary.client.Upload")
    @ResponseWrapper(localName = "uploadResponse", targetNamespace = "http://impl.com/", className = "net.codejava.ws.binary.client.UploadResponse")
    @Action(input = "http://impl.com/ImageWebServiceImpl/uploadRequest", output = "http://impl.com/ImageWebServiceImpl/uploadResponse")
    public void upload(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        byte[] arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "download", targetNamespace = "http://impl.com/", className = "net.codejava.ws.binary.client.Download")
    @ResponseWrapper(localName = "downloadResponse", targetNamespace = "http://impl.com/", className = "net.codejava.ws.binary.client.DownloadResponse")
    @Action(input = "http://impl.com/ImageWebServiceImpl/downloadRequest", output = "http://impl.com/ImageWebServiceImpl/downloadResponse")
    public byte[] download(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}

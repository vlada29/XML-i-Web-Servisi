package com.soapservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ImageWebService {
    @WebMethod
    public void upload(String fileName, byte[] imageBytes);
     
    @WebMethod
    public byte[] download(String fileName);   
}

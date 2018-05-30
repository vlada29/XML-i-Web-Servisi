package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.model.DodatnaUsluga;
import com.model.KategorijaSmestaja;
import com.model.TipSmestaja;
import com.repositories.DodatneuslugeRepository;
import com.repositories.KategorijasmestajaRepository;
import com.repositories.TipsmestajaRepository;

@RestController
public class CatalogController {
	
	@Autowired
	TipsmestajaRepository tipRep;
	
	@Autowired
	KategorijasmestajaRepository katRep;
	
	@Autowired
	DodatneuslugeRepository dodRep;
	
	@GetMapping("/getTipovi")
	public String getTipovi() {
		String ret = "[";
		ArrayList<TipSmestaja> a = tipRep.findAll();
		Gson g = new Gson();
		for(int i=0;i<a.size();i++) {
			if(i!=0) {
				ret+=",";
			}
			ret+=g.toJson(a.get(i));
		}
		ret +="]";	
		return ret;
	}
	
	@GetMapping("/getKategorije")
	public String getKategorije() {
		String ret = "[";
		ArrayList<KategorijaSmestaja> a = katRep.findAll();
		Gson g = new Gson();
		for(int i=0;i<a.size();i++) {
			if(i!=0) {
				ret+=",";
			}
			ret+=g.toJson(a.get(i));
		}
		ret+="]";
		return ret;
	}
	
	@GetMapping("/getDodatneUsluge")
	public String getDodatneUsluge() {
		String ret = "[";
		ArrayList<DodatnaUsluga> a = dodRep.findAll();
		Gson g = new Gson();
		for(int i=0;i<a.size();i++) {
			if(i!=0) {
				ret+=",";
			}
			ret+=g.toJson(a.get(i));
		}
		ret+="]";
		return ret;
	}
	
	@RequestMapping(
			value = "/obrisiTip",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void obrisiTip(@RequestBody TipSmestaja tip, HttpServletResponse response,HttpSession session) throws IOException {
		try {
			tipRep.delete(tip);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value="/obrisiKategoriju",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void obrisiKategoriju(@RequestBody KategorijaSmestaja kategorija,HttpServletResponse response,HttpSession session) throws IOException {
		try {
			katRep.delete(kategorija);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value="/obrisiUslugu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void obrisiUslugu(@RequestBody DodatnaUsluga usluga,HttpServletResponse response,HttpSession session) throws IOException {
		try {
			dodRep.delete(usluga);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/snimiTip",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void snimiTip(@RequestBody TipSmestaja tip, HttpServletResponse response,HttpSession session) throws IOException {
		try {
			tipRep.save(tip);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value="/snimiKategoriju",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void snimiKategoriju(@RequestBody KategorijaSmestaja kategorija,HttpServletResponse response,HttpSession session) throws IOException {
		try {
			katRep.save(kategorija);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value="/snimiUslugu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void snimiUslugu(@RequestBody DodatnaUsluga usluga,HttpServletResponse response,HttpSession session) throws IOException {
		try {
			dodRep.save(usluga);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
}

package com.controllers;
import static com.mongodb.client.model.Filters.eq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

import org.bson.Document;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.model.Agent;
import com.model.DodatnaUsluga;
import com.model.KategorijaSmestaja;
import com.model.Message;
import com.model.PlanCena;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.SmestajnaJedinicaPictureItem;
import com.model.TipSmestaja;
import com.model.ZauzetostJedinice;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.repositories.CategoryRepository;
import com.repositories.ExtrasRepository;
import com.repositories.TypesRepository;
import com.services.AgentService;
import com.services.AvailableService;
import com.services.MessageService;
import com.services.RezService;
import com.services.UnitService;
import com.soapservices.CloudSynchronisationService;
import com.soapservices.SoapActionsService;
@RestController
public class MainController {
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	CloudSynchronisationService syncService;
	
	@Autowired
	RezService rezService;
	
	@Autowired
	AgentService aService;
	
	@Autowired
	SoapActionsService soapService;
	
	@Autowired
	AvailableService avaService;
	
	@Autowired
	MessageService messService;
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	ExtrasRepository extrasRepo;
	
	@Autowired
	TypesRepository typesRepo;
	
	@RequestMapping(
			value = "/createNewUnit/{username}", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public String createNewUnit(@RequestBody SmestajnaJedinica unit, @PathVariable String username) throws SOAPException, JAXBException, IOException, JSONException{
		 
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(mapper.writeValueAsString(unit));
		String prettyJsonString = gson.toJson(je);
		System.out.println("New unit = " + prettyJsonString);
		
		List<PlanCena> cene = unit.getCene();
		if(cene.size()>1) {
			for(PlanCena pc : unit.getCene()) {
				for(int i = 0; i<cene.size(); i++) {
					if(pc!=cene.get(i)) {
						if((( cene.get(i).getPocetakVazenjaItem().getTime() <= pc.getKrajVazenjaItem().getTime()) &&
								(cene.get(i).getKrajVazenjaItem().getTime()  >= pc.getPocetakVazenjaItem().getTime()))
								) {
							System.out.println("Price error");
							return null;
						}
					}
				}
			}
		}
		
		
		ArrayList<SmestajnaJedinica> units = new ArrayList<>();
		
		for(SmestajnaJedinica sj : unitService.findAll()){
			if(sj.getAgent()!=null){
				if(sj.getAgent().getUsername().equals(unit.getAgent().getUsername())){
					System.out.println(sj.getHjid());
					units.add(sj);
					
				}
			}
		}
		
		File fi;
		byte[] fileContent;
		for(String s : unit.getSlike()){
			System.out.println("Slika: " + s);
			String path = s.replaceAll("http://localhost:8090/files", "upload-dir");
			fi = new File(path);
			fileContent = Files.readAllBytes(fi.toPath());
			SmestajnaJedinicaPictureItem pi = new SmestajnaJedinicaPictureItem();
			pi.setItem(fileContent);
			unit.getPictureItems().add(pi);
		}
		
		soapService.createNewUnit(unit);
		
		ObjectMapper mapper2 = new ObjectMapper();
		   	   
	    String u = mapper2.writeValueAsString(units);
	    return u;
	}
	
	@RequestMapping(path = "/getMyUnits/{username}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String getMyUnits(@PathVariable String username)
			throws Exception {	
		ArrayList<SmestajnaJedinica> units = new ArrayList<>();
		
		for(SmestajnaJedinica sj : unitService.findAll()){
			
			if(sj.getAgent()!=null){
				
				if(sj.getAgent().getUsername().equals(username)){
					System.out.println(sj.getHjid());
					units.add(sj);
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		   	   
	    String u = mapper.writeValueAsString(units);
	    return u;
	}
	
	@RequestMapping(path = "/getResOfMyUnits/{username}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String getResOfMyUnits(@PathVariable String username)
			throws Exception {	
		ArrayList<Rezervacija> ress = new ArrayList<>();
		
		for(Rezervacija r : rezService.findAll()){
			
			if(r.getSmestajnaJedinica()!=null){
				System.out.println("username:" + r.getSmestajnaJedinica().getAgent().getUsername());
				if(r.getSmestajnaJedinica().getAgent().getUsername().equals(username)){
					System.out.println(r.getHjid());
					ress.add(r);
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		   	   
	    String u = mapper.writeValueAsString(ress);
	    System.out.println(username+"'s units reservations:");
	    System.out.println(u);
	    return u;
	}
	
	@RequestMapping(path = "/confirmArrival/{username}/{idRez}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String confirmArrival(@PathVariable String username, @PathVariable Long idRez)
			throws Exception {	
		
		//local
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase baza = mongoClient.getDatabase("test");
		MongoCollection<Document> resers = baza.getCollection("rezervacija");
		 
		for(Rezervacija r : rezService.findAll()){
			if(r.getHjid().equals(idRez)){
				if(r.isRealizovana()==false){
					resers.findOneAndUpdate(eq("hjid",idRez), Updates.set("realizovana", true));
				}
				
			}
		}
		
		//notify back-end database
		soapService.confirmArrival(username, idRez);
		
		ObjectMapper mapper = new ObjectMapper();
	    String u = mapper.writeValueAsString(rezService.findAll());
	    System.out.println(u);
	    mongoClient.close();
	    return u;
	}
	
	@RequestMapping(
			value = "/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public String login(@RequestBody Agent agent) throws SOAPException, JAXBException, IOException, JSONException{
		soapService.login(agent);
		
		if (aService.findByUsername(agent.getUsername()) != null){
			if(aService.findByUsername(agent.getUsername()).getPassword().equals(agent.getPassword())){
				ObjectMapper mapper = new ObjectMapper();
				String u = mapper.writeValueAsString(aService.findByUsername(agent.getUsername()));
				
				
				unitService.deleteAll();
				rezService.deleteAll();
				catRepo.deleteAll();
				typesRepo.deleteAll();
				extrasRepo.deleteAll();
				messService.deleteAll();
				avaService.deleteAll();
				
				syncService.syncroniseWithCloudWS(agent.getUsername());
				syncService.syncroniseWithCloudRes(agent.getUsername());
				syncService.syncroniseWithCloudCategories();
				syncService.syncroniseWithCloudExtras();
				syncService.syncroniseWithCloudMess(aService.findByUsername(agent.getUsername()).getHjid());
				syncService.syncroniseWithCloudTypes();
				System.out.println("Synchronising finished.");
				
				return u;
				
			} else {
				return null;
			}
		} else {
			System.out.println("Agent ne postoji!");
			return null;
		}
		
	}
	
	@RequestMapping(
			value = "/reserve", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public String reserve(@RequestBody Rezervacija zauzetost ) throws SOAPException, JAXBException, IOException, JSONException{
		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(mapper.writeValueAsString(zauzetost));
		String prettyJsonString = gson.toJson(je);
		System.out.println("Zauzetost = " + prettyJsonString);
		
 
		Rezervacija r = soapService.reserve(zauzetost.getHjid(), zauzetost);
		
		 
		if(r!=null) {
			rezService.save(r);
			return "{\"message\":\"OK\"}";
		}
		else {
			return null;
		}
	}
	
	
	
	@RequestMapping(
			value = "/sendMessage", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public String sendMessage(@RequestBody Message message) throws SOAPException, JAXBException, IOException, JSONException{
		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(mapper.writeValueAsString(message));
		String prettyJsonString = gson.toJson(je);
		System.out.println("Message = " + prettyJsonString);
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		message.setDatum(date);
		message.setDat(date.getTime());
		
		messService.save(message);
		
		soapService.sendMessage(message);
		
		return "{\"message\":\"OK\"}";
	}
	
	@RequestMapping(
			value = "/getMessages/{hjid}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Message> getMessages(@PathVariable Long hjid) throws SOAPException, JAXBException, IOException, JSONException{
		return messService.findByAgentHjid(hjid);
	}
	
	@RequestMapping(
			value = "/getCategories", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<KategorijaSmestaja> getCategories() throws SOAPException, JAXBException, IOException, JSONException{
		return catRepo.findAll();
	}
	
	@RequestMapping(
			value = "/getTypes", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<TipSmestaja> getTypes() throws SOAPException, JAXBException, IOException, JSONException{
		return typesRepo.findAll();
	}
	
	@RequestMapping(
			value = "/getExtras", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<DodatnaUsluga> getExtras() throws SOAPException, JAXBException, IOException, JSONException{
		return extrasRepo.findAll();
	}

}
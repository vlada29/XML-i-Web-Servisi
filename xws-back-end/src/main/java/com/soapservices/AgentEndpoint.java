package com.soapservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.model.Message;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.SmestajnaJedinicaPictureItem;
import com.model.ZauzetostJedinice;
import com.repositories.DodatneuslugeRepository;
import com.repositories.KategorijasmestajaRepository;
import com.repositories.TipsmestajaRepository;
import com.services.AvailabilityService;
import com.services.MessageService;
import com.services.RezService;
import com.services.UnitService;
import com.soapservices.soapenv.CatsWrapper;
import com.soapservices.soapenv.ConfirmArrival;
import com.soapservices.soapenv.ExtrasWrapper;
import com.soapservices.soapenv.MessWrapper;
import com.soapservices.soapenv.ResWrapper;
import com.soapservices.soapenv.TypesWrapper;
import com.soapservices.soapenv.UnitsWrapper;

@Endpoint
public class AgentEndpoint {
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private RezService rezService;
	
	@Autowired
	private AvailabilityService avaService;
	
	@Autowired
	private MessageService messService;
	
	@Autowired
	private KategorijasmestajaRepository catRepo;
	
	@Autowired
	private TipsmestajaRepository typesRepo;
	
	@Autowired 
	private DodatneuslugeRepository extrasRepo;
	
	@PayloadRoot(namespace="model", localPart = "Smestajna_Jedinica")
	@ResponsePayload
	public SmestajnaJedinica oneUnit(@RequestPayload SmestajnaJedinica request)  throws Exception {
		//azure
		List<SmestajnaJedinica> all = unitService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[AT THE MOMENT] "+request.getAgent().getUsername()+"'s Cloud DB Units:***");
		for(SmestajnaJedinica u : all){
			if(u.getAgent()!=null)
				if(u.getAgent().getUsername().equals(request.getAgent().getUsername()))
					System.out.println(u);
		}
		System.out.println("*******************************************");
		
		System.out.println("###########################################");
		System.out.println("### Unit Request Message: ###");
		System.out.println(request);
		SmestajnaJedinica savedUnit = unitService.save(request);
		System.out.println("###########################################");
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] "+request.getAgent().getUsername()+"'s Cloud DB Units:***");
	
		for(SmestajnaJedinica u : unitService.findAll()){
			if(u.getAgent()!=null)
				if(u.getAgent().getUsername().equals(request.getAgent().getUsername())){		
					System.out.println(u);
				}
		}
		
		
		return savedUnit;
	}
	
	@PayloadRoot(namespace="model", localPart = "Units_Wrapper")
	@ResponsePayload
	public UnitsWrapper unitsEndpoint(@RequestPayload UnitsWrapper request)  throws Exception {
		//azure
		List<SmestajnaJedinica> all = unitService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[AT THE MOMENT] "+request.getUsername()+"'s Cloud DB Units:***");
		for(SmestajnaJedinica u : all){
			if(u.getAgent()!=null)
				if(u.getAgent().getUsername().equals(request.getUsername()))
					System.out.println(u);
		}
		System.out.println("*******************************************");
		
		System.out.println("###########################################");
		System.out.println("### Units Wrapper Request Message: ###");
		for(SmestajnaJedinica unit : request.getUnits()){
			System.out.println(unit);
			unitService.save(unit);
		}
		System.out.println("###########################################");
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] "+request.getUsername()+"'s Cloud DB Units:***");
		ArrayList<SmestajnaJedinica> synced = new ArrayList<>();
		for(SmestajnaJedinica u : unitService.findAll()){
			if(u.getAgent()!=null)
				if(u.getAgent().getUsername().equals(request.getUsername())){
					synced.add(u);
					System.out.println(u);
				}
		}
		System.out.println("*******************************************");
		UnitsWrapper uw = new UnitsWrapper();
		uw.setUnits(synced);
		uw.setUsername(request.getUsername());
		
		return uw;
	}
	
	@PayloadRoot(namespace="model", localPart = "Res_Wrapper")
	@ResponsePayload
	public ResWrapper syncRes(@RequestPayload ResWrapper request)  throws Exception {
		//find all on azure
		List<Rezervacija> reservations = rezService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[AT THE MOMENT] "+request.getUsername()+"'s Cloud DB Reservations:***");
		for(Rezervacija r : reservations){
			if(r.getSmestajnaJedinica().getAgent()!=null)
				if(r.getSmestajnaJedinica().getAgent().getUsername().equals(request.getUsername()))
					System.out.println(r);
		}
		System.out.println("*******************************************");
		
		ResWrapper rw = new ResWrapper();
				
		System.out.println("###########################################");
		System.out.println("### Res Wrapper Request Message: ###");
		for(Rezervacija r : request.getReservations()){
			System.out.println(r);
			rezService.save(r);
		}
		System.out.println("###########################################");
		
		//azure db content after sync with locals
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] "+request.getUsername()+"'s Cloud DB Reservations:***");
		ArrayList<Rezervacija> synced = new ArrayList<>();
		for(Rezervacija r : rezService.findAll()){
			if(r.getSmestajnaJedinica().getAgent()!=null)
				if(r.getSmestajnaJedinica().getAgent().getUsername().equals(request.getUsername())){
					System.out.println(r);
					synced.add(r);
				}
		}
		System.out.println("*******************************************");
		rw.setUnits(synced);
		rw.setUsername(request.getUsername());
		
		return rw;
	}
	
	@PayloadRoot(namespace="model", localPart = "Mess_Wrapper")
	@ResponsePayload
	public MessWrapper syncRes(@RequestPayload MessWrapper request)  throws Exception {
		//find all on azure
		List<Message> messages = messService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[AT THE MOMENT] "+request.getMessages()+"'s Cloud DB Messages:***");
		for(Message r : messages){
			if(r.getAgent().getHjid().equals(request.getHjid()))
				System.out.println(r);
		}
		System.out.println("*******************************************");
		
		MessWrapper rw = new MessWrapper();
				
		System.out.println("###########################################");
		System.out.println("### Message Wrapper Request Message: ###");
		for(Message r : request.getMessages()){
			System.out.println(r);
			messService.save(r);
		}
		System.out.println("###########################################");
		
		//azure db content after sync with locals
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] "+request.getHjid()+"'s Cloud DB Messages:***");
		ArrayList<Message> synced = new ArrayList<>();
		for(Message r : messService.findAll()){
			if(r.getAgent().getHjid().equals(request.getHjid())){
				System.out.println(r);
				synced.add(r);	
			}
				
		}
		System.out.println("*******************************************");
		rw.setMessages(synced);
		rw.setHjid(request.getHjid());
		
		return rw;
	}
	
	@PayloadRoot(namespace="model", localPart = "Confirm_Arrival")
	@ResponsePayload
	public ConfirmArrival confirmArrival(@RequestPayload ConfirmArrival request)  throws Exception {
		for(Rezervacija r : rezService.findAll()){
			if(r.getHjid().equals(request.getHjid())){
				if(r.getSmestajnaJedinica().getAgent().getUsername().equals(request.getUsername())){
					rezService.confirmArrival(request.getHjid());
				}
			}
					
		}
		return request;
	}
	
	@PayloadRoot(namespace="model", localPart = "Zauzetost_Jedinice")
	@ResponsePayload
	public ZauzetostJedinice reserve(@RequestPayload ZauzetostJedinice request)  throws Exception {
		return avaService.save(request);
	}
	
	@PayloadRoot(namespace="model", localPart = "Smestajna_Jedinica_Picture_Item")
	@ResponsePayload
	public SmestajnaJedinicaPictureItem recieveImage(@RequestPayload SmestajnaJedinicaPictureItem request)  throws Exception {
		return null;
	}
	
	@PayloadRoot(namespace="model", localPart = "Message")
	@ResponsePayload
	public Message sendMessage(@RequestPayload Message message)  throws Exception {
		return messService.save(message);
	}
	
	@PayloadRoot(namespace="model", localPart = "Cats_Wrapper")
	@ResponsePayload
	public CatsWrapper getCategories(@RequestPayload CatsWrapper request)  throws Exception {
		CatsWrapper cw = new CatsWrapper();
		cw.setCategories(catRepo.findAll());
		return cw;
	}
	
	@PayloadRoot(namespace="model", localPart = "Types_Wrapper")
	@ResponsePayload
	public TypesWrapper getTypes(@RequestPayload TypesWrapper request)  throws Exception {
		TypesWrapper tw = new TypesWrapper();
		tw.setTypes(typesRepo.findAll());
		return tw;
	}
	
	@PayloadRoot(namespace="model", localPart = "Extras_Wrapper")
	@ResponsePayload
	public ExtrasWrapper getExtras(@RequestPayload ExtrasWrapper request)  throws Exception {
		ExtrasWrapper ew = new ExtrasWrapper();
		ew.setExtras(extrasRepo.findAll());
		return ew;
	}
}

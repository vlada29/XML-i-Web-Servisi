package com.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.services.CloudService;

@RestController
public class CommentController {

	@GetMapping("/getComments")
	public String getComments() { 
		System.out.println("pozvao get comments");
		CloudService cs = new CloudService();
		String ret = cs.getKomentariForAdmin((long) 1);
		if(ret == null){
			System.out.println("vratio gresku");
			return "[]";
		}
		System.out.println(ret);
		return ret;
		
	}
}

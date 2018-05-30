package com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.services.StorageService;


@Controller
public class UploadController {

	@Autowired
	StorageService storageService;

	List<String> files = new ArrayList<String>();

	@PostMapping("/post")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());

			message = "http://localhost:8090/files/"+file.getOriginalFilename();
			System.out.println("message: " + message);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) throws IOException {
		System.out.println("Getting images: ");
		List<String> alternative = new ArrayList<String>();
		try (Stream<Path> paths = Files.walk(Paths.get("upload-dir"))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach( f -> {
		        	System.out.println(f);
		        	alternative.add("http://localhost:8090/"+f.toString().replace("\\", "/").replaceAll("upload-dir", "files"));
		        });
		} 
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
		//System.out.println("Filenames: " + fileNames);
		//if(fileNames.size()==0){
			return ResponseEntity.ok().body(alternative);
		//} else {
		//	return ResponseEntity.ok().body(fileNames);
		//}
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
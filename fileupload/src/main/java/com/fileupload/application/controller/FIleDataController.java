package com.fileupload.application.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.application.model.FileData;
import com.fileupload.application.repository.FileDataRepository;
import com.fileupload.application.service.FileDataService;





@RestController
@RequestMapping("/file")

public class FIleDataController {
	@Autowired
	FileDataRepository fileDataRepository;
	@Autowired
	private FileDataService service;

	@PostMapping("/A")
	public ResponseEntity<FileData> uploadImageToFIleSystem( @RequestBody FileData fileData,MultipartFile file ) throws IOException {
	fileDataRepository.save(new FileData(fileData.getFirstName(),fileData.getLastName(),fileData.getDateofbirth(),fileData.getEmail()));
	return new ResponseEntity<FileData>(HttpStatus.CREATED);

	}
	@PutMapping("/A/{id}")
	public ResponseEntity<?> uploadImage(@PathVariable("id")long id,@RequestParam("file")MultipartFile file) throws IOException {

	String uploadImage = service.UploadFileSystem(id,file);
	return ResponseEntity.status(HttpStatus.OK)
	.body(uploadImage);

	}


	@GetMapping ("/upload/{filename}")
	public  ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String filename) throws IOException{
	byte[] imageData=service.downloadImageFromFileSystem(filename);
	return ResponseEntity.status(HttpStatus.OK)
	.contentType(MediaType.valueOf("image/png,file/pdf"))
	.body(imageData);

	}

}

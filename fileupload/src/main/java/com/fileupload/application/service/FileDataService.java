package com.fileupload.application.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.application.model.FileData;
import com.fileupload.application.repository.FileDataRepository;




@Service

public class FileDataService {
	@Autowired
	FileDataRepository fileDataRepository;


	@Value("${AddressProof}")
	private String filepath;


	public String UploadFileSystem(long id, MultipartFile file)throws IOException {

	String filePath=filepath+file.getOriginalFilename();

	Optional<FileData> fileDataOptional = fileDataRepository.findById(id);

	if(fileDataOptional.isEmpty()) {
	return "it does not exisits";
	}

	FileData fileData=fileDataRepository.save(FileData.builder()
	.firstName(fileDataOptional.get().getFirstName())
	.lastName(fileDataOptional.get().getLastName())
	.dateofbirth(fileDataOptional.get().getDateofbirth())
	.email(fileDataOptional.get().getEmail())
	.name(file.getOriginalFilename())
	.id(id)
	.filepath(filePath).build());

	file.transferTo(new File(filePath));

	if(fileData!=null) {
	return "file uploded succesfully : "   +filePath;
	}
	return null;
	}



	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
	    Optional<FileData> fileData = fileDataRepository.findByName(fileName);
	    String filePath=fileData.get().getFilepath();
	    byte[] images = Files.readAllBytes(new File(filePath).toPath());
	    return images;
	}
}

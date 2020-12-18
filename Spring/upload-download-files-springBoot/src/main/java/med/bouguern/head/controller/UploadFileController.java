package med.bouguern.head.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import med.bouguern.head.model.FileModel;
import med.bouguern.head.repository.FileRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UploadFileController {
	
	@Autowired
	FileRepository fileRepo;
	
	/**
	 * MultipartFile Upload
	 */
	
	@PostMapping("/api/file/upload")
	public String uploadMultipartFile(@RequestParam("file") MultipartFile file) {
		try {
			// save file to mysql
			FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			fileRepo.save(filemode);
			
			return "File uploaded successfully -> fileName = " + file.getOriginalFilename();
		} catch(Exception ex) {
			return "FAIL! Maybe you had uploaded the file before or the file's size > 500KB";
		}
	}

}

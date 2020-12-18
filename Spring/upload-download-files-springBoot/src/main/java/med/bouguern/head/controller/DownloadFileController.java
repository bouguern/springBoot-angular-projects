package med.bouguern.head.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import med.bouguern.head.model.FileModel;
import med.bouguern.head.model.View;
import med.bouguern.head.repository.FileRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DownloadFileController {
	
	@Autowired
	FileRepository fileRepo;
	
	/**
	 * List all Files
	 */
	@JsonView(View.FileInfo.class)
	@GetMapping("/api/file/all")
	public List<FileModel> getListFiles() {
		return fileRepo.findAll();
	}
	
	/**
	 * Download Files
	 */
	@GetMapping("/api/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		
		Optional<FileModel> fileOptional = fileRepo.findById(id);
		
		if(fileOptional.isPresent()) {
			FileModel file = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName = " + file.getName())
					.body(file.getPic());
		}
		
		return ResponseEntity.status(404).body(null);
	}

}

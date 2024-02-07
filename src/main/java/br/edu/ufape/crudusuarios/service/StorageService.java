package br.edu.ufape.crudusuarios.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	@Value("${file.upload-dir}")
    private String uploadDir;
	
	public String uploadFile(MultipartFile file, String path) throws IllegalStateException, IOException {
		String fullPath = Paths.get(this.uploadDir, path).toString();
		File uploadDirectory = new File(fullPath);
		
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		
		File targetFile = new File(uploadDirectory, file.getOriginalFilename());
		file.transferTo(targetFile);
		
		String relativePath = Paths.get(path, file.getOriginalFilename()).toString();
		
		return relativePath;
	}
	
	public ResponseEntity<byte[]> downloadFile(String path) throws IOException {
		Path fullPath = Paths.get(uploadDir, path);
		File file = fullPath.toFile();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        headers.setContentLength(file.length());
        
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(fullPath);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}
}

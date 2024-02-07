package br.edu.ufape.crudusuarios.controller;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ufape.crudusuarios.model.Estudante;
import br.edu.ufape.crudusuarios.repository.EstudanteRepository;
import br.edu.ufape.crudusuarios.service.StorageService;

@RestController
@RequestMapping("api/")
public class StorageController {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@PostMapping("estudantes/{estudanteId}/assinatura")
	public ResponseEntity<String> assinaturaEstudante(@PathVariable Long estudanteId, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		String path = Paths.get("estudantes", estudanteId.toString()).toString();
		path = storageService.uploadFile(file, path);
		
		Estudante estudante = estudanteRepository.findById(estudanteId).get();
		estudante.setAssinaturaPath(path);
		estudanteRepository.save(estudante);
		
		return ResponseEntity.ok(path);
	}
	
	@GetMapping("estudantes/{estudanteId}/assinatura")
	public ResponseEntity<byte[]> assinaturaEstudante(@PathVariable Long estudanteId) throws IOException {
		Estudante estudante = estudanteRepository.findById(estudanteId).get();
		return storageService.downloadFile(estudante.getAssinaturaPath());
	}
	
}

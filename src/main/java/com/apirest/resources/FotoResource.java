package com.apirest.resources;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apirest.models.DesafioRepository;
import com.apirest.models.Foto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Fotos")
@CrossOrigin(origins = "*")
public class FotoResource {

	@Autowired
	DesafioRepository desafioRepository;

	@GetMapping("/fotos")
	@ApiOperation(value = "Retorna uma lista de fotos")
	public List<Foto> listarFotos() {
		return desafioRepository.findAll();

	}

	@GetMapping("/foto/{id}")
	@ApiOperation(value = "Retorna uma foto")
	public Optional<Foto> fotoId(@PathVariable(value = "id") Long id) {
		return desafioRepository.findById(id);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/foto")
	@ApiOperation(value = "Salva a imagem")
	public ResponseEntity<String> receiveData(MultipartFile img) throws IOException {

		Foto foto = new Foto();
		byte[] byteArr;
		byteArr = img.getBytes();
		String text = Base64.getEncoder().encodeToString(byteArr);
		foto.setFoto(text);
		System.out.println("IMG" + foto.getFoto());
		desafioRepository.save(foto);
		
		return ResponseEntity.ok("Imagem Salva com Sucesso!!");

	}

	@DeleteMapping("/foto")
	@ApiOperation(value = "Excluir fotos")
	public void excluir(@RequestBody Foto foto) {
		desafioRepository.delete(foto);
	}

	@PutMapping("/foto")
	@ApiOperation(value = "Atualizar fotos")
	public Foto atualizaFoto(@RequestBody Foto foto) {
		return desafioRepository.save(foto);
	}

	

}

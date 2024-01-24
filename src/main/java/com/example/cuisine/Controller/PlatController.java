package com.example.cuisine.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.cuisine.Model.Plat;
import com.example.cuisine.config.RoleName;
import com.example.cuisine.service.PlatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cuisine/plat/")
public class PlatController {

	@Autowired
	private PlatService platService;

	@GetMapping
	public ResponseEntity<?> getAllPlats() {
		List<Plat> plats = this.platService.getAll();
		return plats.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No plat available")
				: ResponseEntity.ok(plats);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPlatById(@PathVariable String id) {
		return ResponseEntity.ok(this.platService.getOne(id));
	}

	@Secured({ RoleName.ROLE_ADMIN, RoleName.ROLE_CUISINE })
	@PostMapping
	public ResponseEntity<?> createPlat(@RequestParam("photo") MultipartFile file,
			@Valid @RequestPart("data") Plat plat) {
		try {
			plat.setPhoto(file.getBytes());
			plat = this.platService.save(plat);
			return ResponseEntity.ok(plat);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Secured({ RoleName.ROLE_ADMIN, RoleName.ROLE_CUISINE })
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePlat(@RequestBody Plat plat) {
		try {
			return ResponseEntity.ok(this.platService.save(plat));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Secured({ RoleName.ROLE_ADMIN, RoleName.ROLE_CUISINE })
	@DeleteMapping("/{plat}")
	public ResponseEntity<?> deletePlat(@PathVariable Plat plat) {
		try {
			this.platService.delete(plat);
			return ResponseEntity.ok("Successfully deleted Plat with id " + plat.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}

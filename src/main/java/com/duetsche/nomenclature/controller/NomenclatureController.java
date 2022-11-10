package com.duetsche.nomenclature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.helper.CSVHelper;
import com.duetsche.nomenclature.message.ResponseMessage;
import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.service.NomenclatureServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@ApiOperation("Nomenclature API")
@RequestMapping("/api")
public class NomenclatureController {

	@Autowired
	NomenclatureServiceImpl nomenclatureService;

	private Nomenclature nomenclature;

	@ApiOperation(value = "upload the NAC details csv file", notes = "NAC csv file details are saved into the system")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Uploaded the NAC file successfully"),
	  @ApiResponse(code = 404, message = "Could not upload the NAC file")
	})
	@PostMapping("/nomenclature/upload")
	public ResponseEntity<ResponseMessage> uploadNacDetails(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				nomenclatureService.uploadNacDetails(file);

				message = "Uploaded the NAC file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the NAC file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@ApiOperation(value = "get the NAC details by id", notes = "Returns the NAC details as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 404, message = "No Nac record exists for the given id")
	})
	@GetMapping("/nomenclature/{orderId}")
	public ResponseEntity<Nomenclature> getNacDetails(@PathVariable Long orderId) throws ResourceNotFoundException {
		nomenclature = nomenclatureService.getNacDetailsByOrderId(orderId).get();
		return new ResponseEntity<Nomenclature>(nomenclature, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "save the NAC details", notes = "Returns the saved Nac details")
	@PostMapping("/nomenclature")
	public ResponseEntity<Nomenclature> putNacDetails(@RequestBody Nomenclature nomenclature) {
		nomenclature = nomenclatureService.saveNacDetails(nomenclature);
		return new ResponseEntity<Nomenclature>(nomenclature, new HttpHeaders(), HttpStatus.CREATED);
	}

}

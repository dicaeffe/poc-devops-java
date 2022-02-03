package poc.devops.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import poc.devops.bean.ElementResponse;
import poc.devops.bean.ErrorResponse;
import poc.devops.bean.model.Element;
import poc.devops.service.ElementService;
import poc.devops.utils.Enums.EError;

@Slf4j
/* @RestController combines @Controller and @ResponseBody */
@RestController
@RequestMapping("/crud")
public class CRUDController {
	
	@Autowired
	private ElementService service;
	
	
	@GetMapping("")
	public ResponseEntity<?> get() {
		
    	log.info("REQUESTED - GET /crud");
		return ResponseEntity.ok(new ElementResponse(service.getAll()));
	}
	
	
	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> get(@PathVariable String name) {
		
    	log.info("REQUESTED - GET /crud/{}", name);
		return ResponseEntity.ok(new ElementResponse(service.get(name)));
	}
	
	
    @PostMapping(value = "",
    			 consumes = MediaType.APPLICATION_JSON_VALUE,
    			 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> post(
    		@RequestHeader	(value = "myheader",	required = false) String cf,
            @RequestParam	(value = "myParam",		required = false) String tipo,
            @RequestBody @Valid	Element request) {

    	log.info("REQUESTED - POST /crud");
    	log.info("Request = {}", request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ElementResponse(service.add(request)));
    }
    
    
    /** Manage bean validation (https://beanvalidation.org/2.0/).
     * 
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e) {
    	
    	log.error("Handling validation exception | {}", e);
    	Map<String, String> errorMap = new HashMap<>();
    	
    	e.getBindingResult().getAllErrors().forEach((error) -> {
													    		String fieldName = ((FieldError) error).getField();
													    		String errorMessage = error.getDefaultMessage();
													    		errorMap.put(fieldName, errorMessage);
													    	});
    	return new ErrorResponse(EError.INVALID_INPUT, errorMap);
    }
}
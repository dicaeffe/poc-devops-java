package poc.devops.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import poc.devops.bean.ScientistResponse;
import poc.devops.bean.model.Scientist;
import poc.devops.service.ScientistService;

@Slf4j
/* @RestController combines @Controller and @ResponseBody */
@RestController
@RequestMapping("/scientist")
public class ScientistController extends AbstractController {
	
	@Autowired
	private ScientistService service;
	
	
	@Operation(summary = "Retrieves all scientists.", description = "")
    @GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public ScientistResponse get() {
		
    	log.info("REQUESTED - GET /crud");
		return new ScientistResponse(service.getAll());
	}
	
	
	@Operation(summary = "Retrieves the scientist with the requested name.", description = "The name must be mapped with an available scientist.")
	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ScientistResponse get(@PathVariable String name) {
		
    	log.info("REQUESTED - GET /crud/{}", name);
		return new ScientistResponse(service.get(name));
	}
	
	
	@Operation(summary = "Post a new scientist.", description = "This is an API that adds a new scientist to the datasource.")
    @PostMapping(value = "",
    			 consumes = MediaType.APPLICATION_JSON_VALUE,
    			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
    public ScientistResponse post(
    		@RequestHeader	(value = "myheader",	required = false) String cf,
            @RequestParam	(value = "myParam",		required = false) String tipo,
            @RequestBody @Valid	Scientist request) {

    	log.info("REQUESTED - POST /crud");
    	log.info("Request = {}", request);
		return new ScientistResponse(service.add(request));
    }
}
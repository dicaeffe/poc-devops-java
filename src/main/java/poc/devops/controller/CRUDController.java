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
import poc.devops.bean.ElementResponse;
import poc.devops.bean.model.Element;
import poc.devops.service.ElementService;

@Slf4j
/* @RestController combines @Controller and @ResponseBody */
@RestController
@RequestMapping("/crud")
public class CRUDController extends AbstractController {
	
	@Autowired
	private ElementService service;
	
	
	@Operation(summary = "Retrieves all elements.", description = "")
    @GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public ElementResponse get() {
		
    	log.info("REQUESTED - GET /crud");
		return new ElementResponse(service.getAll());
	}
	
	
	@Operation(summary = "Retrieves the element with the requested name.", description = "The name must be mapped with an available element.")
	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ElementResponse get(@PathVariable String name) {
		
    	log.info("REQUESTED - GET /crud/{}", name);
		return new ElementResponse(service.get(name));
	}
	
	
	@Operation(summary = "Post a new element.", description = "This is an API that adds a new element to the datasource.")
    @PostMapping(value = "",
    			 consumes = MediaType.APPLICATION_JSON_VALUE,
    			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
    public ElementResponse post(
    		@RequestHeader	(value = "myheader",	required = false) String cf,
            @RequestParam	(value = "myParam",		required = false) String tipo,
            @RequestBody @Valid	Element request) {

    	log.info("REQUESTED - POST /crud");
    	log.info("Request = {}", request);
		return new ElementResponse(service.add(request));
    }
}
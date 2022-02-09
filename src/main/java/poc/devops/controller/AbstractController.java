package poc.devops.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import poc.devops.bean.ErrorResponse;
import poc.devops.utils.Enums.EError;

@Slf4j
@ControllerAdvice
public class AbstractController {
    
    
	//HTTP 400
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
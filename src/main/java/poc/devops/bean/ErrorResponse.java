package poc.devops.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import poc.devops.utils.Enums.EError;

@Data
@AllArgsConstructor
/* Any property not bound in this type should be ignored. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

	private List<Error> errors;

	public ErrorResponse(EError error, Map<String, String> errorMap) {
		super();
		
		List<Error> list = new ArrayList<>();
		errorMap.entrySet().stream().forEach(e -> list.add(new Error(error, e.getKey(), e.getValue())));
		this.errors = list;
	}
	
	
}

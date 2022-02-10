package poc.devops.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import poc.devops.utils.Enums.EError;

@Data
@AllArgsConstructor
/* Any property not bound in this type should be ignored. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
	
	@NotEmpty
	@Schema(description = "List of errors.")
	private List<Error> errors;
	
	
	public ErrorResponse(EError error, Map<String, String> errorMap) {
		super();
		
		List<Error> list = new ArrayList<>();
		errorMap.entrySet().stream().forEach(e -> list.add(new Error(error, e.getKey(), e.getValue())));
		this.errors = list;
	}
	
	
}

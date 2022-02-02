package poc.devops.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import poc.devops.utils.Enums.EError;

@Data
/* Any property not bound in this type should be ignored. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

	private String field;
	private String errorCode;
	private String errorMessage;
	
	
	public Error(EError error) {
		super();
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
	}
	
	public Error(EError error, String field, String message) {
		super();
		this.field = field;
		this.errorCode = error.getErrorCode();
		this.errorMessage = String.format(error.getErrorMessage(), field, message);
	}
}
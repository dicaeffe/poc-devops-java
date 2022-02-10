package poc.devops.bean;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import poc.devops.utils.Enums.EError;

@Data
/* Any property not bound in this type should be ignored. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

	@Schema(description = "The name of the field involved in the validation.", example = "name")
	private String field;

    @NotBlank
	@Schema(description = "Error code.", example = "000.001")
	private String errorCode;

    @NotBlank
	@Schema(description = "Error message.", example = "Invalid input: the field [name] must not be blank")
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
package poc.devops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import poc.devops.bean.model.Scientist;

@Data
@AllArgsConstructor
/* Any property not bound in this type should be ignored. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScientistResponse {

	@Schema(description = "List of scientists.")
	private List<Scientist> scientists;
}

package poc.devops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import poc.devops.bean.model.Element;

@Data
@AllArgsConstructor
/* Any property not bound in this type should be ignored. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementResponse {

	private List<Element> elements;
}

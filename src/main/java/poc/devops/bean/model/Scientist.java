package poc.devops.bean.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Scientist {

	@Schema(description = "Alphanumeric identifier associated to the scientist.", example = "a123b4c5-de6f-7890-gh9i-j87k6lm5nop4")
	private String uuid;

    @NotBlank
	@Schema(description = "Name of the scientist.", example = "Marie Salomea Skłodowska Curie")
	private String name;
	
    @Min(value = 0)
	@Schema(description = "Number of Nobel prizes.", example = "2")
	private long numberOfNobels;
    
	@Schema(description = "Creation date.", example = "1903-11-12T01:23:45.678Z")
	private Date creationDate;
	
	@Schema(description = "Update date.", example = "1903-11-12T01:23:45.678Z")
	private Date updateDate;
	
	
	public Scientist(String name, long numberOfNobels) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.name = name;
		this.numberOfNobels = numberOfNobels;
		this.creationDate = new Date();
	}


	public Scientist(Scientist scientist) {
		this(scientist.getName(), scientist.getNumberOfNobels());
	}
}

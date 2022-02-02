package poc.devops.bean.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Element {
	
	private String uuid;

    @NotBlank
	private String name;
	
    @Min(value = 2)
	private long points;

	private Date creationDate;
	
	private Date updateDate;
	
	
	public Element(@NotBlank String name, long points) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.name = name;
		this.points = points;
		this.creationDate = new Date();
	}


	public Element(@Valid Element element) {
		this(element.getName(), element.getPoints());
	}
}

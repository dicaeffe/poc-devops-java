package poc.devops.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import poc.devops.bean.model.Scientist;

@Service
public class ScientistService {
	
	private List<Scientist> datasource = initiate();
	
	
	private List<Scientist> initiate() {
		List<Scientist> list = new ArrayList<>();
		Collections.addAll(list, new Scientist("Marie Salomea Skłodowska Curie", 2), 
								 new Scientist("Irène Joliot-Curie", 1),
								 new Scientist("Paul Langevin", 0));
		return list;
	}
	
	
	/** @return all Scientists in the datasource. */
	public List<Scientist> getAll() {
		return datasource;
	}
	
	
	/** @param name
	 *  @return the Scientists where the name field match with the provided name.
	 */
	public List<Scientist> get(String name) {
		return datasource.stream()
						 .filter(e -> e.getName().equalsIgnoreCase(name))
						 .collect(Collectors.toList());
	}

	
	/** Add the scientist to the datasource.
	 * 
	 * @param request
	 * @return
	 */
	public List<Scientist> add(@Valid Scientist scientist) {
		Scientist newScientist = new Scientist(scientist);
		datasource.add(newScientist);
		return Collections.singletonList(newScientist);
	}

}

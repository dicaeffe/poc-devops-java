package poc.devops.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import poc.devops.bean.model.Element;

@Service
public class ElementService {
	
	private List<Element> datasource = initiate();
	
	
	private List<Element> initiate() {
		List<Element> list = new ArrayList<>();
		Collections.addAll(list, new Element("Marie Salomea Skłodowska Curie", 2), 
								 new Element("Irène Joliot-Curie", 1),
								 new Element("Paul Langevin", 0));
		return list;
	}
	
	
	/** @return all Elements in the datasource. */
	public List<Element> getAll() {
		return datasource;
	}
	
	
	/** @param name
	 *  @return the Elements where the name field match with the provided name.
	 */
	public List<Element> get(String name) {
		return datasource.stream()
						 .filter(e -> e.getName().equalsIgnoreCase(name))
						 .collect(Collectors.toList());
	}

	
	/** Add the element to the datasource.
	 * 
	 * @param request
	 * @return
	 */
	public List<Element> add(@Valid Element element) {
		Element newElement = new Element(element);
		datasource.add(newElement);
		return Collections.singletonList(newElement);
	}

}

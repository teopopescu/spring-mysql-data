package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
//@RequestMapping(path="/attractions") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private AttractionRepository attractionRepository;
	
	//@GetMapping(path="/postAttractions") // Map ONLY GET Requests
	@RequestMapping(value = "/attractions", method = RequestMethod.POST)
	public @ResponseBody String addNewUser (@RequestParam Integer id ,@RequestParam String address
			, @RequestParam double lat, @RequestParam double lng,
											@RequestParam String name,
											@RequestParam String type) {
		// @ResponseBody means the returned String is the response,cp850_general_ci not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Attraction newAttraction = new Attraction();
		newAttraction.setId(id);
		newAttraction.setName(name);
		newAttraction.setAddress(address);
		newAttraction.setLat(lat);
		newAttraction.setLng(lng);
		newAttraction.setType(type);
		attractionRepository.save(newAttraction);
		return "Saved";
	}
	
	//@GetMapping(path="/alll")
	@RequestMapping(value = "/attractions", method = RequestMethod.GET)
	public @ResponseBody Iterable<Attraction> getAllUsers() {
		// This returns a JSON or XML with the users
		return attractionRepository.findAll();
	}

	//@RequestMapping(value = "/attractions", method = RequestMethod.GET)
	//@ResponseBody
	//public String getAttractions() {

	//	return "ALL ATTRACTIONS" ;

	//}







}

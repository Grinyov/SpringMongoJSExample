package rest;
import domain.model.Person;
import domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Created by Grinyov Vitaliy on 04.09.15.
 *
 *
 *
 */
public class RestApiHandler extends AbstractRestHandler {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/api/person/{personId}")
    public @ResponseBody Person findPersonById(@PathVariable("personId") String personId) {
        return personRepository.findById(personId);
    }

    @RequestMapping("/api/person/all")
    public @ResponseBody List findAll(
            @RequestParam(value="page", required=true) Integer page,
            @RequestParam(value="size", required=true) Integer size) {

        Page pageOfPerson = personRepository.findAll(new PageRequest(page, size));

        return pageOfPerson.getContent();
    }

    @RequestMapping(value="/adminapi/person/add", method=RequestMethod.POST)
    public @ResponseBody RestResponse addPerson(@RequestBody Person person) {
        RestResponse response = new RestResponse("Person object was successfully saved", 0);

        /**
         * there is no need to handle exception, super.handleUncaughtException method
         * will handle exceptions if there is any thrown exception
         */

        mongoTemplate.save(person);

        return response;
    }
}

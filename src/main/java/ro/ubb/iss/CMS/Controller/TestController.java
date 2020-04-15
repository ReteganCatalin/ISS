package ro.ubb.iss.CMS.Controller;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.iss.CMS.Repository.personRepository;
import ro.ubb.iss.CMS.domain.Person;

@RestController
@RequestMapping("person")
public class TestController {

    @Autowired
    private personRepository PersonRepository;

    @Builder
    public String createPerson(@RequestParam String name)
    {
        PersonRepository.save(new Person.builder());
        return name;
    }
}

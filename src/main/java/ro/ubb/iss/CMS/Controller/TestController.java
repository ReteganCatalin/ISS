package ro.ubb.iss.CMS.Controller;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.iss.CMS.Repository.personRepository;
import ro.ubb.iss.CMS.domain.Person;

import java.util.List;

@RestController
@RequestMapping("person")
public class TestController {

    @Autowired
    private personRepository PersonRepository;

    @PostMapping("person")
    public String createPerson(@RequestParam String name)
    {
        Person to_save=Person.builder().person_id(1).name(name).height("180 cm").build();
        System.out.println(to_save);
        PersonRepository.save(to_save);
        return PersonRepository.findByName(name)+" Succesfully saved";
    }

    @GetMapping("person")
    public List<Person> getAllPersons()
    {
        return PersonRepository.findAll();
    }
}

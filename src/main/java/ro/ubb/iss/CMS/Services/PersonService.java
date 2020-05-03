package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Person;
import ro.ubb.iss.CMS.domain.Presentation;
import ro.ubb.iss.CMS.domain.Section;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> findPerson(int personID);

    List<Person> findAll();

    Person updatePerson(
            int personID,
            String name,
            int height,
            String address);

    Person savePerson(
            String name,
            int height,
            String address);

    void deletePerson(int personID);
}

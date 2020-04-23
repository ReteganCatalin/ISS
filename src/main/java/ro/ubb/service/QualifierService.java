package ro.ubb.service;

import ro.ubb.domain.Qualifier;
import ro.ubb.domain.Recommendation;
import ro.ubb.domain.Review;

import java.util.List;
import java.util.Optional;

public interface QualifierService {
    Optional<Qualifier> findQualifier(int qualifierID);

    List<Qualifier> findAll();

    Qualifier updateQualifier(int qualifierID, String name);

    Qualifier saveQualifier(String name);

    void deleteQualifier(int qualifierID);
}

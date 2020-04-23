package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Qualifier;

import java.util.List;
import java.util.Optional;

public interface QualifierService {
  Optional<Qualifier> findQualifier(int qualifierID);

  List<Qualifier> findAll();

  Qualifier updateQualifier(int qualifierID, String name);

  Qualifier saveQualifier(String name);

  void deleteQualifier(int qualifierID);
}

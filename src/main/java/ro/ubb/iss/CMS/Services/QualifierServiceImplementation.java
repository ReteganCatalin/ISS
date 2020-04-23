package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Qualifier;

import java.util.List;
import java.util.Optional;

public class QualifierServiceImplementation implements QualifierService {
  @Override
  public Optional<Qualifier> findQualifier(int qualifierID) {
    return Optional.empty();
  }

  @Override
  public List<Qualifier> findAll() {
    return null;
  }

  @Override
  public Qualifier updateQualifier(int qualifierID, String name) {
    return null;
  }

  @Override
  public Qualifier saveQualifier(String name) {
    return null;
  }

  @Override
  public void deleteQualifier(int qualifierID) {}
}

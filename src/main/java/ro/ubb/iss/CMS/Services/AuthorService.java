package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.domain.Proposal;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
  Optional<Author> findAuthor(int abstractID);

  List<Author> findAll();

  Author updateAuthor(int authorID, String name, Proposal proposalID);

  Author saveAuthor(String name, Proposal proposalID);

  void deleteAuthor(int authorID);
}

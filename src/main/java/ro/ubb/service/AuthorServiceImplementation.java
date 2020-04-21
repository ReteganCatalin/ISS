package ro.ubb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.domain.Analysis;
import ro.ubb.domain.Author;
import ro.ubb.domain.Proposal;
import ro.ubb.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService{
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImplementation.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Optional<Author> findAuthor(int abstractID) {
        log.trace("findAuthor - method entered");
        Optional<Author> result = authorRepository.findById(abstractID);
        log.trace("findAuthor - method exit result={}",result);
        return result;
    }

    @Override
    public List<Author> findAll() {
        log.trace("findAll - method entered");
        List<Author> result = authorRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    public Author updateAuthor(int authorID, String name, Proposal proposalID) {
        log.trace("updateAuthor - method entered: authorID={}, name={}, proposalID={}", authorID,name,proposalID);

        Optional<Author> abstractOptional = authorRepository.findById(authorID);

        abstractOptional.ifPresent(
                newAuthor -> {
                    newAuthor.setName(name);
                    newAuthor.setProposal(proposalID);
                    log.debug("updateAuthor - updated: newAuthor={}", newAuthor);
                });
        log.trace("updateAuthor - method finished result={}",abstractOptional);
        return abstractOptional.orElse(null);
    }

    @Override
    public Author saveAuthor(String name, Proposal proposalID) {
        log.trace("saveAuthor - method entered: name={}, proposalID={}", name,proposalID);
        Author newAuthor = Author.builder().name(name).proposal(proposalID).build();

        authorRepository.save(newAuthor);

        log.trace("saveAuthor - method finished result={}",newAuthor);
        return newAuthor;
    }

    @Override
    public void deleteAuthor(int authorID) {
        log.trace("deleteAuthor - method entered: authorID={}", authorID);
        authorRepository.deleteById(authorID);
        log.trace("deleteAuthor - method finished");

    }
}

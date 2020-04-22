package ro.ubb.service;

import ro.ubb.domain.Abstract;
import ro.ubb.domain.Proposal;

import java.util.List;
import java.util.Optional;

public interface AbstractService {
    Optional<Abstract> findAbstract(int abstractID);

    List<Abstract> findAll();

    Abstract updateAbstract(int abstractID, String format, String byteFileLocation);

    Abstract saveAbstract(String format, String byteFileLocation);

    void deleteAbstract(int abstractID);

}

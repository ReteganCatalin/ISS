package ro.ubb.iss.CMS.Services;


import ro.ubb.iss.CMS.domain.MetaInformation;

import java.util.List;
import java.util.Optional;

public interface MetaInformationService {

    Optional<MetaInformation> findMetaInformation(int metaInfoID);

    List<MetaInformation> findAll();

    MetaInformation updateMetaInformation(int metaInfoID, String name, String keywords, String topics);

    MetaInformation saveMetaInformation(String name, String keywords, String topics);

    void deleteMetaInformation(int metaInfoID);

}

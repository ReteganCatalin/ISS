package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.AnalysisKey;

public interface AnalysisRepository extends JpaRepository<Analysis, AnalysisKey> {}

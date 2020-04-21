package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Analysis;
import ro.ubb.domain.AnalysisKey;

public interface AnalysisRepository extends JpaRepository<Analysis, AnalysisKey> {
}

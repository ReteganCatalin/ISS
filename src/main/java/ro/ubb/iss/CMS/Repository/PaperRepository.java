package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Paper;

public interface PaperRepository extends JpaRepository<Paper, Integer> {}

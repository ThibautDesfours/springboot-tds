package s4.spring.td2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td2.entities.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer>{

}

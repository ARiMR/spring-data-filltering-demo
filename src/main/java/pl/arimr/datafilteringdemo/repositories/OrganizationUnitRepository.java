package pl.arimr.datafilteringdemo.repositories;

import org.springframework.stereotype.Repository;
import pl.arimr.datafilteringdemo.domain.OrganizationUnit;
import pl.arimr.datafilteringdemo.repositories.base.BaseRepository;

@Repository
public interface OrganizationUnitRepository extends BaseRepository<OrganizationUnit, Long> {
}

package pl.arimr.datafilteringdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.arimr.datafilteringdemo.domain.OrganizationUnit;
import pl.arimr.datafilteringdemo.repositories.OrganizationUnitRepository;

@Transactional
@Service
public class OrganizationUnitService {
    private final OrganizationUnitRepository organizationUnitRepository;

    public OrganizationUnitService(OrganizationUnitRepository organizationUnitRepository) {
        this.organizationUnitRepository = organizationUnitRepository;
    }

    public OrganizationUnit save(OrganizationUnit organizationUnit) {
        return this.organizationUnitRepository.save(organizationUnit);
    }

}

package pl.arimr.datafilteringdemo.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.arimr.datafilteringdemo.domain.Application;
import pl.arimr.datafilteringdemo.domain.OrganizationUnit;
import pl.arimr.datafilteringdemo.repositories.ApplicationRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class ApplicationService {

    private final EntityManager entityManager;
    private final ApplicationRepository applicationRepository;

    public ApplicationService(EntityManager entityManager, ApplicationRepository applicationRepository) {
        this.entityManager = entityManager;
        this.applicationRepository = applicationRepository;
    }

    public Application save(Application application) {
        return this.applicationRepository.save(application);
    }

    public List<Application> findByAmountGreaterThanOrderById(BigDecimal amount) {
        return this.applicationRepository.findByAmountGreaterThanOrderById(amount);
    }

    public List<Application> findByAmountGreaterThanOrderByIdAndOrganizationUnit(BigDecimal amount, OrganizationUnit organizationUnit) {
        Session session = (Session) entityManager.getDelegate();

        Filter filter = session.enableFilter("organizationUnitFilter");
        filter.setParameter("unitId", organizationUnit.getId());

        List<Application> applications = this.applicationRepository.findByAmountGreaterThanOrderById(amount);

        session.disableFilter("organizationUnitFilter");

        return applications;
    }
}

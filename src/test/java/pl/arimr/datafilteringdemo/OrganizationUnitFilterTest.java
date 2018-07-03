package pl.arimr.datafilteringdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.arimr.datafilteringdemo.domain.Application;
import pl.arimr.datafilteringdemo.domain.OrganizationUnit;
import pl.arimr.datafilteringdemo.service.ApplicationService;
import pl.arimr.datafilteringdemo.service.OrganizationUnitService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationUnitFilterTest {

    @Autowired
    private OrganizationUnitService organizationUnitService;
    @Autowired
    private ApplicationService applicationService;

    @Test
    public void organizationUnitFilterTest() {
        // create units
        OrganizationUnit unitA = organizationUnitService.save(new OrganizationUnit("Organization unit A", "A"));
        OrganizationUnit unitB = organizationUnitService.save(new OrganizationUnit("Organization unit B", "B"));
        OrganizationUnit unitC = organizationUnitService.save(new OrganizationUnit("Organization unit C", "C"));

        // create applications
        List<Application> applications = createApplications(unitA, unitB, unitC);

        Long expectedCountForMinAmount1000AndUnitA = findExpectedCount(applications, unitA, BigDecimal.valueOf(1000));
        log.info("expectedCountForMinAmount1000AndUnitA={}", expectedCountForMinAmount1000AndUnitA);
        Long expectedCountForMinAmount1000AndUnitB = findExpectedCount(applications, unitB, BigDecimal.valueOf(1000));
        log.info("expectedCountForMinAmount1000AndUnitB={}", expectedCountForMinAmount1000AndUnitB);
        Long expectedCountForMinAmount1000AndUnitC = findExpectedCount(applications, unitC, BigDecimal.valueOf(1000));
        log.info("expectedCountForMinAmount1000AndUnitC={}", expectedCountForMinAmount1000AndUnitC);

        List<Application> applicationsForMinAmount1000 = applicationService.findByAmountGreaterThanOrderById(BigDecimal.valueOf(1000));
        log.info("applicationsForMinAmount1000={}", applicationsForMinAmount1000.size());

        Assert.assertEquals(
                applicationsForMinAmount1000.size(),
                expectedCountForMinAmount1000AndUnitA + expectedCountForMinAmount1000AndUnitB + expectedCountForMinAmount1000AndUnitC
        );

        Long countForMinAmount1000AndUnitA = (long) applicationService.findByAmountGreaterThanOrderByIdAndOrganizationUnit(BigDecimal.valueOf(1000), unitA).size();
        log.info("countForMinAmount1000AndUnitA={}", countForMinAmount1000AndUnitA);
        Long countForMinAmount1000AndUnitB = (long) applicationService.findByAmountGreaterThanOrderByIdAndOrganizationUnit(BigDecimal.valueOf(1000), unitB).size();
        log.info("countForMinAmount1000AndUnitB={}", countForMinAmount1000AndUnitB);
        Long countForMinAmount1000AndUnitC = (long) applicationService.findByAmountGreaterThanOrderByIdAndOrganizationUnit(BigDecimal.valueOf(1000), unitC).size();
        log.info("countForMinAmount1000AndUnitC={}", countForMinAmount1000AndUnitC);

        Assert.assertEquals(expectedCountForMinAmount1000AndUnitA, countForMinAmount1000AndUnitA);
        Assert.assertEquals(expectedCountForMinAmount1000AndUnitB, countForMinAmount1000AndUnitB);
        Assert.assertEquals(expectedCountForMinAmount1000AndUnitC, countForMinAmount1000AndUnitC);
    }

    private List<Application> createApplications(OrganizationUnit unitA, OrganizationUnit unitB, OrganizationUnit unitC) {
        List<Application> applications = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            OrganizationUnit organizationUnit = null;
            if (i % 3 == 0) {
                organizationUnit = unitA;
            }
            if (i % 3 == 1) {
                organizationUnit = unitB;
            }
            if (i % 3 == 2) {
                organizationUnit = unitC;
            }
            Random random = new Random();
            Application application = applicationService.save(new Application(
                    "Application #" + i,
                    LocalDateTime.now(),
                    organizationUnit,
                    BigDecimal.valueOf(random.nextInt())
            ));
            applications.add(application);
        }
        return applications;
    }

    private Long findExpectedCount(List<Application> applications, OrganizationUnit unit, BigDecimal minAmount) {
        return applications.stream()
                .filter(application -> Objects.equals(application.getOrganizationUnit(), unit))
                .filter(application -> application.getAmount().compareTo(minAmount) > 0)
                .count();
    }

}

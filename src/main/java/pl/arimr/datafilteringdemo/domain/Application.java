package pl.arimr.datafilteringdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FilterDef(name = "organizationUnitFilter", parameters = {
        @ParamDef(name = "unitId", type = "long")
})
@Filters({
        @Filter(name = "organizationUnitFilter", condition = "organization_unit = :unitId")
})
public class Application extends BaseEntity {
    private String name;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "organization_unit")
    private OrganizationUnit organizationUnit;
    private BigDecimal amount;
}

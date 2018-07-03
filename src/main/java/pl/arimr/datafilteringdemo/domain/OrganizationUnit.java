package pl.arimr.datafilteringdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationUnit extends BaseEntity {
    private String name;
    private String code;
}

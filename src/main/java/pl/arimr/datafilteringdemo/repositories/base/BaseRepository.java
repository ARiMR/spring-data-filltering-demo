package pl.arimr.datafilteringdemo.repositories.base;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    EntityManager getEntityManager();

    Session getSession();
}

package pl.arimr.datafilteringdemo.repositories;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.arimr.datafilteringdemo.domain.Application;
import pl.arimr.datafilteringdemo.repositories.base.BaseRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ApplicationRepository extends BaseRepository<Application, Long> {
    List<Application> findByAmountGreaterThanOrderById(BigDecimal amount);

    @Query("from Application a where a.amount>:amount")
    List<Application> findByAmountGreaterThanOrderByIdUsingQuery(@Param("amount") BigDecimal amount);

    @SuppressWarnings({"unchecked"})
    default List<Application> findByAmountGreaterThanOrderByIdUsingCriteria(BigDecimal amount) {
        return getSession()
                .createCriteria(Application.class, "a")
                .add(Restrictions.gt("a.amount", amount))
                .addOrder(Order.asc("a.id"))
                .list();
    }
}

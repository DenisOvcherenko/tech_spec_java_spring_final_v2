package webraiz.tech_spec_java_spring_final_v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webraiz.tech_spec_java_spring_final_v2.dto.TopSubscriptionDto;
import webraiz.tech_spec_java_spring_final_v2.model.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT new webraiz.tech_spec_java_spring_final_v2.dto.TopSubscriptionDto(s.name, COUNT(u)) " +
            "FROM Subscription s JOIN s.users u " +
            "GROUP BY s.id, s.name " +
            "ORDER BY COUNT(u) DESC")
    List<TopSubscriptionDto> findTopSubscriptions();
}

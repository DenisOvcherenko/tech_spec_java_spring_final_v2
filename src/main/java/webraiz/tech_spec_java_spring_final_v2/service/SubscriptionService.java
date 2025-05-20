package webraiz.tech_spec_java_spring_final_v2.service;

import webraiz.tech_spec_java_spring_final_v2.dto.TopSubscriptionDto;
import webraiz.tech_spec_java_spring_final_v2.model.Subscription;
import webraiz.tech_spec_java_spring_final_v2.model.User;

import java.util.List;
import java.util.Set;

public interface SubscriptionService {
    User addSubscription(Long userId, Long subscriptionId);

    User removeSubscription(Long userId, Long subscriptionId);

    Set<Subscription> getUserSubscriptions(Long userId);

    List<TopSubscriptionDto> getTopSubscriptions(int limit);
}

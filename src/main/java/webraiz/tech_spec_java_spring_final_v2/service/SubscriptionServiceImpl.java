package webraiz.tech_spec_java_spring_final_v2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webraiz.tech_spec_java_spring_final_v2.dto.TopSubscriptionDto;
import webraiz.tech_spec_java_spring_final_v2.model.Subscription;
import webraiz.tech_spec_java_spring_final_v2.model.User;
import webraiz.tech_spec_java_spring_final_v2.repository.SubscriptionRepository;
import webraiz.tech_spec_java_spring_final_v2.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public User addSubscription(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Подписка не найдена"));

        user.getSubscriptions().add(subscription);
        return userRepository.save(user);
    }

    @Override
    public User removeSubscription(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Подписка не найдена"));

        user.getSubscriptions().remove(subscription);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Subscription> getUserSubscriptions(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"))
                .getSubscriptions();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopSubscriptionDto> getTopSubscriptions(int limit) {
        List<TopSubscriptionDto> topSubscriptions = subscriptionRepository.findTopSubscriptions();
        if (topSubscriptions.size() > limit) {
            return topSubscriptions.subList(0, limit);
        }
        return topSubscriptions;
    }
}

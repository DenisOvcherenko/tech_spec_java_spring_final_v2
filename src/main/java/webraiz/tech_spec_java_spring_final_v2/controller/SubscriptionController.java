package webraiz.tech_spec_java_spring_final_v2.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webraiz.tech_spec_java_spring_final_v2.dto.SubscriptionDto;
import webraiz.tech_spec_java_spring_final_v2.dto.TopSubscriptionDto;
import webraiz.tech_spec_java_spring_final_v2.dto.UserDto;
import webraiz.tech_spec_java_spring_final_v2.mapper.UserMapper;
import webraiz.tech_spec_java_spring_final_v2.model.Subscription;
import webraiz.tech_spec_java_spring_final_v2.model.User;
import webraiz.tech_spec_java_spring_final_v2.service.SubscriptionService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
    private final SubscriptionService subscriptionService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<UserDto> addSubscription(@PathVariable Long userId,
                                                   @RequestBody SubscriptionDto subscriptionDto) {
        User user = subscriptionService.addSubscription(userId, subscriptionDto.getId());
        return ResponseEntity.ok(UserMapper.toDto(user));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<SubscriptionDto>> getUserSubscriptions(@PathVariable Long userId) {
        logger.info("Получение подписок для пользователя {}", userId);
        Set<Subscription> subscriptions = subscriptionService.getUserSubscriptions(userId);
        Set<SubscriptionDto> dto = subscriptions.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/user/{userId}/{subscriptionId}")
    public ResponseEntity<UserDto> removeSubscription(@PathVariable Long userId,
                                                      @PathVariable Long subscriptionId) {
        logger.info("Удалить подписку  {} у пользователя {}", subscriptionId, userId);
        User user = subscriptionService.removeSubscription(userId, subscriptionId);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping("/top")
    public List<TopSubscriptionDto> getTopSubscriptions() {
        logger.info("Получить top подписок");
        return subscriptionService.getTopSubscriptions(3);
    }
}

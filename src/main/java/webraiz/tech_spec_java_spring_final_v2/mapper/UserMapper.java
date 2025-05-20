package webraiz.tech_spec_java_spring_final_v2.mapper;


import webraiz.tech_spec_java_spring_final_v2.dto.SubscriptionDto;
import webraiz.tech_spec_java_spring_final_v2.dto.UserDto;
import webraiz.tech_spec_java_spring_final_v2.model.Subscription;
import webraiz.tech_spec_java_spring_final_v2.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;
        Set<SubscriptionDto> subs = null;
        if (user.getSubscriptions() != null) {
            subs = user.getSubscriptions().stream()
                    .map(UserMapper::toDto)
                    .collect(Collectors.toSet());
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .subscriptions(subs)
                .build();
    }

    public static SubscriptionDto toDto(Subscription s) {
        if (s == null) return null;
        return SubscriptionDto.builder()
                .id(s.getId())
                .name(s.getName())
                .build();
    }
}

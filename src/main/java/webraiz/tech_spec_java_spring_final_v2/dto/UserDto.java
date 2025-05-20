package webraiz.tech_spec_java_spring_final_v2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotBlank(message = "Не может быть пустым")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Не может быть пустым")
    @Email
    private String email;

    private Set<SubscriptionDto> subscriptions;
}

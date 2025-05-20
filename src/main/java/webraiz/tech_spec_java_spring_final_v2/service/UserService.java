package webraiz.tech_spec_java_spring_final_v2.service;

import webraiz.tech_spec_java_spring_final_v2.dto.UserDto;
import webraiz.tech_spec_java_spring_final_v2.model.User;

public interface UserService {
    User createUser(UserDto dto);

    User getUser(Long id);

    User updateUser(Long id, UserDto dto);

    void deleteUser(Long id);
}

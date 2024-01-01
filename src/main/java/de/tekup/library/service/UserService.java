package de.tekup.library.service;


import de.tekup.library.dto.request.UserRequest;
import de.tekup.library.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    
    List<UserResponse> findUsers();
    UserResponse saveUser(UserRequest userRequest);
    UserResponse findUserByUsername(String username);
}


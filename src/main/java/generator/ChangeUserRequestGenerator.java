package generator;

import dto.ChangeUserRequest;
import dto.CreateUserRequest;

public class ChangeUserRequestGenerator {

    public static ChangeUserRequest from(CreateUserRequest createUserRequest) {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest();
        changeUserRequest.setEmail(createUserRequest.getEmail());
        changeUserRequest.setPassword(createUserRequest.getPassword());
        changeUserRequest.setName(createUserRequest.getName());
        return changeUserRequest;
    }
}

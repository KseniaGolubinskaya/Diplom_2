package generator;

import dto.CreateUserRequest;
import dto.LoginUserRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class LoginUserRequestGenerator {
    public static LoginUserRequest from(CreateUserRequest createUserRequest) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(createUserRequest.getEmail());
        loginUserRequest.setPassword(createUserRequest.getPassword());
        return loginUserRequest;
    }

    public static LoginUserRequest byInvalidEmail(String password) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("ariika-87@mail.ru");
        loginUserRequest.setPassword(password);
        return loginUserRequest;
    }

    public static LoginUserRequest byInvalidPassword(String email) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(email);
        loginUserRequest.setPassword("458977210");
        return loginUserRequest;
    }
}

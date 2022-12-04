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

    public static LoginUserRequest byRandomEmail(String password) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(RandomStringUtils.randomAlphabetic(10)+'@'+RandomStringUtils.randomAlphabetic(5)+'.'+RandomStringUtils.randomAlphabetic(3));
        loginUserRequest.setPassword(password);
        return loginUserRequest;
    }

    public static LoginUserRequest byRandomPassword(String email) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(email);
        loginUserRequest.setPassword(RandomStringUtils.randomAlphanumeric(10));
        return loginUserRequest;
    }
}

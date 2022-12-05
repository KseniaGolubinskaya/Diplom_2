package generator;

import dto.ChangeUserRequest;
import dto.CreateUserRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class ChangeUserRequestGenerator {
    public static ChangeUserRequest changeAllFieldRandomUserRequest() {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest();
        changeUserRequest.setEmail(RandomStringUtils.randomAlphabetic(20));
        changeUserRequest.setPassword(RandomStringUtils.randomAlphanumeric(10));
        changeUserRequest.setName(RandomStringUtils.randomAlphabetic(10));
        return changeUserRequest;
    }

    public static ChangeUserRequest from(CreateUserRequest createUserRequest) {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest();
        changeUserRequest.setEmail(createUserRequest.getEmail());
        changeUserRequest.setPassword(createUserRequest.getPassword());
        changeUserRequest.setName(createUserRequest.getName());
        return changeUserRequest;
    }

    public static ChangeUserRequest changePasswordFieldRandomUserRequest() {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest();
        changeUserRequest.setEmail("ksenia-86@yandex.ru");
        changeUserRequest.setPassword(RandomStringUtils.randomAlphanumeric(10));
        changeUserRequest.setName("Ksenia");
        return changeUserRequest;
    }

    public static ChangeUserRequest changeNameFieldRandomUserRequest() {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest();
        changeUserRequest.setEmail("ksenia-86@yandex.ru");
        changeUserRequest.setPassword("458977215");
        changeUserRequest.setName(RandomStringUtils.randomAlphabetic(10));
        return changeUserRequest;
    }
}

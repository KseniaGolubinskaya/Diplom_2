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

    public static ChangeUserRequest changeEmailFieldRandomUserRequest() {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest();
        changeUserRequest.setEmail(RandomStringUtils.randomAlphabetic(20));
        changeUserRequest.setPassword("458977215");
        changeUserRequest.setName("Ksenia");
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

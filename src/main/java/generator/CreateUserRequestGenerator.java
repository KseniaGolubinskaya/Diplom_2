package generator;

import dto.CreateUserRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class CreateUserRequestGenerator {
    public static CreateUserRequest createRandomUniqueUserRequest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(RandomStringUtils.randomAlphabetic(10)+'@'+RandomStringUtils.randomAlphabetic(5)+'.'+RandomStringUtils.randomAlphabetic(3));
        createUserRequest.setPassword(RandomStringUtils.randomAlphanumeric(10));
        createUserRequest.setName(RandomStringUtils.randomAlphabetic(10));
        return createUserRequest;
    }

    public static CreateUserRequest createUserWithoutEmailRequest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setPassword(RandomStringUtils.randomAlphanumeric(10));
        createUserRequest.setName(RandomStringUtils.randomAlphabetic(10));
        return createUserRequest;
    }

    public static CreateUserRequest createUserWithoutPasswordRequest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(RandomStringUtils.randomAlphabetic(20));
        createUserRequest.setName(RandomStringUtils.randomAlphabetic(10));
        return createUserRequest;
    }

    public static CreateUserRequest createUserWithoutNameRequest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(RandomStringUtils.randomAlphabetic(20));
        createUserRequest.setPassword(RandomStringUtils.randomAlphanumeric(10));
        return createUserRequest;
    }
}

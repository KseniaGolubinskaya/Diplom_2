import client.UserRestClient;
import dto.CreateUserRequest;
import dto.LoginUserRequest;
import generator.CreateUserRequestGenerator;
import generator.LoginUserRequestGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestsHelper {
    public static CreateUserRequest createUser(UserRestClient userRestClient) {
        CreateUserRequest randomCreateUserRequest = CreateUserRequestGenerator.createRandomUniqueUserRequest();
        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
        createUniqueUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
        return randomCreateUserRequest;
    }

    public static LoginUserRequest loginUser(UserRestClient userRestClient, CreateUserRequest createUserRequest) {
        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(createUserRequest);
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);
        return loginUserRequest;
    }
}

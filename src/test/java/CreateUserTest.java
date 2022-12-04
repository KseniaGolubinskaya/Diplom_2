import client.UserRestClient;
import dto.CreateUserRequest;
import dto.LoginUserRequest;
import generator.LoginUserRequestGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static generator.CreateUserRequestGenerator.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {
    private UserRestClient userRestClient;

    @Before
    public void setUp() {
        userRestClient = new UserRestClient();
    }

    @After
    public void tearDown() {
        if(userRestClient.getToken() == null) {
            return;
        }
           userRestClient.deleteUser()
                    .assertThat()
                    .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Проверка создания уникального пользователя и его успешной авторизации")
    public void createUniqueUserSuccessTest() {
        // Arrange
        CreateUserRequest randomCreateUserRequest = createRandomUniqueUserRequest();
        // Act
        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
        // Assert
        createUniqueUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // login user
        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);
    }

    @Test
    @DisplayName("Проверка создания пользователя, который уже зарегистрирован")
    public void createUserThatAlreadyExistFailedTest() {
        // Arrange
        CreateUserRequest randomCreateUserRequest = createRandomUniqueUserRequest();
        // Act
        ValidatableResponse createUserResponse = userRestClient.createUser(randomCreateUserRequest);

        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
        userRestClient.getUserToken(loginUserRequest);

        ValidatableResponse createSameUserResponse = userRestClient.createUser(randomCreateUserRequest);

        // Assert
        createUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        createSameUserResponse
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .and()
                .body("message", equalTo("User already exists"));
    }

        @Test
        @DisplayName("Проверка создания пользователя без поля email")
        public void createUserWithoutFieldEmailFailedTest () {
            // Arrange
            CreateUserRequest randomCreateUserRequest = createUserWithoutEmailRequest();
            // Act
            ValidatableResponse createUserResponse = userRestClient.createUser(randomCreateUserRequest);
            // Assert
            createUserResponse
                    .assertThat()
                    .statusCode(SC_FORBIDDEN)
                    .and()
                    .body("message", equalTo("Email, password and name are required fields"));
        }

    @Test
    @DisplayName("Проверка создания пользователя без поля password")
    public void createUserWithoutFieldPasswordFailedTest () {
        // Arrange
        CreateUserRequest randomCreateUserRequest = createUserWithoutPasswordRequest();
        // Act
        ValidatableResponse createUserResponse = userRestClient.createUser(randomCreateUserRequest);
        // Assert
        createUserResponse
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Проверка создания пользователя без поля name")
    public void createUserWithoutFieldNameFailedTest () {
        // Arrange
        CreateUserRequest randomCreateUserRequest = createUserWithoutNameRequest();
        // Act
        ValidatableResponse createUserResponse = userRestClient.createUser(randomCreateUserRequest);
        // Assert
        createUserResponse
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
import client.UserRestClient;
import dto.CreateUserRequest;
import dto.LoginUserRequest;
import generator.CreateUserRequestGenerator;
import generator.LoginUserRequestGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserTest {
    private UserRestClient userRestClient;

    @Before
    public void setUp() {
        userRestClient = new UserRestClient();
    }

    @After
    public void tearDown() {
        if (userRestClient.getToken() == null) {
            return;
        }
        userRestClient.deleteUser()
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Проверка, что система вернёт ошибку, если неправильно указать email при авторизации")
    public void loginCourierWithInvalidEmailFailedTest() {
        // Создание пользователя
        // Arrange
        CreateUserRequest randomCreateUserRequest = CreateUserRequestGenerator.createRandomUniqueUserRequest();
        // Act
        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
        // Assert
        createUniqueUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // Успешная авторизация
        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);

        // Авторизация с неверным email
        LoginUserRequest randomLoginUserRequest = LoginUserRequestGenerator.byRandomEmail(loginUserRequest.getPassword());
        ValidatableResponse loginUserByInvalidEmailResponse = userRestClient.loginUser(randomLoginUserRequest);
        loginUserByInvalidEmailResponse
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Проверка, что система вернёт ошибку, если неправильно указать пароль при авторизации")
    public void loginCourierWithInvalidPasswordFailedTest() {
        // Создание пользователя
        // Arrange
        CreateUserRequest randomCreateUserRequest = CreateUserRequestGenerator.createRandomUniqueUserRequest();
        // Act
        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
        // Assert
        createUniqueUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // Успешная авторизация
        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);

        // Авторизация с неверным password
        LoginUserRequest randomLoginUserRequest = LoginUserRequestGenerator.byRandomPassword(loginUserRequest.getEmail());
        ValidatableResponse loginUserByInvalidPasswordResponse = userRestClient.loginUser(randomLoginUserRequest);
        loginUserByInvalidPasswordResponse
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}

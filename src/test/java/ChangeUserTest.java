import client.UserRestClient;
import dto.ChangeUserRequest;
import dto.CreateUserRequest;
import dto.LoginUserRequest;
import generator.ChangeUserRequestGenerator;
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

public class ChangeUserTest {
    private UserRestClient userRestClient;
    private CreateUserRequest randomCreateUserRequest;
    private LoginUserRequest loginUserRequest;

    @Before
    public void setUp() {
        userRestClient = new UserRestClient();
        // Create user
        randomCreateUserRequest = CreateUserRequestGenerator.createRandomUniqueUserRequest();
        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
        createUniqueUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // login user
        loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);
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
    @DisplayName("Проверка изменения почты авторизованного пользователя")
    public void changeAuthUserEmailSuccessTest() {
        // change user email
        ChangeUserRequest changeEmailUserRequest = ChangeUserRequestGenerator.from(randomCreateUserRequest);
        changeEmailUserRequest.setEmail("new_email12@yandex.ru");
        // Act
        ValidatableResponse changeUserEmailResponse = userRestClient.changeUser(changeEmailUserRequest);
        // Assert
        changeUserEmailResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // Проверка, что вернулся изменённый email
        ValidatableResponse getUserResponse = userRestClient.getUser();
        getUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("user.email", equalTo("new_email12@yandex.ru"));
    }

    @Test
    @DisplayName("Проверка изменения пароля авторизованного пользователя")
    public void changeAuthUserPasswordSuccessTest() {
        // change user email
        ChangeUserRequest changePasswordRequest = ChangeUserRequestGenerator.from(randomCreateUserRequest);
        changePasswordRequest.setPassword("7tr65trfyguihjkol");
        // Act
        ValidatableResponse changePasswordResponse = userRestClient.changeUser(changePasswordRequest);
        // Assert
        changePasswordResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // Проверка логина пользователя с новым паролем
        loginUserRequest.setPassword("7tr65trfyguihjkol");
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);
    }

    @Test
    @DisplayName("Проверка изменения имени авторизованного пользователя")
    public void changeAuthUserNameSuccessTest() {
        // change user email
        ChangeUserRequest changeNameRequest = ChangeUserRequestGenerator.from(randomCreateUserRequest);
        changeNameRequest.setName("John");
        // Act
        ValidatableResponse changeNameResponse = userRestClient.changeUser(changeNameRequest);
        // Assert
        changeNameResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // Проверка, что вернулось изменённое имя
        ValidatableResponse getUserResponse = userRestClient.getUser();
        getUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("user.name", equalTo("John"));
    }

    @Test
    @DisplayName("Проверка изменения данных неавторизованного пользователя")
    public void changeAuthUserNameFailedTest() {
        String oldToken = userRestClient.getToken();
        userRestClient.setToken("");
        // change user
        ChangeUserRequest changeUserRequest = ChangeUserRequestGenerator.from(randomCreateUserRequest);
        // Act
        ValidatableResponse changeUserResponse = userRestClient.changeUser(changeUserRequest);
        // Assert
        changeUserResponse
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
        userRestClient.setToken(oldToken);
    }
}

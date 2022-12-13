package client;

import dto.ChangeUserRequest;
import dto.CreateUserRequest;
import dto.LoginUserRequest;
import dto.LoginUserResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserRestClient extends RestClient {
    private static final String USER_CREATE = "api/auth/register";
    private static final String USER_LOGIN = "api/auth/login";
    private static final String USER_CHANGE = "api/auth/user";
    private static final String USER_DELETE = "api/auth/user";
    private static final String USER_GET = "api/auth/user";

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(CreateUserRequest createUserRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(createUserRequest)
                .post(USER_CREATE)
                .then();
    }

    @Step("Получение токена пользователя")
    public String getUserToken(LoginUserRequest loginUserRequest) {
        token = given()
                .spec(getDefaultRequestSpec())
                .body(loginUserRequest)
                .post(USER_LOGIN)
                .body().as(LoginUserResponse.class).getAccessToken();
        return token;
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(LoginUserRequest loginUserRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(loginUserRequest)
                .post(USER_LOGIN)
                .then();
    }

    @Step("Изменение данных пользователя")
    public ValidatableResponse changeUser(ChangeUserRequest changeUserRequest) {
        return given()
                .header("Authorization", token)
                .spec(getDefaultRequestSpec())
                .body(changeUserRequest)
                .patch(USER_CHANGE)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser() {
        return given()
                .header("Authorization", token)
                .spec(getDefaultRequestSpec())
                .delete(USER_DELETE)
                .then();
    }

    @Step("Получение данных пользователя")
    public ValidatableResponse getUser() {
        return given()
                .header("Authorization", token)
                .spec(getDefaultRequestSpec())
                .get(USER_GET)
                .then();
    }
}

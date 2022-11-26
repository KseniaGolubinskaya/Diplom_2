//import client.UserRestClient;
//import dto.CreateUserRequest;
//import dto.LoginUserRequest;
//import generator.LoginUserRequestGenerator;
//import io.qameta.allure.junit4.DisplayName;
//import io.restassured.response.ValidatableResponse;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static generator.CreateUserRequestGenerator.createRandomUniqueUserRequest;
//import static org.apache.http.HttpStatus.*;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.notNullValue;
//
//public class LoginUserTest {
//    private UserRestClient userRestClient;
//    private String email;
//
//    @Before
//    public void setUp() {
//        userRestClient = new UserRestClient();
//    }
//
//    @After
//    public void tearDown() {
//        if (email = ) {
//            userRestClient.deleteUser(email)
//                    .assertThat()
//                    .body("ok", equalTo(true));
//        }
//    }
//
//    @Test
//    @DisplayName("Проверка успешной авторизации пользователя")
//    public void loginCourierSuccessTest() {
//        CreateUserRequest randomCreateUserRequest = createRandomUniqueUserRequest();
//        // Act
//        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
//        // Assert
//        createUniqueUserResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .and()
//                .body("ok", equalTo(true));
//
//        // login user
//        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
//        email = userRestClient.loginUser(loginUserRequest)
//                .assertThat()
//                .statusCode(SC_OK)
//                .and()
//                .body("email", notNullValue())
//                .extract()
//                .path("email");
//    }
//
//    @Test
//    @DisplayName("Проверка, что система вернёт ошибку, если неправильно указать email")
//    public void loginCourierWithInvalidEmailFailedTest() {
//        CreateUserRequest randomCreateUserRequest = createRandomUniqueUserRequest();
//        // Act
//        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
//        // Assert
//        createUniqueUserResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .and()
//                .body("ok", equalTo(true));
//
//        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
//        email = userRestClient.loginUser(loginUserRequest)
//                .assertThat()
//                .statusCode(SC_OK)
//                .and()
//                .body("email", notNullValue())
//                .extract()
//                .path("email");
//
//        loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
//        loginUserRequest.setEmail("invalid");
//        userRestClient.loginUser(loginUserRequest)
//                .assertThat()
//                .statusCode(SC_UNAUTHORIZED)
//                .and()
//                .body("message", equalTo("email or password are incorrect"));
//    }
//
//    @Test
//    @DisplayName("Проверка, что система вернёт ошибку, если неправильно указать пароль")
//    public void loginCourierWithInvalidPasswordFailedTest() {
//        CreateUserRequest randomCreateUserRequest = createRandomUniqueUserRequest();
//        // Act
//        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
//        // Assert
//        createUniqueUserResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .and()
//                .body("ok", equalTo(true));
//
//        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
//        email = userRestClient.loginUser(loginUserRequest)
//                .assertThat()
//                .statusCode(SC_OK)
//                .and()
//                .body("email", notNullValue())
//                .extract()
//                .path("email");
//
//        loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
//        loginUserRequest.setPassword("invalid");
//        userRestClient.loginUser(loginUserRequest)
//                .assertThat()
//                .statusCode(SC_UNAUTHORIZED)
//                .and()
//                .body("message", equalTo("email or password are incorrect"));
//    }
//}

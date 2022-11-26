//import client.UserRestClient;
//import dto.ChangeUserRequest;
//import dto.CreateUserRequest;
//import dto.LoginUserRequest;
//import generator.LoginUserRequestGenerator;
//import io.qameta.allure.junit4.DisplayName;
//import io.restassured.response.ValidatableResponse;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static generator.ChangeUserRequestGenerator.*;
//import static generator.CreateUserRequestGenerator.createRandomUniqueUserRequest;
//import static org.apache.http.HttpStatus.*;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.notNullValue;
//
//public class ChangeUserTest {
//    private UserRestClient userRestClient;
//    private String email;
//    private String password;
//    private String name;
//
//    @Before
//    public void setUp() {
//        userRestClient = new UserRestClient();
//    }
//
//    @After
//    public void tearDown() {
//        if (email =) {
//            userRestClient.deleteUser(email)
//                    .assertThat()
//                    .body("ok", equalTo(true));
//        }
//    }
//
//    @Test
//    @DisplayName("Проверка изменения почты авторизованного пользователя.")
//    public void changeAuthUserEmailSuccessTest() {
//        // Arrange
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
//
//        // change user
//        ChangeUserRequest randomChangeUserRequest = changeEmailFieldRandomUserRequest();
//        // Act
//        ValidatableResponse changeUserEmailResponse = userRestClient.changeUser();
//        // Assert
//        changeUserEmailResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .and()
//                .body("ok", equalTo(true));
//    }
//
//    @Test
//    @DisplayName("Проверка изменения пароля авторизованного пользователя.")
//    public void changeAuthUserPasswordSuccessTest() {
//        // Arrange
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
//
//        // change user
//        ChangeUserRequest randomChangeUserRequest = changePasswordFieldRandomUserRequest();
//        // Act
//        ValidatableResponse changeUserPasswordResponse = userRestClient.changeUser();
//        // Assert
//        changeUserPasswordResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .and()
//                .body("ok", equalTo(true));
//    }
//
//    @Test
//    @DisplayName("Проверка изменения имени авторизованного пользователя.")
//    public void changeAuthUserNameSuccessTest() {
//        // Arrange
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
//
//        // change user
//        ChangeUserRequest randomChangeUserRequest = changeNameFieldRandomUserRequest();
//        // Act
//        ValidatableResponse changeUserNameResponse = userRestClient.changeUser();
//        // Assert
//        changeUserNameResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .and()
//                .body("ok", equalTo(true));
//    }
//
//    @Test
//    @DisplayName("Проверка изменения почты неавторизованного пользователя.")
//    public void changeAuthUserNameFailedTest() {
//        // Arrange
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
//        // change user
//        ChangeUserRequest randomChangeUserRequest = changeEmailFieldRandomUserRequest();
//        // Act
//        ValidatableResponse changeUserEmailResponse = userRestClient.changeUser();
//        // Assert
//        changeUserEmailResponse
//                .assertThat()
//                .statusCode(SC_UNAUTHORIZED)
//                .and()
//                .body("message", equalTo("You should be authorised"));
//    }
//
//    @Test
//    @DisplayName("Проверка изменения пароля неавторизованного пользователя.")
//    public void changeAuthUserNameFailedTest() {
//        // Arrange
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
//        // change user
//        ChangeUserRequest randomChangeUserRequest = changePasswordFieldRandomUserRequest();
//        // Act
//        ValidatableResponse changeUserPasswordResponse = userRestClient.changeUser();
//        // Assert
//        changeUserPasswordResponse
//                .assertThat()
//                .statusCode(SC_UNAUTHORIZED)
//                .and()
//                .body("message", equalTo("You should be authorised"));
//    }
//
//    @Test
//    @DisplayName("Проверка изменения имени неавторизованного пользователя.")
//    public void changeAuthUserNameFailedTest() {
//        // Arrange
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
//        // change user
//        ChangeUserRequest randomChangeUserRequest = changeNameFieldRandomUserRequest();
//        // Act
//        ValidatableResponse changeUserNameResponse = userRestClient.changeUser();
//        // Assert
//        changeUserNameResponse
//                .assertThat()
//                .statusCode(SC_UNAUTHORIZED)
//                .and()
//                .body("message", equalTo("You should be authorised"));
//    }
//}

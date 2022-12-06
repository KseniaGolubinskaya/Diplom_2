import client.IngredientsClient;
import client.OrderRestClient;
import client.UserRestClient;
import dto.CreateOrderRequest;
import dto.CreateUserRequest;
import dto.GetIngredientsResponse;
import dto.LoginUserRequest;
import generator.LoginUserRequestGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static generator.CreateUserRequestGenerator.createRandomUniqueUserRequest;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class CreateOrderTest {
    OrderRestClient orderRestClient = new OrderRestClient();
    UserRestClient userRestClient = new UserRestClient();
    IngredientsClient ingredientsClient = new IngredientsClient();
    private GetIngredientsResponse ingredients;

    @Before
    public void setUp() {
        // create user
        CreateUserRequest randomCreateUserRequest = createRandomUniqueUserRequest();
        ValidatableResponse createUniqueUserResponse = userRestClient.createUser(randomCreateUserRequest);
        createUniqueUserResponse
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));

        // login user
        LoginUserRequest loginUserRequest = LoginUserRequestGenerator.from(randomCreateUserRequest);
        String token = userRestClient.getUserToken(loginUserRequest);
        Assert.assertNotNull(token);

        // get ingredients list
        ingredients = ingredientsClient.getIngredients();
        Assert.assertThat(ingredients.getData().length, greaterThan(0));
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
    @DisplayName("Проверка создания заказа с ингредиентами авторизованным пользователем")
    public void createOrderWithIngredientsAuthUserSuccessTest() {
        // Arrange
        orderRestClient.setToken(userRestClient.getToken());
        List<String> ingredientsForOrder = new ArrayList<String>();
        ingredientsForOrder.add(ingredients.getData()[0].get_id());
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredientsForOrder);
        // Act
        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
        // Assert
        createOrderResponse
                .assertThat()
                .statusCode(SC_OK)
                .body("order.number", notNullValue());
    }

    /**
     * Тест падает, так как возвращается код ответа 200 ОК вместо 401 Unauthorized
     */
    @Test
    @DisplayName("Проверка создания заказа неавторизованным пользователем")
    public void createOrderNotAuthUserFailedTest() {
        // Arrange
        orderRestClient.setToken("");
        List<String> ingredientsForOrder = new ArrayList<String>();
        ingredientsForOrder.add(ingredients.getData()[0].get_id());
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredientsForOrder);
        // Act
        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
        // Assert
        createOrderResponse
                .assertThat()
                .statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Проверка создания заказа без ингредиентов")
    public void createOrderWithoutIngredientsFailedTest() {
        // Arrange
        orderRestClient.setToken(userRestClient.getToken());
        List<String> ingredientsForOrder = new ArrayList<String>();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredientsForOrder);
        // Act
        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
        // Assert
        createOrderResponse
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Проверка создания заказа с неверным id ингредиентов")
    public void createOrderIncorrectIngredientsIdFailedTest() {
        // Arrange
        orderRestClient.setToken(userRestClient.getToken());
        List<String> ingredientsForOrder = new ArrayList<String>();
        ingredientsForOrder.add("l9uy76r44e9y765e4ed");
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredientsForOrder);
        // Act
        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
        // Assert
        createOrderResponse
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}

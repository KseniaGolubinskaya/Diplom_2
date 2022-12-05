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
        if(userRestClient.getToken() == null) {
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

//    @Test
//    @DisplayName("Проверка создания заказа неавторизованным пользователем")
//    public void createOrderNotAuthUserFailedTest() {
//        // Arrange
//        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
//        // Act
//        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
//        // Assert
//        number = createOrderResponse
//                .assertThat()
//                .statusCode(SC_OK)
//                .body("number", notNullValue())
//                .extract()
//                .path("number");
//    }
//
//    @Test
//    @DisplayName("Проверка создания заказа без ингредиентов")
//    public void createOrderWithoutIngredientsFailedTest() {
//        // Arrange
//        CreateOrderRequest createOrderRequest = new CreateOrderRequest(Ingredients);
//        // Act
//        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
//        // Assert
//        number = createOrderResponse
//                .assertThat()
//                .statusCode(SC_BAD_REQUEST)
//                .body("number", notNullValue())
//                .extract()
//                .path("message", equalTo("Ingredient ids must be provided"));
//    }
//
//    @Test
//    @DisplayName("Проверка создания заказа с неверным ингредиентов")
//    public void createOrderWithoutIngredientsFailedTest() {
//        // Arrange
//        CreateOrderRequest createOrderRequest = new CreateOrderRequest(Ingredients);
//        // Act
//        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
//        // Assert
//        number = createOrderResponse
//                .assertThat()
//                .statusCode(SC_INTERNAL_SERVER_ERROR)
//                .body("number", notNullValue())
//                .extract();
//    }
}

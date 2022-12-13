import client.IngredientsClient;
import client.OrderRestClient;
import client.UserRestClient;
import dto.CreateOrderRequest;
import dto.CreateUserRequest;
import dto.GetIngredientsResponse;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.greaterThan;

public class GetOrdersTest {
    OrderRestClient orderRestClient = new OrderRestClient();
    UserRestClient userRestClient = new UserRestClient();
    IngredientsClient ingredientsClient = new IngredientsClient();
    private GetIngredientsResponse ingredients;
    private int orderNumber;

    @Before
    public void setUp() {
        CreateUserRequest randomCreateUserRequest = TestsHelper.createUser(userRestClient);
        TestsHelper.loginUser(userRestClient, randomCreateUserRequest);

        // get ingredients list
        ingredients = ingredientsClient.getIngredients();
        Assert.assertThat(ingredients.getData().length, greaterThan(0));

        // create order
        orderRestClient.setToken(userRestClient.getToken());
        List<String> ingredientsForOrder = new ArrayList<String>();
        ingredientsForOrder.add(ingredients.getData()[0].get_id());
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredientsForOrder);

        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);

        orderNumber = createOrderResponse
                .assertThat()
                .statusCode(SC_OK)
                .body("order.number", notNullValue())
                .extract()
                .path("order.number");
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
    @DisplayName("Проверка получения заказов авторизованного пользователя")
    public void getOrderListAuthUserSuccessTest() {
        ValidatableResponse getOrdersListResponse = orderRestClient.getOrderList();

        getOrdersListResponse
                .assertThat()
                .statusCode(SC_OK)
                .body("orders", not(emptyArray()))
                .and()
                .body("orders[0].number", equalTo(orderNumber));
    }

    @Test
    @DisplayName("Проверка получения заказов неавторизованного пользователя")
    public void getOrderListNotAuthUserFaledTest() {
        orderRestClient.setToken("");

        ValidatableResponse getOrdersListResponse = orderRestClient.getOrderList();

        getOrdersListResponse
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }
}

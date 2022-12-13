package client;

import dto.CreateOrderRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderRestClient extends RestClient {
    private static final String ORDER_CREATE = "api/orders";
    private static final String GET_LIST_ORDER = "api/orders";

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    @Step("Создание заказа")
    public ValidatableResponse createOrder(CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .header("Authorization", token)
                .body(createOrderRequest)
                .post(ORDER_CREATE)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getDefaultRequestSpec())
                .header("Authorization", token)
                .get(GET_LIST_ORDER)
                .then();
    }
}

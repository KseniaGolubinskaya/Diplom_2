package client;

import dto.CreateOrderRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderRestClient extends RestClient {
    private static final String ORDER_CREATE = "orders";
    private static final String GET_LIST_ORDER = "orders";
    private static final String DELETE_ORDER = "orders";

    /**
     * create order
     */
    @Step("Создание заказа")
    public ValidatableResponse createOrder(CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(createOrderRequest)
                .post(ORDER_CREATE)
                .then();
    }

    /**
     * get order list
     */
    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getDefaultRequestSpec())
                .get(GET_LIST_ORDER)
                .then();
    }

//    /**
//     * get order list
//     */
//    @Step("Удаление заказа")
//    public ValidatableResponse cancelOrder(Integer number) {
//        return given()
//                .spec(getDefaultRequestSpec())
//                .get(DELETE_ORDER)
//                .then();
//    }
}

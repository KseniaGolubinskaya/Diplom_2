//import client.OrderRestClient;
//import io.restassured.response.ValidatableResponse;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.apache.http.HttpStatus.SC_OK;
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.Matchers.emptyArray;
//
//public class GetOrdersTest {
//    private OrderRestClient orderRestClient;
//
//    @Before
//    public void setUp() {
//        orderRestClient = new OrderRestClient();
//    }
//
//    @Test
//    @DisplayName("Проверка возврата заказов авторизованного пользователя")
//    public void getOrderListAuthUserSuccessTest() {
//        // Act
//        ValidatableResponse getOrdersListResponse = orderRestClient.getOrderList();
//        // Assert
//        getOrdersListResponse
//                .assertThat()
//                .statusCode(SC_OK)
//                .body("orders", not(emptyArray()));
//    }
//
//    @Test
//    @DisplayName("Проверка возврата заказов неавторизованного пользователя")
//    public void getOrderListNotAuthUserFaledTest() {
//        // Act
//        ValidatableResponse getOrdersListResponse = orderRestClient.getOrderList();
//        // Assert
//        getOrdersListResponse
//                .assertThat()
//                .statusCode(SC_OK)
//                .body("orders", not(emptyArray()));
//    }
//}

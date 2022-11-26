//import client.OrderRestClient;
//import dto.CreateOrderRequest;
//import generator.CreateOrderRequestGenerator;
//import io.restassured.response.ValidatableResponse;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.apache.http.HttpStatus.*;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.notNullValue;
//
//public class CreateOrderTest {
//    OrderRestClient orderRestClient = new OrderRestClient();
//    String[] Ingredients;
//    Integer number;
//
//    @Before
//    public void setUp() {
//        orderRestClient = new OrderRestClient();
//    }
//
////    @After
////    public void tearDown() {
////        if (number != null) {
////            orderRestClient.cancelOrder(new CancelOrderRequest(number))
////                    .assertThat()
////                    .body("ok", equalTo(true));
////        }
////    }
//
//    @Test
//    @DisplayName("Проверка создания заказа с ингредиентами авторизованным пользователем")
//    public void createOrderWithIngredientsAuthUserSuccessTest() {
//        // Arrange
//        CreateOrderRequest createOrderRequest = new CreateOrderRequest(Ingredients);
//        // Act
//        ValidatableResponse createOrderResponse = orderRestClient.createOrder(createOrderRequest);
//        // Assert
//        number = createOrderResponse
//                .assertThat()
//                .statusCode(SC_CREATED)
//                .body("number", notNullValue())
//                .extract()
//                .path("number");
//    }
//
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
//}

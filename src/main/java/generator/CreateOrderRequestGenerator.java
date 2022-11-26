package generator;

import dto.CreateOrderRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequestGenerator {

    public static CreateOrderRequest createRandomOrderRequest() {
        List<String> ingredients = new ArrayList<>();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredients);
       // createOrderRequest.setIngredients(RandomStringUtils.randomNumeric(24));
        return createOrderRequest;
    }

    public static CreateOrderRequest createOrderWithoutIngredientsRequest() {
       // CreateOrderRequest createOrderRequest = new CreateOrderRequest();
       // createOrderRequest.setIngredients("");
        return null;
    }

    public static CreateOrderRequest createOrderWithInvalidHashRequest() {
        // CreateOrderRequest createOrderRequest = new CreateOrderRequest();
       // createOrderRequest.setIngredients(RandomStringUtils.randomNumeric(24));
        return null;
    }
}

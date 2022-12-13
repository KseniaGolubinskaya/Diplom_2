package client;

import dto.GetIngredientsResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class IngredientsClient extends RestClient {
    private static final String INGREDIENTS_GET = "api/ingredients";

    @Step("Получение списка ингредиентов")
    public GetIngredientsResponse getIngredients() {
        return given()
                .spec(getDefaultRequestSpec())
                .get(INGREDIENTS_GET)
                .body().as(GetIngredientsResponse.class);
    }
}

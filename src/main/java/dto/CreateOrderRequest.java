package dto;

import java.util.List;

public class CreateOrderRequest {
    List<String> Ingredients;

    public CreateOrderRequest(List<String> ingredients) {
        Ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        Ingredients = ingredients;
    }
}

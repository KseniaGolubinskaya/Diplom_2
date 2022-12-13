package dto;

public class GetIngredientsResponse {
    private String success;
    private IngredientData[] data;

    public GetIngredientsResponse(String success, IngredientData[] data) {
        this.success = success;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public IngredientData[] getData() {
        return data;
    }

    public void setData(IngredientData[] data) {
        this.data = data;
    }
}


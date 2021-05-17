package kz.reserve.backend.payload.response;

import org.json.JSONArray;

public class JSONArrayResponse {

    private JSONArray restaurants;

    public JSONArrayResponse(JSONArray restaurants) {
        this.restaurants = restaurants;
    }

    public JSONArray getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(JSONArray restaurants) {
        this.restaurants = restaurants;
    }
}

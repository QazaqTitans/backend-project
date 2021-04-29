package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Meal;


import java.util.List;

public class MealResponse {
    private List<Meal> mealList;
    public MealResponse(List<Meal> mealList){this.mealList=mealList;}
    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }
}

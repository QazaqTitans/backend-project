package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.SortEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SearchRequest {
    private String filter;

    @NotNull
    private Integer minPrice;

    @NotNull
    private Integer maxPrice;

    private Integer stars = 0;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    @NotNull
    private Integer person;

    private SortEnum sortEnum;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public SortEnum getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(SortEnum sortEnum) {
        this.sortEnum = sortEnum;
    }
}
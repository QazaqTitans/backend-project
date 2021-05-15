package kz.reserve.backend.payload.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class MealRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String features;

    @NotNull
    private double price;

    private boolean isFinished = false;

    @NotNull
    private Long time;

    @NotNull
    private Long categoryId;

//    private MultipartFile multipartFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

//    public MultipartFile getMultipartFile() {
//        return multipartFile;
//    }
//
//    public void setMultipartFile(MultipartFile multipartFile) {
//        this.multipartFile = multipartFile;
//    }
}

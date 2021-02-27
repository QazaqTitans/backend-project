package kz.reserve.backend.payload.response;

import kz.reserve.backend.model.ErrorModel;

import java.util.List;

public class ErrorResponse {
    private List<ErrorModel> errorMessage;

    public ErrorResponse(List<ErrorModel> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ErrorModel> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ErrorModel> errorMessage) {
        this.errorMessage = errorMessage;
    }
}

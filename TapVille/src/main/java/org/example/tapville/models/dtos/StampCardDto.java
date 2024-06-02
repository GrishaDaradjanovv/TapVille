package org.example.tapville.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StampCardDto {

    private int numberOfStamps;
    private String qrCodePath;

    public StampCardDto() {
    }

    public int getNumberOfStamps() {
        return numberOfStamps;
    }

    public void setNumberOfStamps(int numberOfStamps) {
        this.numberOfStamps = numberOfStamps;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }
}

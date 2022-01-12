package com.softwaresecurityapplication.Model.Payload.request;

import javax.validation.constraints.NotBlank;

public class FileAnalyseRequest {
    @NotBlank
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

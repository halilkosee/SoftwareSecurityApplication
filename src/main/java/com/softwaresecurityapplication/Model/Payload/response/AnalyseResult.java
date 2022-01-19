package com.softwaresecurityapplication.Model.Payload.response;

public class AnalyseResult {
    private int warningRate;
    private String vulnerabilityType;
    private int detectedLineNumber;
    private String detectedLine;
    private String successCode;

    public AnalyseResult(int warningRate, String vulnerabilityType, int detectedLineNumber, String detectedLine) {
        this.warningRate = warningRate;
        this.vulnerabilityType = vulnerabilityType;
        this.detectedLineNumber = detectedLineNumber;
        this.detectedLine = detectedLine;
    }

    public AnalyseResult(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "AnalyseResult{" +
                "warningRate=" + warningRate +
                ", vulnerabilityType=" + vulnerabilityType +
                ", detectedLineNumber=" + detectedLineNumber +
                ", detectedLine='" + detectedLine + '\'' +
                '}';
    }

    public int getWarningRate() {
        return warningRate;
    }

    public void setWarningRate(int warningRate) {
        this.warningRate = warningRate;
    }

    public String getVulnerabilityType() {
        return vulnerabilityType;
    }

    public void setVulnerabilityType(String vulnerabilityType) {
        this.vulnerabilityType = vulnerabilityType;
    }

    public int getDetectedLineNumber() {
        return detectedLineNumber;
    }

    public void setDetectedLineNumber(int detectedLineNumber) {
        this.detectedLineNumber = detectedLineNumber;
    }

    public String getDetectedLine() {
        return detectedLine;
    }

    public void setDetectedLine(String detectedLine) {
        this.detectedLine = detectedLine;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }
}

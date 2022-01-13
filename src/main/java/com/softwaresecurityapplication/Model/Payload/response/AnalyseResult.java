package com.softwaresecurityapplication.Model.Payload.response;

import com.softwaresecurityapplication.Enum.EVulnerabilityType;

public class AnalyseResult {
    private double warningRate;
    private String vulnerabilityType;
    private int detectedLineNumber;
    private String detectedLine;

    public AnalyseResult(double warningRate, String vulnerabilityType, int detectedLineNumber, String detectedLine) {
        this.warningRate = warningRate;
        this.vulnerabilityType = vulnerabilityType;
        this.detectedLineNumber = detectedLineNumber;
        this.detectedLine = detectedLine;
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

    public double getWarningRate() {
        return warningRate;
    }

    public void setWarningRate(double warningRate) {
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
}
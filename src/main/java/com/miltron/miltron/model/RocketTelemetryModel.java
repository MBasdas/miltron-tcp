package com.miltron.miltron.model;


public class RocketTelemetryModel {

    private byte packageStartByte;
    private String rocketId;
    private byte packageNumber;
    private byte packageSize;
    private float altitude;
    private float speed;
    private float acceleration;
    private float thrust;
    private float temprature;
    private short footer;

    public byte getPackageStartByte() {
        return packageStartByte;
    }

    public void setPackageStartByte(byte packageStartByte) {
        this.packageStartByte = packageStartByte;
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
    }

    public byte getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(byte packageNumber) {
        this.packageNumber = packageNumber;
    }

    public byte getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(byte packageSize) {
        this.packageSize = packageSize;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getThrust() {
        return thrust;
    }

    public void setThrust(float thrust) {
        this.thrust = thrust;
    }

    public float getTemprature() {
        return temprature;
    }

    public void setTemprature(float temprature) {
        this.temprature = temprature;
    }

    public short getFooter() {
        return footer;
    }

    public void setFooter(short footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "RocketTelemetryModel{" +
                "packageStartByte=" + packageStartByte +
                ", rocketId='" + rocketId + '\'' +
                ", packageNumber=" + packageNumber +
                ", packageSize=" + packageSize +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", thrust=" + thrust +
                ", temprature=" + temprature +
                ", footer=" + footer +
                '}';
    }
}

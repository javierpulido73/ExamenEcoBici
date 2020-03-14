package com.anzen.android.examenandroid.model;

/**
 * Created by Icom_JP on 2020-03-12.
 * Description: Dto para las estaciones de ecoBici
 */
@SuppressWarnings("unused")
public class StationsDto {
    private Integer id;
    private String district;
    private Double lon;
    private Double lat;
    private Integer bikes;
    private Integer slots;
    private String zip;
    private String address;
    private String addressNumber;
    private String nearbyStations;
    private String status;
    private String name;
    private String stationType;
    private Double distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getBikes() {
        return bikes;
    }

    public void setBikes(Integer bikes) {
        this.bikes = bikes;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getNearbyStations() {
        return nearbyStations;
    }

    public void setNearbyStations(String nearbyStations) {
        this.nearbyStations = nearbyStations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "StationsDto{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", bikes=" + bikes +
                ", slots=" + slots +
                ", zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", nearbyStations='" + nearbyStations + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", stationType='" + stationType + '\'' +
                ", distance=" + distance +
                '}';
    }
}

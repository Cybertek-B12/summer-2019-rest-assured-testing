package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country_name")
    private String countryName;

    @SerializedName("iso-3166")
    private String iso3166;

    @SerializedName("total_holidays")
    private int totalHolidays;

    @SerializedName("supported_languages")
    private int supportedLanguages;

    private String uuid;

    public Country(){

    }

    public Country(String countryName, String iso3166, int totalHolidays, int supportedLanguages, String uuid) {
        this.countryName = countryName;
        this.iso3166 = iso3166;
        this.totalHolidays = totalHolidays;
        this.supportedLanguages = supportedLanguages;
        this.uuid = uuid;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public int getTotalHolidays() {
        return totalHolidays;
    }

    public void setTotalHolidays(int totalHolidays) {
        this.totalHolidays = totalHolidays;
    }

    public int getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(int supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", iso3166='" + iso3166 + '\'' +
                ", totalHolidays=" + totalHolidays +
                ", supportedLanguages=" + supportedLanguages +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

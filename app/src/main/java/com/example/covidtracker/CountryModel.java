package com.example.covidtracker;

public class CountryModel {
    private String acitve,flag,totalcases,todaycases,deaths,todaydeaths,critical,country,recovered;

    public CountryModel(String acitve, String flag, String totalcases, String todaycases, String deaths, String todaydeaths, String critical, String country, String recovered) {
        this.acitve = acitve;
        this.flag = flag;
        this.totalcases = totalcases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.critical = critical;
        this.country = country;
        this.recovered = recovered;

    }

    public String getAcitve() {
        return acitve;
    }

    public void setAcitve(String acitve) {
        this.acitve = acitve;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }




}

package com.aor.refactoring.example6;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tree {
    private Date plantedAt;
    private String locationLatitude;
    private String locationLongitude;
    private String locationName;
    private List<Date> appraisalDates = new ArrayList<>();

    public String getLocationLatitude() {
        return locationLatitude;
    }
    public String getLocationLongitude(){
        return locationLongitude;
    }
    public String getLocationName() {
        return locationName;
    }
    public Date getPlantedAt() {
        return plantedAt;
    }

    public Tree(Date plantedAt, String locationLatitude, String locationLongitude, String locationName){
        this.plantedAt = plantedAt;
        this.setLocation(locationLatitude, locationLongitude, locationName);
    }

    public void setLocation(String locationLatitude, String locationLongitude, String locationName){
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.locationName = locationName;
    }

    public String toString() {
        return "Tree planted at " + this.plantedAt.toString() + " in location " + this.locationLatitude + "," + this.locationLongitude + " (" + this.locationName + ")";
    }

    void addAppraisal(Date appraisalDate) {
        this.appraisalDates.add(appraisalDate);
    }

    public List<Date> getAppraisals(){
        return this.appraisalDates;
    }

    public boolean isNextAppraisalOverdue(){
        Date today = new Date();
        Date latestAppraisalDate = today;

        if (this.appraisalDates.size() > 0) {
            latestAppraisalDate = this.appraisalDates.get(0);
        }
        for(Date appraisalDate : this.appraisalDates) {
            if (latestAppraisalDate.before(appraisalDate)) {
                latestAppraisalDate = appraisalDate;
            }
        }

        Calendar calendar = calculateNextAppraisal(latestAppraisalDate);

        Date nextAppraisalDate = calendar.getTime();
        // Appraisal is only overdue if its date is in the past
        return nextAppraisalDate.before(today);
    }

    private Calendar calculateNextAppraisal(Date latestAppraisalDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(latestAppraisalDate);
        calendar.add(Calendar.MONTH, 3);

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            calendar.add(Calendar.DAY_OF_MONTH, 2);
        return calendar;
    }
}

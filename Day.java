package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Day {

    private Calendar date;
    private ArrayList<Double> temperatureByDay;
    private double maxTemperature;
    private int maxTemperatureTime;
    private double averageTemperature;

    private ArrayList<Double> humidityByDay;
    private double lowestHumidity;
    private int lowestHumidityTime;
    private double averageHumidity;

    private ArrayList<Double> windSpeedByDay;
    private double maxWindSpeed;
    private int maxWindSpeedTime;
    private double averageWindSpeed;

    public Day(ArrayList<String> strings) {
        this.date=this.parseStringsDate(strings);
        this.temperatureByDay=this.parseBeforeComa(strings);
        this.humidityByDay=this.parseBeforeComa(strings);
        this.windSpeedByDay=this.parseBeforeComa(strings);
        this.maxTemperature=this.searchMaxFromInfoByDay(this.temperatureByDay);
        this.maxTemperatureTime = this.searchTime(this.maxTemperature, this.temperatureByDay);
        this.averageTemperature = this.searchAverage(this.temperatureByDay);
        this.lowestHumidity = this.searchMinFromInfoByDay(this.humidityByDay);
        this.lowestHumidityTime = this.searchTime(this.lowestHumidity, this.humidityByDay);
        this.averageHumidity = this.searchAverage(this.humidityByDay);
        this.maxWindSpeed = this.searchMaxFromInfoByDay(this.windSpeedByDay);
        this.maxWindSpeedTime = this.searchTime(this.maxWindSpeed, this.windSpeedByDay);
        this.averageWindSpeed = this.searchAverage(this.windSpeedByDay);

    }


    //украдено
    private double searchMaxFromInfoByDay(ArrayList<Double> informationByDay) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < informationByDay.size(); ++i) {
            if (max < informationByDay.get(i)) {
                max = informationByDay.get(i);
            }
        }
        return max;
    }

    private double searchMinFromInfoByDay(ArrayList<Double> humidityByDay) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < humidityByDay.size(); ++i) {
            if (min > humidityByDay.get(i)) {
                min = humidityByDay.get(i);
            }
        }
        return min;
    }


    private double searchAverage(ArrayList<Double> informationByDay) {
        double average = 0.0;
        for (int i = 0; i < informationByDay.size(); ++i) {
            average += informationByDay.get(i);
        }
        return average / informationByDay.size();
    }



    private ArrayList<Double> parseBeforeComa(ArrayList<String> strings) {
        final ArrayList<Double> arrayList = new ArrayList<Double>();
        for (int i = 0; i < strings.size(); ++i) {
            String str = strings.get(i).split(",")[0];
            arrayList.add(Double.valueOf(str));
            strings.set(i,strings.get(i).replaceFirst(str+",", ""));
        }
        return arrayList;
    }

    private int searchTime(double value,ArrayList<Double> DayInformation){
        for(int i =0;i<DayInformation.size();++i){
            if(value == DayInformation.get(i)){
                return i;
            }
        }
        return -1;
    }

    private Calendar parseStringsDate(ArrayList<String> strings) {
        int year = 0;
        for (int i = 0; i < 4; ++i) {
            year += Character.digit(strings.get(0).charAt(i), 10);
            year *= 10;
        }
        year /= 10;
        int month = 0;
        month += Character.digit(strings.get(0).charAt(4), 10) * 10;
        month += Character.digit(strings.get(0).charAt(5), 10);
        int day = 0;
        day += Character.digit(strings.get(0).charAt(6), 10) * 10;
        day += Character.digit(strings.get(0).charAt(7), 10);
        final Calendar date = new GregorianCalendar(year, month, day);
        for (int j = 0; j < strings.size(); ++j) {
            strings.set(j, strings.get(j).substring(14));
        }
        return date;
    }

    @Override
    public String toString() {
        return "Day: " + this.date.get(Calendar.YEAR) + "." + this.date.get(Calendar.MONTH) + "." + this.date.get(Calendar.DATE) +"\n"+
                "average temperature = " + String.format("%.2f",this.averageTemperature) +"°C"+
                ", max temperature = " + this.maxTemperature + "°C"+
                ", recorded in " + this.maxTemperatureTime +" o'clock\n"+
                "average humidity = " + String.format("%.2f",this.averageHumidity) + "%"+
                ", lowest humidity = " + String.format("%.2f",this.lowestHumidity) +"%"+
                ", recorded in " + this.lowestHumidityTime + " o'clock\n"+
                "average wind speed = " + String.format("%.2f",this.averageWindSpeed) + " km/h"+
                ", max wind speed = " + this.maxWindSpeed +" km/h"+
                ", recorded in " + this.maxWindSpeedTime +  " o'clock";
    }

    public double getAverageTemperature() {
        return this.averageTemperature;
    }

    public double getAverageHumidity() {
        return this.averageHumidity;
    }


    public double getAverageWindSpeed() {
        return this.averageWindSpeed;
    }

    public Calendar getDate() {
        return date;
    }

    public int getTimeMaxTemperature() {
        return maxTemperatureTime;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getLowestHumidity() {
        return lowestHumidity;
    }

    public int getLowestHumidityTime() {
        return lowestHumidityTime;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }



}



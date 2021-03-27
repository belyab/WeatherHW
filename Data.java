package com.company;

import javax.crypto.AEADBadTagException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class Data {
    private ArrayList<Day> dayArrayList;
    private double totalTemperature;
    private double totalHumidity;
    private double totalWindSpeed;
    private double averageTemperature;
    private double averageHumidity;
    private double averageWindSpeed;

    public Data(ArrayList<String> parse) {
    }


    public void WeatherData(final ArrayList<String> text){
        this.dayArrayList = this.DayCreation(text);

    }

    private ArrayList<Day> DayCreation(final ArrayList<String> text){
        final   ArrayList<Day> days = new ArrayList<>();

        for(int i =0;i<text.size()/24;++i){
            final ArrayList<String> DayInformation = new ArrayList<>();

            for(int j = 0;j<24;++j){
                DayInformation.add(text.get(i*24+j));
            }
            days.add(new Day(DayInformation));
        }
        return days;
    }

    public ArrayList<Day> getDayArrayList() {
        return this.dayArrayList;
    }

    public double getAverageTemperature() {
        final ArrayList<Double> averageTemperature = new ArrayList<Double>();
        for(int i =0;i<this.dayArrayList.size();++i){
            averageTemperature.add(this.dayArrayList.get(i).getAverageTemperature());
        }
        for(int i =0;i<averageTemperature.size();++i){
            totalTemperature +=averageTemperature.get(i);
        }
        return totalTemperature/averageTemperature.size();
    }

    public double getAverageHumidity() {
        final ArrayList<Double> averageHumidity = new ArrayList<Double>();
        for(int i =0;i<this.dayArrayList.size();++i){
            averageHumidity.add(this.dayArrayList.get(i).getAverageHumidity());
        }
        for(int i =0;i<averageHumidity.size();++i){
            totalHumidity +=averageHumidity.get(i);
        }
        return totalHumidity/averageHumidity.size();
    }

    public double getAverageWindSpeed() {
        final ArrayList<Double> averageWindSpeed = new ArrayList<Double>();
        for(int i =0;i<this.dayArrayList.size();++i){
            averageWindSpeed.add(this.dayArrayList.get(i).getAverageWindSpeed());
        }
        for(int i =0;i<averageWindSpeed.size();++i){
            totalWindSpeed +=averageWindSpeed.get(i);
        }
        return totalWindSpeed/averageWindSpeed.size();
    }

    //взято из просторов гугла
    public Day getDayMaxTemperature(){
        Day day = dayArrayList.stream().max(Comparator.comparingDouble(o->o.getMaxTemperature())).get();
        day.getDate().set(Calendar.HOUR_OF_DAY,day.getTimeMaxTemperature());
        return day;
    }

    public Day getDayLowestHumidity(){
        Day day = dayArrayList.stream().min(Comparator.comparingDouble(o->o.getLowestHumidity())).get();
        day.getDate().set(Calendar.HOUR_OF_DAY,day.getLowestHumidityTime());
        return day;
    }

    public  Day getDayMaxWindSpeed(){
        Day day = dayArrayList.stream().max(Comparator.comparingDouble(o->o.getMaxWindSpeed())).get();
        day.getDate().set(Calendar.HOUR_OF_DAY, (int) day.getMaxWindSpeed());
        return day;
    }

    @Override
    public String toString() {
        return getAverageTemperatureToString()+"\n"+
                getMaxTemperatureToString()+"\n"+
                getLowestHumidityToString()+"\n"+
                getMaxWindSpeedToString()+"\n";
    }

    private String getMaxWindSpeedToString() {
        Day day = getDayMaxWindSpeed();

        return "Max wind speed = "+day.getDate().get(Calendar.YEAR) +
                "." + day.getDate().get(Calendar.MONTH) + "." + day.getDate().get(Calendar.DATE) +
                " at " + day.getDate().get(Calendar.HOUR_OF_DAY) + " o'clock" +
                " = " + day.getMaxWindSpeed();

    }

    private String getLowestHumidityToString() {
        Day day = getDayLowestHumidity();
        return "Lowest humidity = "+day.getDate().get(Calendar.YEAR) +
                "." + day.getDate().get(Calendar.MONTH) + "." + day.getDate().get(Calendar.DATE) +
                " at " + day.getDate().get(Calendar.HOUR_OF_DAY) + " o'clock" +
                " = "+day.getLowestHumidity();


    }

    private String getMaxTemperatureToString() {
        Day day = getDayMaxTemperature();

        return "Max temperature = "+day.getDate().get(Calendar.YEAR)+
                "." + day.getDate().get(Calendar.MONTH) +
                "." + day.getDate().get(Calendar.DATE) +
                " at " + day.getDate().get(Calendar.HOUR_OF_DAY) + " o'clock" +
                " = "+day.getMaxTemperature();

    }

    private String getAverageTemperatureToString() {
        return "Average temperature = "+String.format("%.2f",getAverageTemperature());
    }


}

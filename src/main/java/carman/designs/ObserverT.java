package carman.designs;

import java.util.ArrayList;
import java.util.List;
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperture;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }
    public void setMeasurements(float temperture, float humidity, float pressure) {
        this.temperture = temperture;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }
    @Override
    public void notifyObserver() {
        for (Observer o : observers ) {
            o.update(temperture, humidity, pressure);
        }
    }
}
interface Observer {
    void update(float temp, float humidity, float pressure);
}
class StatisticsDisplay implements Observer {
    public StatisticsDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("StatisticsDisplay.update: " + temp + " " + humidity + " " + pressure);
    }
}
class CurrentConditionDisplay implements Observer {
    CurrentConditionDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("CurrentConditionDisplay.update: " + temp + " " + humidity + " " + pressure);
    }
}
public class ObserverT {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        weatherData.setMeasurements(0, 0, 0);
        weatherData.setMeasurements(1, 1, 1);
    }
}
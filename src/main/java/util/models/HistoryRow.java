package util.models;

import java.util.Date;
import java.util.Objects;

/**
 * Утилитарный класс для удобства работы со строками таблицы истории события
 */
public class HistoryRow implements Comparable<HistoryRow> {
    public HistoryRow(String time, String actual, String forecast, String previous) {
        this.time = time;
        this.actual = actual;
        this.forecast = forecast;
        this.previous = previous;
    }

    String time;
    String actual;
    String forecast;
    String previous;

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryRow that = (HistoryRow) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(actual, that.actual) &&
                Objects.equals(forecast, that.forecast) &&
                Objects.equals(previous, that.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, actual, forecast, previous);
    }

    @Override
    public String toString() {
        return time + "\t|\t" +
                actual + "\t|\t" +
                forecast + "\t|\t" +
                previous;
    }

    @Override
    public int compareTo(HistoryRow o) {
        return -(new Date(this.time).compareTo(new Date(o.time)));
    }
}

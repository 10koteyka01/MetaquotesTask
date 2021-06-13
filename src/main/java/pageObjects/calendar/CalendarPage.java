package pageObjects.calendar;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import util.models.HistoryRow;

import java.util.*;

import static util.Driver.*;

/**
 * Страница с календарём
 */
public class CalendarPage {
    /**
     * Кнопка "Фильтр"
     */
    public MobileElement filter() {
        return mobileElementById("net.metaquotes.economiccalendar:id/menu_filter");
    }

    /**
     * Событие
     */
    public List<MobileElement> events() {
        return mobileElementsById("net.metaquotes.economiccalendar:id/title");
    }

    /**
     * Страница "события"
     */
    public static class EventPage {
        /**
         * Страна
         */
        public MobileElement countryName() {
            return mobileElementById("net.metaquotes.economiccalendar:id/country_name");
        }

        /**
         * Важность события
         */
        public MobileElement eventImportance() {
            return mobileElementById("net.metaquotes.economiccalendar:id/event_importance");
        }

        /**
         * Кнопка "История"
         */
        public MobileElement historyButton() {
            return mobileElementById("net.metaquotes.economiccalendar:id/tab_history");
        }

        /**
         * Таблица с историей события
         */
        public Set<HistoryRow> historyTable() {
            Set<HistoryRow> history = new TreeSet<>();
            int size = 0;
            while (true) {
                history.addAll(convertHistoryTableRows(mobileElementsByClassName("android.widget.LinearLayout")));
                scrollDownOneTime();
                if (history.size() == size)
                    break;
                else size = history.size();
            }

            return history;
        }

        private Set<HistoryRow> convertHistoryTableRows(List<MobileElement> table) {
            Set<HistoryRow> historyRows = new HashSet<>();

            table.forEach(row -> {
                if (historyTableTime(row) != null && isTimeColumnSuccessfullyConvertsToDate(historyTableTime(row)))
                    historyRows.add(new HistoryRow(historyTableTime(row).getText(),
                            historyTableActual(row).getText(),
                            historyTableForecast(row).getText(),
                            historyTablePrevious(row).getText()));
            });


            return historyRows;
        }

        private MobileElement historyTableTime(MobileElement row) {
            return findTableElementById(row, "time");
        }

        private MobileElement historyTableActual(MobileElement row) {
            return findTableElementById(row, "actual");
        }

        private MobileElement historyTableForecast(MobileElement row) {
            return findTableElementById(row, "forecast");
        }

        private MobileElement historyTablePrevious(MobileElement row) {
            return findTableElementById(row, "previous");
        }

        private MobileElement findTableElementById(MobileElement row, String elementName) {
            try {
                return row.findElementById("net.metaquotes.economiccalendar:id/".concat(elementName));
            } catch (NoSuchElementException ex) {
                return null;
            }
        }

        private boolean isTimeColumnSuccessfullyConvertsToDate(MobileElement element) {
            try {
                new Date(element.getText());
                return true;
            } catch (IllegalArgumentException ex) {
            }
            return false;
        }
    }
}

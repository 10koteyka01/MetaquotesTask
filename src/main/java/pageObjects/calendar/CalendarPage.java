package pageObjects.calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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
    public WebElement filter() {
        return WebElementById("net.metaquotes.economiccalendar:id/menu_filter");
    }

    /**
     * Событие
     */
    public List<WebElement> events() {
        return WebElementsById("net.metaquotes.economiccalendar:id/title");
    }

    /**
     * Страница "события"
     */
    public static class EventPage {
        /**
         * Страна
         */
        public WebElement countryName() {
            return WebElementById("net.metaquotes.economiccalendar:id/country_name");
        }

        /**
         * Важность события
         */
        public WebElement eventImportance() {
            return WebElementById("net.metaquotes.economiccalendar:id/event_importance");
        }

        /**
         * Кнопка "История"
         */
        public WebElement historyButton() {
            return WebElementById("net.metaquotes.economiccalendar:id/tab_history");
        }

        /**
         * Таблица с историей события
         */
        public Set<HistoryRow> historyTable() {
            Set<HistoryRow> history = new TreeSet<>();
            int size = 0;
            while (true) {
                history.addAll(convertHistoryTableRows(WebElementsByClassName("android.widget.LinearLayout")));
                scrollDownOneTime();
                if (history.size() == size)
                    break;
                else size = history.size();
            }

            return history;
        }

        private Set<HistoryRow> convertHistoryTableRows(List<WebElement> table) {
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

        private WebElement historyTableTime(WebElement row) {
            return findTableElementById(row, "time");
        }

        private WebElement historyTableActual(WebElement row) {
            return findTableElementById(row, "actual");
        }

        private WebElement historyTableForecast(WebElement row) {
            return findTableElementById(row, "forecast");
        }

        private WebElement historyTablePrevious(WebElement row) {
            return findTableElementById(row, "previous");
        }

        private WebElement findTableElementById(WebElement row, String elementName) {
            try {
                return row.findElement(By.id("net.metaquotes.economiccalendar:id/".concat(elementName)));
            } catch (NoSuchElementException ex) {
                return null;
            }
        }

        private boolean isTimeColumnSuccessfullyConvertsToDate(WebElement element) {
            try {
                new Date(element.getText());
                return true;
            } catch (IllegalArgumentException ex) {
            }
            return false;
        }
    }
}

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.BottomBar;
import pageObjects.calendar.CalendarPage;
import pageObjects.calendar.filter.FilterPage;
import pageObjects.settings.SettingsPage;
import util.models.HistoryRow;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static util.Driver.*;

public class FilterTest {
    private static Logger log = LoggerFactory.getLogger(FilterTest.class);

    @BeforeAll
    static void beforeAll() {
        setUpDriver();
    }

    /**
     * В тесте не предусмотрено ожидание загрузки элементов, поэтому на медленных устройствах он может работать
     * не корректно. Не нашёл удобного механизма для этих ожиданий в Appium, как в Selenide. Решение
     * вижу в написании своей реализации класса WebElement, в которой доделать возможность проверки и ожиданий,
     * либо в использовании более удобного фреймворка для UI-тестирования типа Selenide.
     */
    @Test
    @DisplayName("Тест экономического календаря")
    void test() {
        CalendarPage calendarPage = new CalendarPage();
        CalendarPage.EventPage eventPage = new CalendarPage.EventPage();
        BottomBar bottomBar = new BottomBar();
        FilterPage filterPage = new FilterPage();

        step("Убедиться, что язык приложения выбран Английский", () -> {
            bottomBar.settings().click();
            assumeTrue(new SettingsPage().language("English") != null, "Язык приложения не английский");
        });

        step("Перейти на страницу фильтра экономического календаря", () -> {
            bottomBar.calendar().click();
            calendarPage.filter().click();
        });

        filterPage.filterByParameters("Medium", "Switzerland");

        step("Выбрать 3 событие из списка, проверить, что приоритет и страна отображаемого события соответствуют" +
                "выбранным фильтрам", () -> {
            calendarPage.events().get(2).click();

            assertEquals("Switzerland",
                    eventPage.countryName().getText(),
                    "Страна отображаемого события не соответствует выбранному фильтру");

            assertTrue(eventPage.eventImportance().getText().equalsIgnoreCase("Medium"),
                    "Приоритет отображаемого события не соответствует выбранному фильтру");
        });

        step("Вывести в лог историю события за последние 12 месяцев в виде таблицы.", () -> {
            eventPage.historyButton().click();

            Calendar calendar = Calendar.getInstance();
            calendar.roll(Calendar.YEAR, false);

            log.info("Date\t\t|\tActual\t\t|\tForecast\t|\tPrevious");

            Set<HistoryRow> history = eventPage.historyTable();
            history.forEach(row -> {
                if (isRowTimeAfterRequired(row.getTime(), calendar)){
                    log.info(row.toString());
                }
            });

        });
    }

    private boolean isRowTimeAfterRequired(String time, Calendar calendar) {
        try {
            return new Date(time).after(calendar.getTime());
        }
        catch (IllegalArgumentException ex){
        }

        return false;
    }

    @AfterAll
    static void afterAll() {
        quit();
    }
}

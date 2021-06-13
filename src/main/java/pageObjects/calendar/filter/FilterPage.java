package pageObjects.calendar.filter;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;

import static util.Driver.*;

public class FilterPage {
    @Step("Отфильтровать события календаря по следующим параметрам Importance = {importance}, Country = {country}")
    public void filterByParameters(String importance, String country){
        clearAllSelections();
        selectCheckBox(importance);
        selectCheckBox(country);
        returnToCalendar();
    }

    @Step("Очистить все чек-боксы")
    public void clearAllSelections() {
        mobileElementsByXpath("//android.widget.Button[@text='SELECT ALL']")
                .forEach(
                        RemoteWebElement::click
                );
    }

    @Step("Выбрать чекбокс {checkBoxName}")
    public void selectCheckBox(String checkBoxName) {
        scrollToElementWithText(checkBoxName).click();
    }

    @Step("Вернуться в календарь")
    public void returnToCalendar() {
        mobileElementByAccessibilityId("Navigate up").click();
    }
}

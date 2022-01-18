import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertNotEquals;

public class SelemideTests2 {
    public SelemideTests2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.reportsFolder = "src/main/resources/Reports";

    }

    @Test
    public void test1(){
        open("https://demoqa.com/books ");
        SoftAssert softAssert = new SoftAssert();

        ElementsCollection bookclass = $(".rt-tbody").findAll(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(text("JavaScript"));

        softAssert.assertEquals(bookclass.size(),10,"failed case");
        softAssert.assertEquals(bookclass.get(0).getText(), "Learning JavaScript Design Padtterns");

        ElementsCollection a = $(".rt-tbody").$$(".rt-tr-group div div div span a");
        a.stream().forEach(el -> { el.click(); back();});

    }


    @Test
    public void test2(){
        open("https://demoqa.com/books");
        SoftAssert softAssert = new SoftAssert();
        ElementsCollection book = $(".ReactTable").$(".rt-tbody").findAll(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(text("JavaScript"));
        ElementsCollection img = $(".ReactTable").$(".rt-tbody").findAll("img");
        assertNotEquals(img.size(), 0);


    }


}

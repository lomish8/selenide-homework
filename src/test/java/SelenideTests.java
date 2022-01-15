import com.codeborne.selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    public SelenideTests() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
    }

    @Test
    public void test1(){
        open("http://the-internet.herokuapp.com/checkboxes ");

        // - Set first checkbox selected
        ElementsCollection checkbox = $$("#checkboxes input");
        checkbox.first().click();

        // - Validate that both checkboxes has type 'checkbox'
        for(SelenideElement check : checkbox) {
            check.shouldBe(checked);
        }
    }

    @Test
    public void test2(){
        open("http://the-internet.herokuapp.com/dropdown");

        // - Validate that 'Please select an option' is selected
        SelenideElement dropdown = $("#dropdown");
        dropdown.getSelectedOption().shouldHave(text("Please select an option"));

        // - Select 'Option 2'
        dropdown.selectOptionContainingText("Option 2");

        // - Validate that 'Option 2' was selected
        dropdown.getSelectedOption().shouldHave(text("Option 2"));

    }

    @Test
    public void test3(){
        open("https://demoqa.com/text-box ");

        // - Fill fullname, valid email,current and permanent adresses using different selectors
        $(byId("userName")).sendKeys("Tato");
        $(byAttribute("type" , "email")).sendKeys("tatolomishvili@gmail.com");
        $(by("placeholder" , "Current Address")).sendKeys("Agmashenebeli 101");
        $("#permanentAddress").sendKeys("Agmashenebeli");
        $("#submit").scrollIntoView(true).click();

        // - Validate the result with Collection Assetion
        $$("#output div p").shouldHave(exactTexts("Name:Tato" , "Email:tatolomishvili@gmail.com" , "Current Address :Agmashenebeli 101", "Permananet Address :Agmashenebeli"));


    }
}

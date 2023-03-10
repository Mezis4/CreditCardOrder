package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderNegativeTests {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    // Тесты для поля Имя-Фамилия
    @Test
    void shouldShowErrorIfNameWithLatinLetter() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("John Smith");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfNameOneWord() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Мария");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfNameWithNumber() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("79231734037");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfNameWithSymbols() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("#@^%&");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfNameEmpty() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfNameOnlySpaceBar() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("  ");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfNameOnlyDashesBar() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("----");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expected, actual);
    }

    // Тесты для поля Номер телефона
    @Test
    void shouldShowErrorIfPhoneStructureNotTemplateNumber8() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("89231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhoneStructureNotTemplateNumber7() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("79231734037");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhoneEmpty() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhone10Numbers() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("+7923173403");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhone12Numbers() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("+792317340372");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhoneWithLetters() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("Дроздов Петя");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhoneOnlySymbols() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("+&*&$%^@#^&$");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowErrorIfPhoneOnlySpaceBar() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("    ");
        driver.findElement(By.className("checkbox__text")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText();
        assertEquals(expected, actual);
    }

    // Тест на чекбокс обработки персональных данных
    @Test
    void shouldShowErrorIfCheckboxEmpty() {
        driver.get("http://localhost:9999");
        driver.findElements(By.tagName("input")).get(0).sendKeys("Дроздов Петя");
        driver.findElement(By.name("phone")).sendKeys("+79231734037");
        driver.findElement(By.className("button__text")).click();
        String expected = "checkbox checkbox_size_m checkbox_theme_alfa-on-white input_invalid";
        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement]")).
                getAttribute("class");

        assertEquals(expected, actual);
    }
}

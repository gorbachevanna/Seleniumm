package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {
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
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldOrder() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void shouldOrderDoubleName() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна-Мария");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void shouldOrderNameWithTwoSymbol() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Aя");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void shouldOrderYoInName() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Майя");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void shouldOrderYourInName() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Алёна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void shouldNotOrderInEnglish() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Gorbacheva Anna");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    public void shouldNotOrderSpecialCharacters() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("%орбачева &нна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    public void shouldNotOrderNameWithNumbers() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("123456 Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    public void shouldNotOrderSpaceBar() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys(" ");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void shouldNotOrderNoName() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void shouldNotOrderNoPhone() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void shouldNotOrderEmptiness() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneLess() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7909000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneMore() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+790900000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneMin() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneRusLetters() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Телефон");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneEngLetters() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Phone");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneSpecialCharacters() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("%%%");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderPhoneSpaceBar() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys(" ");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void shouldNotOrderWithoutPlus() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79092564580");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void shouldNotOrderWithoutAgreement() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Горбачева Анна");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79092564580");
        form.findElement(By.cssSelector(".button")).click();

        WebElement agree = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid"));
        assertTrue(agree.isDisplayed(), "Сообщение об ошибке");
    }

    @Test
    public void shouldNotOrderNameWithOneSymbol() {

        WebElement form = driver.findElement(By.cssSelector(".form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("A");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79090000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Недопустимое имя", text.trim());
    }
}

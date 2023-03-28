// импортируем RestAssured
import io.restassured.RestAssured;
// импортируем Before
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static io.restassured.RestAssured.*;

public class Praktikum {

    // аннотация Before показывает, что метод будет выполняться перед каждым тестовым методом
    @Before
    public void setUp() {
        // повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
        // если в классе будет несколько тестов, указывать её придётся только один раз
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    // создаём метод автотеста
    @Test
    public void getMyInfoStatusCode() {
        // метод given() помогает сформировать запрос
        given()
                // указываем протокол и данные авторизации
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2YxZTMyYzYzNDYzYTAwM2Q0N2QzYTAiLCJpYXQiOjE2Nzk5MzI1NDIsImV4cCI6MTY4MDUzNzM0Mn0.NSKSgT8j5FUlvtoH20M0IKK3U9zCZfyjJIhrCR3KsRs")
                // отправляем GET-запрос с помощью метода get, недостающую часть URL (ручку) передаём в него в качестве параметра
                .get("/api/users/me")
                // проверяем, что статус-код ответа равен 200
                .then().statusCode(200);
    }

}
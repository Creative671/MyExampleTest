package api;


import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;

public class TestStart {

    private final static String URL = "https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber";

    @Test
    @DisplayName("Проверка на число")
    public void successTestOne(){
    Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());

    boolean testResult = true;
    String number = "1erer";

    value value = new value(number);
    isNumber resultNumber = given()
            .body(value)
            .when()
            .post("https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber")
            .then().log().all()
            .extract().as(isNumber.class);
        if (isNumeric(number)){
            Assertions.assertTrue(resultNumber.getIsNumber());
        } else {
            Assertions.assertFalse(resultNumber.getIsNumber());
        }
    }

    @Test
    @DisplayName("Проверка на не число")
    public void successTestTwo() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());

        boolean testResult = false;
        String number = "Test";

        value value = new value(number);
        isNumber resultNumber = given()
                .body(value)
                .when()
                .post("https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber")
                .then().log().all()
                .extract().as(isNumber.class);
        Assertions.assertFalse(resultNumber.getIsNumber());
        }

    @Test
    @DisplayName("Проверка на ввод текста 'true'")
    public void successTestThree() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());

        String number = "true";

        value value = new value(number);
        isNumber resultNumber = given()
                .body(value)
                .when()
                .post("https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber")
                .then().log().all()
                .extract().as(isNumber.class);
        Assertions.assertFalse(resultNumber.getIsNumber());
        }

    @Test
    @DisplayName("Проверка на ввод текста 'false'")
    public void successTestFour() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());

        String number = "false";

        value value = new value(number);
        isNumber resultNumber = given()
                .body(value)
                .when()
                .post("https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber")
                .then().log().all()
                .extract().as(isNumber.class);
        Assertions.assertFalse(resultNumber.getIsNumber());
    }

    @Test
    @DisplayName("Проверка на ввод десятичного числа (через точку)")
    public void successTestFifth() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());

        String number = "1.5";

        value value = new value(number);
        isNumber resultNumber = given()
                .body(value)
                .when()
                .post("https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber")
                .then().log().all()
                .extract().as(isNumber.class);
        Assertions.assertTrue(resultNumber.getIsNumber());
    }

    @Test
    @DisplayName("Проверка на ввод десятичного числа (через запятую)")
    public void successTestSix() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());

        String number = "324,3434";

        value value = new value(number);
        isNumber resultNumber = given()
                .body(value)
                .when()
                .post("https://api.aliexpress.ru/ug-qa-sqa-days-is-number/isnumber")
                .then().log().all()
                .extract().as(isNumber.class);
        Assertions.assertTrue(resultNumber.getIsNumber());
    }

    // Проверка на число
    public static boolean isNumeric(String str){
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}

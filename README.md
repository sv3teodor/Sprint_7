<div align="center">

<h3 align="center">Финальный проект 7 спринта</h3>

<!-- ABOUT THE PROJECT -->

## Автотесты для проверки API сервиса аренды самокатов

</div>

<p align="center"><a href="https://qa-scooter.praktikum-services.ru/">Яндекс самокат</a> </p>
Проект покрывает тестами следующие сценарии:

### Тестовые сценарии по основному заданию.

Реализованы следующие проверки:

Курьер

1. Создание курьера
2. Логин курьера
3. Проверка, что невозможно завести курьера с некорректными параметрами
4. Проверка, что нельзя создать двух одинаковых курьеров;

Создание заказа

1. Проверка выбора цвета самоката в различных комбинациях.
2. Проверка, что тело ответа содержит track.

Список заказов

1. Проверка создания заказа
2. Проверка верного заполнения полей при запросе существующего заказа.

Построен отчет с использованием Allure

### Дополнительные тестовые сценарии.

Реализованы следующие проверки:

1. Проверка позитивных и негативных сценариев удаления курьера
2. Проверка позитивных и негативных сценариев запроса на принятия заказа
3. Проверка позитивных и негативных сценариев получения заказа по номеру

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Установка

Для установки необходимо клонировать дистрибутив

   ```sh
   git clone https://github.com/sv3teodor/Sprint_7.git
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Запуск

Для запуска тестов выполните следующую команду:

```sh
mvn clean test
```

## Зависимости

В проекте используются следующие технологии:

- Java 11
- JUnit 4.13.2
- maven 3.8.6
- Allure
- RestAssured

<p align="right">(<a href="#readme-top">back to top</a>)</p>

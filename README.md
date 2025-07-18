Buysell - Интернет-магазин

Описание

Это проект интернет-магазина, реализованный на основе Spring Boot. Приложение позволяет пользователям регистрироваться, создавать и просматривать товары, а также управлять ими. Поддерживается базовая аутентификация и авторизация с использованием Spring Security.
Требования

Java 17 или выше
PostgreSQL
Maven

Установка

Клонируйте репозиторий:git clone https://github.com/ваш_пользователь/buysell.git


Настройте файл application.properties с вашими данными PostgreSQL:
* spring.datasource.url
* spring.datasource.username
* spring.datasource.password


Выполните команду для сборки проекта:mvn clean install


Запустите приложение:mvn spring-boot:run



Использование

* Регистрация пользователя: POST /api/users/registration
* Просмотр всех товаров: GET /api/products
* Создание товара: POST /api/products
* Получение товара по ID: GET /api/products/{id}
* Обновление товара: PATCH /api/products/{id}
* Удаление товара: DELETE /api/products/{id}

Структура проекта

* src/main/java/com.example.buysell: Основной код приложения
* configurations: Конфигурации безопасности
* controllers: Контроллеры REST API
* enums: Перечисления ролей
* models: Модели данных (User, Product)
* repositories: Репозитории для работы с базой данных
* services: Сервисы для бизнес-логики



Автор
[Таубай Ернұр Жамбылұлы]
# Дипломный проект профессии «Тестировщик ПО»

### Инструкция по запуску авто-тестов
1. Склонировать [репозиторий](https://github.com/MarinaSev/Diploma.git)
2. Открыть проект в IntelliJ IDEA Ultimate
3. Запуcтить Docker контейнеры
   * для работы с базой данных **MySQL**
     <code>docker-compose -f docker-compose-mysql.yml up -d</code>
   * для работы с базой данных **PostgreSQL**
     <code>docker-compose -f docker-compose-postgresql.yml up -d</code>
4. Запуcтить приложение
   * для **MySQL**
     <code>java -jar artifacts/aqa-shop.jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/app/</code>
   * для **PostgreSQL**
     <code>java -jar artifacts/aqa-shop.jar -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app</code>
5. Запустить тесты <code>.\gradlew clean test</code>
   * для **MySQL**
     <code>.\gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app/</code>
   * для **PostgreSQL**
     <code>.\gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app</code>

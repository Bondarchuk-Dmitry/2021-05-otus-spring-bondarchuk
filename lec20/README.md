## Использовать WebFlux

**Цель:**

разрабатывать Responsive и Resilent приложения на реактивном стеке Spring c помощью Spring Web Flux и Reactive Spring Data Repositories

**Результат:** 

приложение на реактивном стеке Spring

**Требования:**

* За основу для выполнения работы можно взять ДЗ с Ajax + REST (классическое веб-приложение для этого луче не использовать).
* Но можно выбрать другую доменную модель (не библиотеку).
* Необходимо использовать Reactive Spring Data Repositories.
* В качестве БД лучше использовать MongoDB или Redis. Использовать PostgreSQL и экспериментальную R2DBC не рекомендуется.
* RxJava vs Project Reactor - на Ваш вкус.
* Вместо классического Spring MVC и embedded Web-сервера использовать WebFlux.
* Опционально: реализовать на Functional Endpointsи Вы хотите засчитать, то обязательно пришлите ссылку в чат соответствующего предыдущего занятия.

Данное задание НЕ засчитывает предыдущие!

**UI для текущего backend необходимо склонировать с репозитория: 
[lec20-frontend](https://github.com/Bondarchuk-Dmitry/2021-05-otus-spring-bondarchuk/tree/lec20/lec20-frontend)**


**Запуск проекта с команды java -jar**
* выполнить команду maven: ```mvn clean install```
* запустить jar: ```java -jar target/lec20.jar```
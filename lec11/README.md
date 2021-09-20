## Переписать библиотеку на Spring Data JPA

**Цель:**

максимально просто писать слой репозиториев с применением современных подходов 

**Результат:** 

приложение со слоем репозиториев на Spring Data JPA

**Требования:**

Это домашнее задание выполняется НЕ на основе предыдущего.

Необходимо:

* Переписать все репозитории по работе с книгами на Spring Data JPA репозитории.
* Используйте spring-boot-starter-data-jpa.
* Кастомные методы репозиториев (или с хитрым @Query) покрыть тестами, используя H2.
* @Transactional рекомендуется ставить на методы сервисов, а не репозиториев.

Это домашнее задание является основой для следующих.

**Запуск проекта с команды java -jar**
* выполнить команду maven: ```mvn clean install```
* запустить jar: ```java -jar target/lec11.jar```
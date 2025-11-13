### Написать на Java\Spring (любых версий) сервис:
1. Должен быть доступен сваггер
2. В сваггере единственный контроллер с единственным методом
3. Метод должен принять на вход путь к локальному файлу в формате **xlsx** и число **N**
4. В файле в столбик находятся целые числа
5. Метод должен вернуть N-ное минимальное число из файла
6. Для поиска нельзя использовать библиотечные функции типа сортировки массива, нужно предложить и реализовать эффективный алгоритм (это важно)
7. Приложить инструкцию по сборке и запуску кода
   На задание не надо тратить много времени. Если есть идеи по улучшению, их можно дописать текстом.

### Swagger
```
http://localhost:8080/api/swagger-ui/index.html
```

<br>

### Запуск приложения
Приложение запускается из **класса com.example.demo.DemoApplication**.
Пример Excel-файла: `resources/tableWithMinNumer.xlsx`.
Пример запроса:
```
curl --location --request POST 'http://localhost:8080/api/file/excel/min-number' \
--header 'Content-Type: application/json' \
--data '{
    "filePath": "/home/olga/dev/demo/src/main/resources/tableWithMinNumer.xlsx",
    "n": 4
}'
```
Важно: редактировать путь на свой

<br>

### Запуск приложения из докера
1. Собрать приложение: ```./gradlew clean build```
2. Из директории demo выполнить команду: : ```docker-compose up```
3. Выполнить curl-запрос или запрос из сваггера

```
curl --location --request POST 'http://localhost:8080/api/file/excel/min-number' \
--header 'Content-Type: application/json' \
--data '{
    "filePath": "/app/data/tableWithMinNumer.xlsx",
    "n": 4
}'
```
Примечание: редактировать путь не нужно, файл уже добавлен в контейнер
# cloudStorage application

Сервис предоставляет REST интерфейс для реализации следующего функционала:
 - загрузка файла,  
 - выгрузка файла,
 - переименование файла,
 - удаление файла
 - вывод списка уже загруженных файлов пользователя,
 - авторизация пользователя.   
 Все настройки приложения хранятся в файле настроек (yml).
 
Протокол получения и отправки сообщений между FRONT и BACKEND описан в [yaml file](https://github.com/netology-code/jd-homeworks/blob/master/diploma/CloudServiceSpecification.yaml).
 
Информация о пользователях сервиса (логины для авторизации) и данных должны хранятся в базе данных **MySQL**.  
###### Test data
user1/pass1 - filename1.txt  
user2/pass2 - filename2.txt

### Описание и запуск FRONT
1. Установить nodejs (версия 14.18.1) на компьютер следуя инструкции: https://nodejs.org/ru/download/.  
2. Скачать [FRONT](https://github.com/netology-code/jd-homeworks/tree/master/diploma/netology-diplom-frontend) (JavaScript)  
3. Перейти в папку FRONT приложения и все команды для запуска выполнять из нее.  
4. Можно задать url для вызова своего backend сервиса:  
*В файле .env FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: VUE_APP_BASE_URL=http://localhost:8080  
Пересобрать и запустить FRONT снова: npm run build  
Измененный url сохранится для следующих запусков.*  
По-умолчанию FRONT запускается на порту 8080 и доступен по url в браузере http://localhost:8080  

### Test via API (Postman or http request file)

Upload file to Server  
[http://localhost:8081/file]

Get list of files from server  
[http://localhost:8081/list]

Download file from server  
[http://localhost:8081/files/cs_t.txt]
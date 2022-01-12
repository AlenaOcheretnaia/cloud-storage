# cloudStorage application

Сервис предоставляет REST интерфейс для реализации следующего функционала:
 - загрузка файла,  
 - выгрузка файла,
 - переименование файла,
 - удаление файла
 - вывод списка уже загруженных файлов пользователя,
 - авторизация пользователя.   
 Все настройки приложения хранятся в файле настроек (yaml).
 
Протокол получения и отправки сообщений между FRONT и BACKEND описан в [yaml file](https://github.com/netology-code/jd-homeworks/blob/master/diploma/CloudServiceSpecification.yaml).  
Схему работы приложения (BACKEND) можно посмотреть на [схеме](https://github.com/AlenaOcheretnaia/cloud-storage/blob/main/docs/Схема%20работы%20cloudStorage.png).
 
Информация о пользователях сервиса (логины для авторизации) и данных должны хранятся в базе данных **MySQL**. 
Установите базу данных и пропишите username/password к базе в настройках приложения.
 
`docker run -v /mysql_data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mysql -p 3306:3306  mysql`

Структуру таблиц базы данных, можно найти в файле [Структура таблиц БД](https://github.com/AlenaOcheretnaia/cloud-storage/blob/main/docs/Структура%20таблиц%20БД.png).

##### Test data

Для работы с приложением созданы пользователи и пароли, а также файлы для начала работы с приложением.
user1/pass1 - filename1.txt  
user2/pass2 - filename2.txt  
user3/pass3 - filename3.txt  
user4/pass4 - filename4.txt  
user5/pass5 - filename5.txt 

>### Описание и запуск FRONT
>
>1. Установить nodejs (версия 14.18.1) на компьютер следуя инструкции: https://nodejs.org/ru/download/.  
>2. Скачать [FRONT](https://github.com/netology-code/jd-homeworks/tree/master/diploma/netology-diplom-frontend) (JavaScript)  
>3. Перейти в папку FRONT приложения и все команды для запуска выполнять из нее.  
>4. Можно задать url для вызова своего backend сервиса:  
>*В файле .env FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: VUE_APP_BASE_URL=http://localhost:8080  
>Пересобрать и запустить FRONT снова: npm run build  
>Измененный url сохранится для следующих запусков.*  
>По-умолчанию FRONT запускается на порту 8080 и доступен по url в браузере http://localhost:8080  
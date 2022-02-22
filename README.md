# cloudStorage application

 The service provides a REST interface to implement the following functionality:
    - download file,
    - upload file,
    - rename file,
    - delete file,
    - display a list of loaded user's files,
    - user authorization.
    All application settings are used in settings files (yml).
 
The protocol for receiving and sending messages between FRONT and BACKEND is described in [CloudServiceSpecification yaml file](https://github.com/AlenaOcheretnaia/cloud-storage/blob/main/docs/CloudServiceSpecification.yaml).  
The scheme of the application (BACKEND) can be viewed in the file [CloudStorageAppSchema](https://github.com/AlenaOcheretnaia/cloud-storage/blob/main/docs/CloudStorageAppSchema.png).
 
Information about service users (logins for authorization) and data must be stored in the **MySQL** database.
Install the database and set the username/password to the database in the application settings.
 
`docker run -v /mysql_data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mysql -p 3306:3306  mysql`

The structure of the database tables can be found in the file [CloudStorageDatabaseTablesStructure](https://github.com/AlenaOcheretnaia/cloud-storage/blob/main/docs/CloudStorageDatabaseTablesStructure.png)

###### Test data

To work with the application, users and passwords have been created, as well as files to start working with the application.  
user1/pass1 - filename1.txt  
user2/pass2 - filename2.txt  
user3/pass3 - filename3.txt  
user4/pass4 - filename4.txt  
user5/pass5 - filename5.txt 

>### Description and launch of FRONT app

>1. Install nodejs (version 14.18.1) on your computer following the instructions: https://nodejs.org/ru/download/.  
>2. Download [FRONT](https://github.com/netology-code/jd-homeworks/tree/master/diploma/netology-diplom-frontend) (JavaScript)  
>3. Go to the FRONT folder of the application and execute all commands to run from it.  
>4. You can set a url to call your backend service:  
>*In the .env FRONT file (located in the project root) of the application, you need to change the url to the backend, for example: VUE_APP_BASE_URL=http://localhost:8080  
>Rebuild and run FRONT again: npm run build  
>Changed url will be saved for next runs.*  
>By default, FRONT runs on port 8080 and is accessible by url in the browser http://localhost:8080    
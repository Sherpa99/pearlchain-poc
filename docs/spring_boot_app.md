# Spring Boot Application Using Postgres

## Applicaiton workflow
![Screenshot](https://github.com/Sherpa99/pearlchain-poc/blob/master/docs/images/%20springboot_app.png)
### Spring_Boot Base RESTApi build based on lasted feature as mentioned
* SpringBoot Version : 2.3.0.RELEASE
* Java Version: 1.8
* Spring Boot Pagination & Filter giving faster response time
* JPA Repository
  * No-Code Repositories
  * Reduced boilerplate code
  * Generated Queries

Infrastrucure:
* IBM Cloud Base Postgres Database
* Application is hosted in OCP - OpenShift Container Platform

Tools used
* Docker 
* Kubernetes
* Eclipse

RESTEND Points fetching data from a table - DBO.ACT_RE_DEPLOYMENT
1) Fetch total record count
```console
curl 'http://52.116.72.12:30604/ard/rcount'
```
2) Fetch the record by id
```console
curl 'http://52.116.72.12:30604/ard/10001'
```
3) Fetch data by page
```console
curl 'http://52.116.72.12:30604/ard/byparam?pageNumber=20&pageSize=20'
```

## <a href=https://github.com/Sherpa99/pearlchain-poc/blob/master/docs/db_migration_process.md>Database Migration</a>
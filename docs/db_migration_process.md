# MSSQL to PGSQL Migration

## Database Migration Workflow
![Screenshot](https://github.com/Sherpa99/pearlchain-poc/blob/master/docs/images/%20database_migration.png)
### Pre-requisites
* 1) Must have Access to OpenShift Cluster and IBMCloud
* 2) Download IBMCloud and OpenShift CLI to execute commands from commandline

* **Perform following steps on MSSQL container**

1) Install DbVisualizer 
2) Create secret
```console
oc create secret generic mssql --from-literal=SA_PASSWORD="yourPassword"
```
3) Create a deployment object YAML file  
```console 
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: mssql-db
spec:
  replicas: 2
  template:
    metadata:
      labels:
        run: mssql-db
    spec:
      containers:
      - name: mssql-db
        image: us.icr.io/gs_dev_ns/mssql-db:gsv2
        ports:
        - containerPort: 1433
          protocol: TCP
        env:
        - name : MSSQL_PID
          value : "Developer"
        - name: ACCEPT_EULA
          value: "Y"
        - name: MSSQL_SA_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mssql
              key: SA_PASSWORD
```
4) Create a deployment using following command   
```console
oc create -f <deployment yaml file> .
```
5) Exec into the pod and make a directory called backup and copy the file into backup folder inside the container
```console
oc exec <pod name> mkdir /var/opt/mssql/backup
```
```console
oc cp <from location(path)> mssql-db:/var/opt/mssql/backup
```
6) List out logical file names and path inside the backup
```console
oc exec -it mssql-db /opt/mssql-tools/bin/sqlcmd -S localhost* -U SA -P '<yourPassword>' -Q 'RESTORE FILELISTONLY FROM DISK = "/var/opt/mssql/backup/<backup filename>"' | tr -s ' ' | cut -d ' ' -f 1-2 
```
7) Call Restore Database commands to restore the database inside the container.     
```console
oc exec -it  msssql-db /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P ' <yourPassword> ' -Q 'RESTORE DATABASE mssqldb FROM DISK ="/var/opt/mssql/backup//<backup filename>" " WITH MOVE "RetailV45" TO "/var/opt/mssql/data/mssqldb.mdf", MOVE "RetailV45_log" TO "/var/opt/mssql/data/mssqldb.ldf"'
```
8) Verify :
```console
docker exec -it mssql-db /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'yourpassword' -Q 'SELECT Name FROM sys.Databases'
```
9) Create Service
```console
oc expose deployment mssql-poc --type="NodePort" --port=1433
```
10) Fireup DbVisualizer which you have just installed, configure connections to connect the database to execute sql commands.

* **Perform following operation on PostreSQL container**

1) Install pgAdmin
2) Install pgLoader
3) Install PostgreSQL on IBM Cloud [ IMB Developer Tutorial for Installing Postgres](https://developer.ibm.com/tutorials/living-on-the-cloud-2)
4) Run following commands to transfer file data from MSSQL to PostgreSQL.
```console
pgloader mssql://sa:<yourMSSQlpassword>@<msql path> pgsql://postgres:<yourPGSQLPassword>@<pgsql path>
```
* **For more details on PostgreSQL database, please visit:** [ IMB Developer Tutorial](https://gist.github.com/timroster/b0fbc0b7054e573226600ba5bf5bdbb4)

## Java Application<a href=https://github.com/Sherpa99/pearlchain-poc/blob/master/docs/spring_boot_app.md></a>
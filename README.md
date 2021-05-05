# MySQL Tables Exporter

This is a Java application that exports tables from an arbitrary MySql server into csv files via command line arguments.

## Getting Started
Make sure you MySQL service is up and running in your machine, run: `sudo service mysql start`. You also need to have 
already a database properly configured, preferentially containing data you with to export.

## Running
This application uses `JDBC` as the MySQL driver and `Maven` as a dependency manager. Get inside the application folder
and run:

`mvn package && mvn assembly:single`

This will properly compile and generate an executable .jar file at `\target`.
To execute it run:

`java -jar target/mysql-tables-exporter-1.0-SNAPSHOT-jar-with-dependencies.jar`

Now you will need to provide all the arguments the application needs in order to begin the exporting process. Those are
four arguments separated by an empty space plus an optional one:

1. The server name as `name`:`port`
2. The desired user
3. The correct password for a given user
4. The desired database name
5. (optional) The table name that you want to export. If none is provided all tables will be exported

Example: `localhost:3306 root password university course`

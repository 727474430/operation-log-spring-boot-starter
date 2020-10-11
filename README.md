### A custom spring boot starter project.

  Mainly provides user for operation log information printing function, which simplifies the need to write an user for operation log function for each project.
  
  
### How to use

1. **git clone https://github.com/727474430/operation-log-spring-boot-starter.git**

2. **cd operation-log-spring-boot-starter**

3. **mvn install**

4. **Introducing dependencies in spring boot project that need to be used**

   ```xml
   <dependency>
       <groupId>com.raindrop</groupId>
       <artifactId>operation-log-spring-boot-starter</artifactId>
       <version>1.0.RELEASE</version>
   </dependency>
   ```

5. **Add following attributes in application.properties/application.yml**

  * Initial database table.
   
    - Mysql using src/main/resources/sql/mysql-schema.sql
    - H2 using src/main/resources/db/h2-schema.sql 

   * op.log.enable=true

     Whether to open the record user for operation log function. default is **"false"** not open. select true is open

   * op.log.logging=true

     Whether to open the print user for operation log function. default is **"true"** open. select false is close 

   * op.log.db-prefix=db_

     Database table prefix. default empty.

   * op.log.db-type=mysql

     Database for record operation log. default mysql database.


### Example

* application.properties

  ```properties
  op.log.enable=true
  op.log.logging=true
  op.log.db-prefix=db_
  op.log.db-type=mysql
  ```

* application.yml

  ```yaml
  op:
    log:
      enable: true
      logging: true
      db-prefix: db_
      db-type: mysql
  ```


[![](https://jitpack.io/v/727474430/web-logging-spring-boot-starter.svg)](https://jitpack.io/#727474430/operation-log-spring-boot-starter)

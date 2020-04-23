# CHEESE COIN SYSTEM 

## About the Project

Our  project is application of **CHEESE COIN SYSTEM** which is Innovation that uses de-centralized crypto currency with integrated block chain technology. It uses **peer-to- peer** communication between Tracker server to member and also member to member in order to share file. The detail protocol used by our Project is described `protocol.md` file in our repository.

## Project : 

>**Dhayananth Dharmalingam**
>


## How to get started with the Project
 
 1. To run the program Java 7 or greater version must be installed in the computer
 2. Download the Application file from Git repository (Here is the link : [click-here](https://github.com/UJM-INFO/2020-net-i))
 3. Install mysql database server and host it in local environment. ([tutorial-click-here](https://blog.templatetoaster.com/xampp-mysql/))
 3. Create a database with name `cheese-coin-member` and `cheese-coin-tracker`. (tutorial : https://blog.templatetoaster.com/xampp-mysql/)
 4. Import the given database file for Member (`member.sql`) as well as for the Tracker (`tracker.sql`).
 4. edit configuration files in root directory of the application folder (cheese-coin-code & tracker-server).
    * host-name: your mysql server host URI. (Ex: `localhost:3308`)
    * username: your mysql server user name. (Ex: `john`)
    * password: your mysql server password.
    * database: your mysql database. (Ex: member or Server)
 5. Import the project to Netbeans IDE  (Follow same step for server)
 6. Build the project (Follow same step for server)
 7. Run cheese-coin-system.jar file in build folder. (Follow same step for server)
 8. Run tracker-server.jar file
 9. In the User interface there are Specific buttons  which allows to see what  the application is performing. 

## Architecture
The Cheese-Coin-System developed with two modules, member and tracker server. Tracker server should initially be configured and should run before member start to run. Each member application maintains its own local database which hosted in the localhost using mysql server. Similarly, different member computers should connect their local database to their application by following the above-mentioned steps. Configuration files should be modified according to the given instruction to make successful communication to the ***Tracker server*** as well as the database. Initially, the member should register to the application. That step will create a record in Tracker server. Then when a new member joins again, he will be shared with the existing member list to share the network. Similarly, the network grows based on each member registration. The transaction, cheese-stack will be shared among members. 

#### NOTE!!!
> If you want to use two or more computers must join the same network.



## Stack
* Java 8
* MySql Database Server


## Reference 
### Code Reference 
**Asymmetric Encription** : https://www.quickprogrammingtips.com/java/java-asymmetric-encryption-decryption-example-with-rsa.html

**Configuration file** : https://www.codejava.net/coding/reading-and-writing-configuration-for-java-application-using-properties-class

**Digital Signature** : https://mkyong.com/java/java-digital-signatures-example/

 ### Library Reference 
 **Jackson.jar** : https://github.com/FasterXML/jackson

 **ORMLite** : https://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_1.html#Getting-Started

 **MySQL JDBC Connector** : https://dev.mysql.com/downloads/connector/j/







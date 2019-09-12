![Rotaract Sarayat El-Maadi's Logo](https://github.com/amrkh97/RacSarayatBackEnd/blob/master/RAC%20SARAYAT%20LOGO%202018-02.png)
# RacSarayatBackEnd

Web Services for Rotaract Sarayat El-Maadi's App to be used by the clubs members.

## Getting Started

* Please refer to the Readme File in the Database Repo, you can find it [Here](https://github.com/amrkh97/RacSarayatDB/blob/master/README.md)
* These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You will need to Install:

* **[Apache TomCat V9.0](https://tomcat.apache.org/download-90.cgi)**
* **[Eclipse EE](https://www.eclipse.org/downloads/packages/release/2019-06/r/eclipse-ide-enterprise-java-developers)**
* **[Postman](https://www.getpostman.com/downloads/)**
* **[Sqljdbc4-4.0](https://mvnrepository.com/artifact/com.microsoft.sqlserver/sqljdbc4/4.0)**

### Installing

After Installing all the programs and cloning the repo:

* **Setting Up The Server**
1. Open Eclipse.
2. Choose File -> Import -> Maven - > Existing Maven Project
3. Choose the Folder containing the repo then finish.
4. Choose From the Toolbar -> Window -> Show View -> Servers
5. Create a new server -> Choose TomCat v9.0 then follow instructions and finish.
7. Double click on newly created server.
8. Open Launch Configuration -> ClassPath -> Add External Jars
9. Choose Sqljdbc4-4.0.jar that you already downloaded
10. Click Apply -> Ok
11. Set the Port Numbers for the server to the following values then save:
    
```
    * tomcat Admin Port -> 7005
    * HTTP/1.1 -> 7070
    * AJP/1.3  -> 7009
```

* **Setting Up The ClassPath**
1. Right click on the project.
2. Choose BuildPath - > Configure Build Path
3. Choose Add Library -> Maven Dependencies
4. Choose Add Library -> JRE System Library
5. Choose Add Library -> Apache Tomcat v9.0
6. Choose Apply then Click on Java Compiler
7. Enable Project Specific Settings - > Set Compiler Compliance Level to 1.8 then Apply
8. If prompted to rebuild the workspace then accept.
9. Move to Project Facets
10. Tick on:

```
    * Dynamic Web Module -> 4.0
    * Java -> 1.8
    * Javascript -> 1.0
    * JAX_RS -> 2.1 
```
11. From the right corner select Runtimes.
12. Tick the Apache Tomcat v9.0 then click Apply.

---
**Your development environment is all set right now, Happy Coding :revolving_hearts:**
---

## Deployment

1. Right Click on the project -> Run As -> Run on Server
2. If the server gives an Error of timeout then please increase its maximum timeout from the servers view and try again.
3. If all goes well a page with 404-Not found should appear *No Worries it means we are on the right track*
4. Add the word api after the link and press enter, the sentence: "Server is running" should appear.
5. If the Sentence does not appear then please review your installation steps and review the classpath.

## Testing

For testing our APIs, Postman is used and you can find the collection ready for import [Here](https://github.com/amrkh97/RacSarayatDB/blob/master/Rotaract.postman_collection.json)

**Please don't forget to clone the Database Repo from [Here](https://github.com/amrkh97/RacSarayatDB)**
---
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* Java - JRE1.8_211

## Contributing

Please read [CONTRIBUTING.md](https://github.com/amrkh97/RacSarayatBackEnd/blob/master/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **[Amr Khaled](https://www.linkedin.com/in/amrkh97/)** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/amrkh97/RacSarayatBackEnd/blob/master/LICENSE) file for details

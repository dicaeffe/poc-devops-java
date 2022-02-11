# ComponentName
Status of the project.


## ABOUT
Short description of the ComponentName.


## Index
* [Getting Started](#getting-started)
	* [Project structure](#project-structure)
	* [Documentation](#documentation)
	* [Installation from scratch](#installation-from-scratch)
	* [Update the installation](#update-the-installation)
* [Roadmap](#roadmap)
* [Contact for support and contributions](#contact-for-support-and-contributions)
* [License and copyright](#license-and-copyright)
* [Notes](#notes)


## Getting Started


### Project structure
Files and folders.

```
.
├───bin                              # Folder of compiled code
├───src                              # Folder of source files
│   ├───main                         
│   │   ├───java                     # Folder of java source code
│   │   │   └───...                  
│   │   └───resources                # Folder of project resources and configurations
│   │       ├───META-INF             # Directory of Java meta-information
│   │       ├───datasource			 # Directory with scripts for datasource configuration
│   │       ├───log4j.properties     # Configuration of logs (used only in tests)
│   │       ├───control.properties   # Configuration of Controller connection
│   │       ├───model.properties     # Configuration of AMQP connection
│   │       ├───queue.properties     # Configuration of Database connection
│   │       └───...                  
│   └───test                         # Folder of tests
│       ├───java                     # Folder of java source code for unit tests
│       │   └───...                  
│       └───jmeter                   # Folder of JMeter's tests (performance tests, end-to-end tests, integration tests)
├───target                           # Folder of compiled code
│   └───...                          
├───docs                             # Folder of documentation files
│   ├───design						 # Directory with functional and technical documentation (swagger, datasource model, swagger, sequence diagrams, etc)
│   │   └───sequenceDiagram			 # Directory with sequence diagrams of flows
│   ├───installationGuide.md		 # Installation Guide of the project
│   └───releaseNote_YYYYMMDD_hhmm_id.md       # Release Notes (version, new/fixed files, test of new feature/fix)
├───.gitignore                       # File for Git ignore
├───Dockerfile                       # File of Docker image configuration
├───Dockerignore                     # File of Docker image ignored configuration files (e.g.: gitignore, readme, target, etc)
├───Jenkinsfile                      # File of Pipeline configurations for Jenkins build
├───mvnw                             # File of Maven Wrapper scripts for Linux environments
├───mvnw.cmd                         # File of Maven Wrapper scripts for Windows environments
├───pom.xml                          # File of Project Object Model (POM) definition for Maven build
└───README.md                        # File of tecnical documentation of the project
```

### Documentation

#### Interface
The documentation has been written in the [OpenAPI/Swagger](http://swagger.io/) format and is available:
- as example in the [swagger.yaml](docs/design/swagger.yaml) file.
- as updated document created dynamically by the java project by running the instance of the project and reaching:
	- SWAGGER-JSON: http://<host>/v3/api-docs
	- SWAGGER-UI: http://<host>/swagger-ui/index.html
where <host> can be localhost:8080.

The swagger can be also visualized as HTML page using one of those options:
* [Swagger UI](https://github.com/swagger-api/swagger-ui)
* [Swagger Editor](http://editor.swagger.io/)

#### Postman collection
A Postman collection is available in the [POC DevOps Java.postman_collection.json](docs/design/POC DevOps Java.postman_collection.json) file.

#### Sequence diagram
An example of Sequence diagram is available in the [design](docs/design) directory.


### Installation from scratch
See the [installationGuide.md](docs/installationGuide.md) file.


### Update the installation
See the latest releaseNote file into the [docs](docs/) directory.


## Roadmap
See the Redmine for issues and backlog.
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#production-ready)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)


## Contact for support and contributions
team email.


## License and copyright
Of the full component and of third parties components.


## Notes
File created from the README_template_v.1.0
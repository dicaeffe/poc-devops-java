# ComponentName installation guide

## ABOUT
This document describes the steps required for the installation of the package.

## Index
* [Prerequisites and Dependencies](#prerequisites-and-dependencies)
* [Build](#build)
* [Installation](#installation)
	* [Datasource](#datasource)
	* [Configuration](#configuration)
* [Execution](#execution)
* [Test](#test)
* [Rollback](#rollback)
* [Contact for support and contributions](#contact-for-support-and-contributions)
* [License and copyright](#license-and-copyright)

## Prerequisites and Dependencies
All dependencies for the new environment.

## Build
- With Maven installed
	- Open the shell into the directory of the pom.xml
	- Execute the command reported below substituting the <command> tag with the one you need (package, install or deploy).
		```
		mvn clean <command>
		```
- Without Maven from Linux
	- Open the shell into the directory of the pom.xml
	- Execute the command reported below substituting the <command> tag with the one you need (package, install or deploy).
		```
		./mvnw <command>
		```
- Without Maven from Window
	- Open the shell into the directory of the pom.xml
	- Execute the command reported below substituting the <command> tag with the one you need (package, install or deploy).
		```
		./mvnw.cmd <command>
		```

## Installation
TBD

### Datasource
1. Execute a backup of the datasource.
2. Run against the datasource all the scripts in the [datasource](src/resources/datasource/) directory.

### Configuration
TBD

## Execution
TBD
```
cmd to be thrown
```

## Test
If the installation has been successful, execute the tests reported below to test functional behaviour.
TBD

# Rollback
1. Restore the datasource from the backup created in the first step of the [datasource](#datasource) paragraph.

## Contact for support and contributions
team email.

## License and copyright
Of the full component and of third parties components.

File created from the installationGuide_template_v.1.0.md
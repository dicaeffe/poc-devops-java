# ComponentName installation guide


## ABOUT
This document describes the steps required for the installation of the package.


## Index
* [Prerequisites and Dependencies](#prerequisites-and-dependencies)
* [Build](#build)
* [Installation](#installation)
	* [Deploy on a local container](#deploy-on-a-local-container)
	* [Deploy on Kubernates](#deploy-on-kubernates)
	* [Install as WORKLOAD on RANCHER GUI](#install-as-workload-on-rancher-gui))
	* [Datasource](#datasource)
	* [Configuration](#configuration)
* [Execution](#execution)
* [Test](#test)
* [Rollback](#rollback)
* [Contact for support and contributions](#contact-for-support-and-contributions)
* [Notes](#notes)


## Prerequisites and Dependencies
All dependencies for the new environment.


## Build
- **Execute maven install**.
	- With Maven installed:
		- Open the shell into the directory of the pom.xml
		- Execute the command reported below substituting the <command> tag with the one you need (package, install or deploy).
			```sh
			mvn clean ${command}
			```
	- Without Maven from Linux:
		- Open the shell into the directory of the pom.xml
		- Execute the command reported below substituting the <command> tag with the one you need (package, install or deploy).
			```sh
			./mvnw ${command}
			```
	- Without Maven from Window:
		- Open the shell into the directory of the pom.xml
		- Execute the command reported below substituting the <command> tag with the one you need (package, install or deploy).
			```sh
			./mvnw.cmd ${command}
			```
- **Create the dependency folders**.
	```sh
	mkdir target/dependency
	(cd target/dependency; jar -xf ../*.jar)
	```
- If not exists, **create a Dockerfile**.
	```sh
	cat > Dockerfile << EOF
	FROM openjdk:8-jdk-alpine AS builder
	WORKDIR target/dependency
	ARG APPJAR=target/*.jar
	COPY \${APPJAR} app.jar
	RUN jar -xf ./app.jar

	FROM openjdk:8-jre-alpine
	VOLUME /tmp
	ARG DEPENDENCY=target/dependency
	COPY --from=builder \${DEPENDENCY}/BOOT-INF/lib /app/lib
	COPY --from=builder \${DEPENDENCY}/META-INF /app/META-INF
	COPY --from=builder \${DEPENDENCY}/BOOT-INF/classes /app
	ENTRYPOINT ["java","-cp","app:app/lib/*","poc.devops.PocDevopsJavaApplication"]
	```
- **Build the container image**.
	- Any image on docker is identified by
		```sh
		${repository}/${imageName}:${versiontag}
		```
    - From repository:
		```sh
		docker build ${urlRepository}#${branch}
		```
	- From the main directory (where are pom.xml and Dockerfile). 
	   n.b.: the tag is made of an *ID* (localhost/springguides) and app name.
		```sh
		docker build -t ${repository}/${imageName}:${versiontag} .
		```
	- From Jenkins:
		- Use a pipeline that will execute the Jenkinsfile. An example is the build http://docker.rete.dom:8080/job/poc/job/poc-devops-java/8/
- Login (deplorate)
	```sh
	docker login -u ${username} -p ${password}
	```
- Tag the image to the remote repository.
	```sh
	docker tag ${repository}/${localImageName}:${versiontag} ${repository}/${newImageName}:${versiontag}
	```
- Push the image to the remote repository.
	```sh
	docker push ${repository}/${newImageName}:${versiontag}
	```


## Installation


### Deploy on a local container
- **Prerequisite**: docker.
- From the CMD execute:
		```
		docker run -p 8080:8080 <project/username>/\<repository>:\<versiontag>
		```
	- n.b.: the \<versiontag> is optional. If not specified, will manage the last tag.
	- other options for the docker run command
		|info|description|
		| --- | --- |
		|-e "key=value"		|		set an environment variable|
		|--name \<myapp>	|			set the app name|
		|-p 8080:8080		|		publish all exposed ports to the host interfaces. \<internalPort>:\<externalPort> |
		|-t \<username>/\<repo>:\<tag> |	tag |

### Deploy on Kubernates
- Deploy to Kubernates
	- You must have a YAML deployment file (if you do not have it, you can create by running on the terminal those 3 commands)
		```sh
		kubectl create deployment ${imageName} --image=${repository}/${imageName}:${versiontag} --dry-run=client -o=yaml > deployment.yaml
		echo --- >> deployment.yaml
		kubectl create service clusterip ${imageName} --tcp=8080:8080 --dry-run=client -o=yaml >> deployment.yaml
		```
	- Edit the YAML
	- Apply the YAML
		```sh
		kubectl apply -f deployment.yaml
		```
- Connect to the application
	- Create an SSH tunnel
		```sh
		kubectl port-forward svc/${imageName} 8080:8080
		```


### Install as WORKLOAD on RANCHER GUI
- **Create a ConfigMap on RANCHER GUI**
    - In the upper left corner, click ☰ > *Cluster Management*.
    - Go to the cluster that has the workload that should reference a ConfigMap and click Explore.
    - In the left navigation bar, click More Resources > Core > ConfigMaps.
    - Click Create.
    - Enter a Name for the Config Map (e.g.: *<component-name-config>*).
    	- NOTE: Kubernetes classifies ConfigMaps as secrets, and no two secrets in a project or namespace can have duplicate names. Therefore, to prevent conflicts, your ConfigMaps must have a unique name among the other certificates, registries, and secrets within your workspace.
    - Select the Namespace you want to add Config Map to.
    - On the Data tab, add a key-value pair to your ConfigMap. Add as many values as you need. You can add multiple key value pairs to the ConfigMap by copying and pasting. Alternatively, use Read from File to add the data. Note: If you need to store sensitive data, use a secret, not a ConfigMap.
    - Click *Create* button.
- **Deploy the Workload on RANCHER GUI**
    - In the upper left corner, click ☰ > *Cluster Management*.
    - Go to the cluster where you want to upgrade a workload and click Explore.
    - In the left navigation bar, click Workload.
    - Click *Create* button.
    - Choose the type of workload (i.e. Deployment).
    - Select the namespace where the workload will be deployed.
    - Enter a Name for the workload (e.g.: *<component-name-deployment>*) and a description.
    - Go into the "General" tab.
    	- *Container Image field*: enter the name of the Docker image that you want to deploy to the project, optionally prefacing it with the registry host (e.g. quay.io, registry.gitlab.com, etc.). This field is case-sensitive. During deployment, Rancher pulls this image from the specified public or private registry. If no registry host is provided, Rancher will pull the image from Docker Hub. Enter the name exactly as it appears in the registry server, including any required path, and optionally including the desired tag (e.g. registry.gitlab.com/user/path/image:tag). If no tag is provided, the latest tag will be automatically used.
    	- Click *Add Port* button to enter a port mapping, which enables access to the application inside and outside of the cluster.
    		- From the *Service Type* drop-down, make sure that *NodePort* is selected.
    		- Insert the *Private Container Port* number.
    		- Select the TCP *Protocol*.
    	- *Environment Variables*: use this section to either specify environment variables for your workload to consume on the fly, or to pull them from another source, such as a secret or ConfigMap.
    		- click on the *Add Variable* button.
    		- Set the type (e.g.: ConfigMap).
    		- Fill the other fields to select the variables source (e.g: select the ConfigMap from the drop-down.
    - If required, configure the remaining options:
    	- Node Scheduling
    	- Health Check
    	- Volumes: use this section to add storage for your workload. You can manually specify the volume that you want to add, use a persistent volume claim to dynamically create a volume for the workload, or read data for a volume to use from a file such as a ConfigMap.
    		- When you are deploying a Stateful Set, you should use a Volume Claim Template when using Persistent Volumes. This will ensure that Persistent Volumes are created dynamically when you scale your Stateful Set.
    		- Scaling/Upgrade Policy
    			- AMAZON NOTE FOR VOLUMES: to mount an Amazon EBS volume:
    				- In Amazon AWS, the nodes must be in the same Availability Zone and possess IAM permissions to attach/unattach volumes.
    				- The cluster must be using the AWS cloud provider option. For more information on enabling this option see Creating an Amazon EC2 Cluster or Creating a Custom Cluster.
    	- Command
    	- Networking
    	- Labels & Annotations
    	- Security and Host Config
    - Click *Create* button.


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
- **Check actuator**
	```sh
	curl --location --request GET 'http://localhost:8080/actuator'
	```
- **Postman collection** in the ```\docs\design\POC DevOps Java.postman_collection.json```


# Rollback
1. Restore the datasource from the backup created in the first step of the [datasource](#datasource) paragraph.


## Contact for support and contributions
team email.


## Notes
File created from the installationGuide_template_v.1.0.md
# ComponentName release note


## ABOUT
This document explains the new features and fixes released with the last version and describes the steps required for the update of the installation of ComponentName.


## Index
* [Contents released](#contents-released)
* [Elements released](#elements-released)
* [Update of the installation](#update-of-the-installation)
	* [Involved environments](#involved-environments)
	* [Update steps](#update-steps)
* [Test fix](#test-fix)
* [Rollback](#rollback)
* [Contact for support and contributions](#contact-for-support-and-contributions)
* [Notes](#notes)


## Contents released
Link the reference of new requirements, bugs and defects.

| Reference | Description |
| --------- | ----------- |
| RedmineId | title       |


## Elements released
List all the new files released.

| Filename  | Description   | Path |
| --------- | ------------- | ---- |
| pack.jar  | executable    | /a/b |
| conf.prop | configuration | /a/b |


## Update of the installation
Description of the steps for the update of the installation


### Involved environments
[optional]
List of the environments (machines, networks, containers, etc) involved.

* IP 127.0.0.1


### Update steps
1. Access to the machine with user "username".

2. Stop the service
```
systemctl stop my-service-prod
```

3. Make the backup of the previous JAR file.
```
cp /home/username/services/my-service/my-service-1.0-master.jar /home/username/services/my-service/backup_YYYYMMDD_hhmm_id/my-service-1.0-master.jar
```

4. Replace the old JAR file with the one released in the package (see [Elements released](#elements-released)

5. Check if permissions of the new JAR are the same of the old one.

6. Start the service
```
systemctl start my-service-prod
```


## Test fix
If the update of the installation has been successful, execute the tests reported below to test functional behaviour.

1. TBD


# Rollback
1. Stop the service
```
systemctl stop my-service-prod
```

2. Restore the backup of the previous JAR file created in the step of the [Update steps](#update-steps).
```
cp /home/username/services/my-service/backup_YYYYMMDD_hhmm_id/my-service-1.0-master.jar /home/username/services/my-service/my-service-1.0-master.jar
```

3. Start the service
```
systemctl start my-service-prod
```


## Contact for support and contributions
team email.


## Notes
File created from the releaseNote_template_v.1.0.md
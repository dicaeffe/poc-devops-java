# ComponentName release note


## ABOUT
This document explains the new features and fixes released with this version and describes the steps required for the update of the installation of ComponentName.


## Index
* [Changelog](#changelog)
* [Download of the new release](#download-of-the-new-release)
* [Elements released](#elements-released)
* [Update of the installation](#update-of-the-installation)
	* [Involved environments](#involved-environments)
	* [Update steps](#update-steps)
* [Test fix](#test-fix)
* [Rollback](#rollback)
* [Contact for support and contributions](#contact-for-support-and-contributions)
* [Notes](#notes)


## Changelog
Link the reference of new requirements, bugs and defects.

| Ticket    | Description |
| --------- | ----------- |
| RedmineId | title       |
| Asana id  | title       |
| Jira id   | title       |
| RTC id    | title       |

## Download of the new release

| URL      | https://please.click/here |
| FILENAME | VOL-notartel-2.1.47.zip |
| SIZE     | 161139730 |
| SHA256   | 5598d860e86507f895d33191029139b05ec9250f68f379d48c3e015a92b833b1 |

## Elements released
List all the new files released.

| Filename  | Description   | Path |
| --------- | ------------- | ---- |
| pack.jar  | executable    | /a/b |
| conf.prop | configuration | /a/b |

## Update of the installation
Description of the steps for the update of the installation.
If this is the first release, please read the Installation Guide before making any action on this Release Note.


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

| Name      | Description | email          |
| --------- | ----------- | -------------- |
| Team name | Who we are  | contact@us.now |


## Notes
File created from the releaseNote_template_v.1.0.md
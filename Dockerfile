FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.4_11 as packager
VOLUME /tmp
ADD /target/poc-devops-java-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

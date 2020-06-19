FROM ibmjava:8-sdk AS builder
LABEL maintainer="IBM Java Engineering at IBM Cloud"

WORKDIR /app
RUN apt-get update && apt-get install -y maven

COPY pom.xml .
RUN mvn -N io.takari:maven:wrapper -Dmaven=3.5.0

COPY . /app
RUN ./mvnw install
#Env Variables
ARG bx_dev_user=root
ARG bx_dev_userid=1000
RUN BX_DEV_USER=$bx_dev_user
RUN BX_DEV_USERID=$bx_dev_userid
RUN if [ $bx_dev_user != "root" ]; then useradd -ms /bin/bash -u $bx_dev_userid $bx_dev_user; fi

FROM adoptopenjdk/openjdk8-openj9:alpine-slim

RUN mkdir /opt/app

RUN mkdir /root/.postgresql/

COPY root.crt /root/.postgresql/

COPY --from=builder /app/target/pearlchaindbm.jar /opt/app/pearlchaindbm.jar

ENTRYPOINT ["java","-jar", "/opt/app/pearlchaindbm.jar" ]

FROM adoptopenjdk/openjdk8-openj9:alpine-slim

RUN mkdir /root/.postgresql/

COPY https://github.com/Sherpa99/pearlchain-poc/blob/master/root.crt /root/.postgresql/

COPY target/pearlchaindbm.jar /

ENTRYPOINT ["java","-jar", "pearlchaindbm.jar" ]

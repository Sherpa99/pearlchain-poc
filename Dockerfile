FROM adoptopenjdk/openjdk8-openj9:alpine-slim

RUN mkdir /root/.postgresql/

COPY root.crt /root/.postgresql/

COPY --from=builder /target/pearlchaindbm.jar /

ENTRYPOINT ["java","-jar", "pearlchaindbm.jar" ]

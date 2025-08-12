
FROM eclipse-temurin:23-jdk
ENV TOMCAT_VERSION=11.0.0-M19
ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH
RUN curl -fSL https://downloads.apache.org/tomcat/tomcat-11/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -o tomcat.tar.gz && \
    mkdir -p $CATALINA_HOME && \
    tar -xvzf tomcat.tar.gz -C $CATALINA_HOME --strip-components=1 && \
    rm tomcat.tar.gz
EXPOSE 8080
CMD ["catalina.sh", "run"]

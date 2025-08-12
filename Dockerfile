# Use Eclipse Temurin JDK 23 (community supported build of OpenJDK)
FROM eclipse-temurin:23-jdk

# Set environment variables
ENV TOMCAT_VERSION=11.0.0-M19
ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

# Download and install Tomcat 11
RUN curl -fSL https://downloads.apache.org/tomcat/tomcat-11/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -o tomcat.tar.gz && \
    mkdir -p $CATALINA_HOME && \
    tar -xvzf tomcat.tar.gz -C $CATALINA_HOME --strip-components=1 && \
    rm tomcat.tar.gz

# Optionally deploy your WAR
# COPY target/yourapp.war $CATALINA_HOME/webapps/

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]

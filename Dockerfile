#FROM openjdk:8
#EXPOSE 9800
#ADD /target/productapp-0.0.1-SNAPSHOT.jar productapp.jar
#ENV JAVA_OPTS $JAVA_OPTS
#ENV SPRING_PROFILES_ACTIVE $SPRING_PROFILES_ACTIVE
#HEALTHCHECK CMD curl --fail http://productapp:9800/actuator/health || exit 1
#ENTRYPOINT ["java", "-jar", "productapp.jar"]
#
FROM openjdk:21-jdk
WORKDIR /app
COPY /target/productApp-0.0.1-SNAPSHOT.jar /app/productApp.jar
EXPOSE 5000
CMD ["java", "-jar", "productApp.jar"]
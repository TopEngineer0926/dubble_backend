FROM openjdk:8

HEALTHCHECK --interval=5s --timeout=5s --retries=3 CMD wget http://localhost:8080/api/v1/system/health?secure=backendMogree123! -q -O - > /dev/null 2>&1

COPY dubble-latest.jar /opt/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-jar","/opt/app.jar"]
EXPOSE 8080
FROM eclipse-temurin:17

COPY build/libs/*.jar /opt/app/application.jar


RUN addgroup --system spring
RUN adduser --system spring --ingroup spring

USER spring:spring

CMD java -jar /opt/app/application.jar
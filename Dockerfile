FROM openjdk:17
EXPOSE 5612
ADD appointmentAppServer.war appointmentAppServer.war
ENTRYPOINT ["java", "-jar", "/appointmentAppServer.war"]
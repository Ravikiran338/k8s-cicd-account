FROM openjdk:8
MAINTAINER Radiant Digital
EXPOSE 3333
ADD target/*.jar /msa-account.jar
RUN bash -c 'touch /msa-account.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-account.jar"]
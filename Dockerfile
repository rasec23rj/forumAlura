
# Build Stage for Spring boot application image
FROM forum
#RUN apt update && apt upgrade -y && apt install openjdk-11-jdk openjdk-11-jre maven -y

WORKDIR /home
COPY forum /home
RUN ls -la
RUN pwd
EXPOSE 8080
EXPOSE 5005
RUN mvn install -DskipTests

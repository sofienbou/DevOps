FROM ubuntu:latest
LABEL authors="Lenovo"

ENTRYPOINT ["top", "-b"]


FROM openjdk:11
EXPOSE 8089
ADD target/achat-1.0.jar Produit-Docker.jar
ENTRYPOINT ["java", "-jar", "Produit-Docker.jar"]
# Usar uma imagem base do OpenJDK (no caso, JDK 17)
FROM openjdk:17-jdk-slim

# Diretório de trabalho na imagem
WORKDIR /app

# Copiar o arquivo JAR para dentro da imagem
COPY target/users-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]

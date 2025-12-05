FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR para dentro da imagem
COPY cpt-web.jar app.jar

# Expõe a porta (opcional)
EXPOSE 8080

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.jar"]
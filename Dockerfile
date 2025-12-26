FROM eclipse-temurin:21-jre

WORKDIR /app

ENV TZ=America/Fortaleza

# Instala o tzdata para aplicar corretamente o timezone
RUN apt-get update && apt-get install -y tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && \
    rm -rf /var/lib/apt/lists/*

COPY cpt-web.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
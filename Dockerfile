# Estágio 1: Build da aplicação
FROM gradle:8.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Concede permissão de execução ao gradle wrapper
RUN chmod +x ./gradlew

# Define limites de memória agressivos para instâncias gratuitas do Render (512MB RAM)
# Reduzimos o heap do Gradle e da JVM para caber no limite
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx512m -Dorg.gradle.daemon=false -Dorg.gradle.workers.max=1"

# Executa o build. Usamos --no-configuration-cache para evitar problemas em ambientes Docker
RUN ./gradlew :server:installDist --no-daemon --no-configuration-cache --stacktrace

# Estágio 2: Imagem de execução
FROM eclipse-temurin:11-jre-focal
EXPOSE 8080

# Cria o diretório da aplicação
RUN mkdir /app
COPY --from=build /home/gradle/src/server/build/install/server /app/

WORKDIR /app

# Define a porta padrão via variável de ambiente
ENV PORT=8080

# Garante permissão de execução para o script do servidor
RUN chmod +x ./bin/server

# Inicia o servidor
CMD ["./bin/server"]

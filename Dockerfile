# Estágio 1: Build da aplicação
FROM gradle:8.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Concede permissão de execução ao gradle wrapper antes de rodar o build
RUN chmod +x ./gradlew

# Executa o build do servidor e do frontend web
RUN ./gradlew :server:installDist --no-daemon

# Estágio 2: Imagem de execução
FROM eclipse-temurin:11-jre-focal
EXPOSE 8080

# Cria o diretório da aplicação
RUN mkdir /app
COPY --from=build /home/gradle/src/server/build/install/server /app/

WORKDIR /app

# Define a porta padrão via variável de ambiente (O Render define a PORT automaticamente)
ENV PORT=8080

# Garante permissão de execução para o script do servidor
RUN chmod +x ./bin/server

# Inicia o servidor
CMD ["./bin/server"]

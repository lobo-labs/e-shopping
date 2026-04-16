# Estágio 1: Build da aplicação
FROM gradle:8.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Executa o build do servidor e do frontend web
# A task :server:installDist já inclui o frontend web graças à configuração que fizemos no build.gradle.kts
RUN ./gradlew :server:installDist --no-daemon

# Estágio 2: Imagem de execução
FROM openjdk:11-jre-slim
EXPOSE 8080

# Cria o diretório da aplicação
RUN mkdir /app
COPY --from=build /home/gradle/src/server/build/install/server /app/

WORKDIR /app

# Define a porta padrão via variável de ambiente (pode ser sobrescrita no docker run)
ENV PORT=8080

# Inicia o servidor
CMD ["./bin/server"]

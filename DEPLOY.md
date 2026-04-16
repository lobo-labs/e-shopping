# Guia de Deploy (VPS & Docker)

Este guia descreve como realizar o deploy da aplicação eShopping (Ktor + Compose Web) em uma VPS utilizando Docker.

## 🚀 Como funciona o projeto
O projeto é um Monorepo:
- `:composeApp`: Frontend Compose Multiplatform (WasmJs).
- `:server`: Backend Ktor que serve a API e os arquivos estáticos do frontend.
- `:shared`: Código compartilhado entre as plataformas.

O build está configurado para que, ao rodar o servidor, o frontend seja compilado e movido para `server/src/main/resources/dist` automaticamente.

---

## 🐳 Deploy com Docker (Recomendado)

### 1. Construir a imagem
Na raiz do projeto, execute:
```bash
docker build -t eshopping-app .
```

### 2. Rodar o container
```bash
docker run -d -p 8080:8080 --name eshopping-instance eshopping-app
```
A aplicação estará disponível em `http://seu-ip:8080`.

---

## 🛠 Deploy Manual (Sem Docker)

Se preferir rodar diretamente na JVM da VPS:

### 1. Gerar o pacote de instalação
Na sua máquina local:
```bash
./gradlew :server:installDist
```

### 2. Transferir para a VPS
Copie a pasta gerada em `server/build/install/server` para o seu servidor:
```bash
scp -r server/build/install/server user@seu-ip:/home/user/app
```

### 3. Executar na VPS
Dentro da pasta no servidor:
```bash
./bin/server
```

---

## ⚙️ Configurações Adicionais

### Porta
O servidor utiliza a porta `8080` por padrão. Para alterar, defina a variável de ambiente `PORT`:
```bash
docker run -e PORT=3000 -p 3000:3000 eshopping-app
```

### Manter o processo rodando (Manual)
Se não estiver usando Docker, utilize o **PM2** ou crie um serviço no **systemd** para garantir que o servidor reinicie em caso de falhas ou reboot da VPS.

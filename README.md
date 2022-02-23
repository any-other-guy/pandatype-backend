# pandatype-backend

## Deploy On Production Environment

### On Local Machine

1. Generate p12 keystore from SSL Certificate
   ```sh
   openssl pkcs12 -export -clcerts -in domain.cert.pem -inkey private.key.pem -out springboot.p12
   ```
2. Send p12 keystore to remote
   ```sh
   scp -i AWS_KEY.pem .\springboot.p12 ec2-user@XXXXXXXXXXXXXXXX.amazonaws.com:springboot.p12
   ```
3. Connect to AWS EC2 Instance
    ```sh
   ssh -i "AWS_KEY.pem" ec2-user@XXXXXXXXXXXXXXXX.amazonaws.com
   ```

### On Remote (AWS EC2 Instance)

1. Clone the repo
   ```sh
   git clone https://github.com/any-other-guy/pandatype-backend.git
   cd pandatype-backend
   ```
2. Setup all prerequisite software
   ```sh
   bash ec2_setup.sh
   ```
3. Copy SSL Certificate p12 keystore to its respective services at **/resource, also remember to enable SSL in application.properties
   ```sh
   cd
   cp springboot.p12 ./pandatype-backend/leaderboard/src/main/resources/
   cp springboot.p12 ./pandatype-backend/typingtest/src/main/resources/
   cp springboot.p12 ./pandatype-backend/auth/src/main/resources/
   ...
   ```
4. Start services
   ```sh
   cd pandatype-backend
   bash server.sh
   ```

## Build and Run Locally

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/any-other-guy/pandatype-backend.git
   ```
2. Run the local build bash script
   ```sh
   cd pandatype-backend
   bash local.sh
   ```
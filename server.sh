# build jar files for services
cd auth; mvn package -DskipTests; cd ..;
cd typingtest; mvn package -DskipTests; cd ..;
cd leaderboard; mvn package -DskipTests; cd ..;

# generate private/public keys for auth service
cd auth/src/main/resources;
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048;
openssl rsa -pubout -in private.pem -out public_key.pem;
openssl pkcs8 -topk8 -in private.pem -nocrypt -out private_key.pem
cp public_key.pem ../../../../leaderboard/src/main/resources;
cd ../../../../;

# cleanup and restart
docker-compose down;
docker rmi -f $(docker images -a | grep -v "mysql" | awk 'NR>1 {print $3}')
docker-compose up;
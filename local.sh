cd auth; ./mvnw package -DskipTests; cd ..;
cd typingtest; ./mvnw package -DskipTests; cd ..;
cd leaderboard; ./mvnw package -DskipTests; cd ..;
docker-compose down;
docker rm -f $(docker ps -a -q);
docker rmi -f $(docker images -a | grep -v "mysql" | awk 'NR>1 {print $3}')
docker volume rm $(docker volume ls -q);
docker-compose up;
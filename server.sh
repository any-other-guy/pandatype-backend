cd auth; mvn package -DskipTests; cd ..;
cd typingtest; mvn package -DskipTests; cd ..;
cd leaderboard; mvn package -DskipTests; cd ..;
docker-compose down;
docker rm -f $(docker ps -a -q);
docker rmi -f $(docker images -a | grep -v "mysql" | awk 'NR>1 {print $3}')
docker-compose up;
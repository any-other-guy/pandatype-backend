wget https://download.java.net/java/GA/jdk17/0d483333a00540d886896bac774ff48b/35/GPL/openjdk-17_linux-x64_bin.tar.gz;
tar xvf openjdk-17_linux-x64_bin.tar.gz;
sudo mv jdk-17 /opt/;

sudo tee /etc/profile.d/jdk.sh <<EOF
export JAVA_HOME=/opt/jdk-17
export PATH=$PATH:$JAVA_HOME/bin
EOF;

source /etc/profile.d/jdk.sh;

sudo yum update -y;
sudo yum install git -y;

sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo;
sudo sed -i s/$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo;
sudo yum install -y apache-maven;

sudo yum update -y;
sudo yum install -y docker;
sudo service docker start;
sudo usermod -a -G docker ec2-user;
sudo chkconfig docker on;

sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose;
sudo chmod +x /usr/local/bin/docker-compose;

sudo reboot;
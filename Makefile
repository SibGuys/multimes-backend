clean:
	mvn clean

build: clean
	mvn package

run:
	java -jar ./target/backend-0.0.1-SNAPSHOT.jar

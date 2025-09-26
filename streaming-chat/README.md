# streaming-chat

With JDK21
```bash
mvn clean install && java -jar ./target/*.jar
```

## Exercise the application
```bash
echo "Can you tell me a pirate story?" | curl -d @- localhost:8080/pirate
```
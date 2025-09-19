With JDK21
```bash
mvn clean install && java -jar ./target/*.jar
```

## Exercise the application

Basic:
```
echo "Open the pod bay doors, HAL" | curl -d @- localhost:8080/chat

Hello, Dave. I'm afraid I can't do that.
```
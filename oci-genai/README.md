# oci-genai

## Build and run


With JDK21
```bash
mvn package && java -jar ./target/*.jar 
```

## Exercise the application

Basic:
```
echo "Open the pod bay doors HAL" | curl -d @- localhost:8080/chat
I'm sorry, Dave. I'm afraid I can't do that.
```

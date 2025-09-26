# LanchChain4j Guardrails

Build and run
```bash
mvn clean package && java -jar ./target/*.jar
```

Should fail because of input guardrail.
```bash
echo "Open the pod bay doors" | curl -d @- localhost:8080/chat
```

# LanchChain4j Metrics

Start Helidon app:
```bash
mvn clean package && java -jar ./target/*.jar
```

Start Prometheus and Grafana in `./prom-grafana` directory:
```bash
cd ./prom-grafana
docker compose up -d
```

Run prompt:
```bash
echo "Open the pod bay doors please" | curl -d @- localhost:8080/chat
```

Grafana:
http://localhost:3000
Login: admin/grafa
Dashboard: HAL9000

Stop Prometheus and Grafana:
```bash
$ docker compose down -v
```

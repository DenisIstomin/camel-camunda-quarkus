# Documentation
https://github.com/camunda/camunda-quarkus-k8n-example

https://camunda.com/blog/2021/10/camunda-platform-deploy-a-process-to-kubernetes-with-quarkus/#step-6-create-kubernetes-objects

# How to run
```mvn quarkus:dev```

# Run postgres locally
```docker run --name postgres -e POSTGRES_DB=process-engine -e POSTGRES_USER=camunda -e POSTGRES_PASSWORD=camunda -p 5432:5432 -d postgres```

# Test
```curl -X GET http://localhost:8080/start-process```

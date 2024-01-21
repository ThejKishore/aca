```shell
dapr run \
--app-id employee-svc \
--app-port 7101 \
--app-protocol http \
--dapr-http-port 3501 \
--dapr-grpc-port 50001 \
--log-level debug 
```
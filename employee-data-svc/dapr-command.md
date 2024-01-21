```shell

dapr run \
 --app-id employee-data-svc \
--app-port 7100 \
--app-protocol http \
--dapr-http-port 3500 \
--dapr-grpc-port 50000 \
--log-level debug

```
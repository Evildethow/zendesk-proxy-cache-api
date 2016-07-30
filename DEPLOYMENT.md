# Docker

## Build

```
sbt docker:publishLocal
```

## Run

```
docker run --name zendesk-proxy-cache-api -e DB_USER=dbuser -e DB_PASSWORD=dbpass -e DB_NAME=dbname -e ZD_USERNAME="user@domain" -e ZD_PASSWORD="mypassword" -e ZD_URL="https://mydomain.zendesk.com" -e ZD_ORGS="[:]" -d --link DATABASE_INSTANCE_NAME:database -p 9090:9000 APPLICATION_IMAGE
```

- `DATABASE_INSTANCE_NAME` - name of your Postgresql docker instance
- `APPLICATION_IMAGE` - id or name of application docker image
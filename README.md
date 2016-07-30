# Config

## ZenDesk (TODO)

Provide your own external configuration file to configure ZenDesk settings.

For example `/Users/me/override.conf`.

Set the following:

```
include "application.conf"

zd {
    username="username@domain"
    password="mypassword"
    url="https://mydomain.zendesk.com"
    # list of orgs for worker to query (use empty list to disable filter)
    organisations=["aorgexternalname","anotherorgexternalname"]
}
```

# Run

Specify the config at run:

```
sbt run -Dconfig.file=/Users/me/override.conf
```




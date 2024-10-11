# Camel Reproducers

This repository contains reproducer projects for Apache Camel issues.

## Client request validation and empty body

There is a bug if you enable the client request validation and the body is empty when there is no need for a body.

This repository contains a sample 'HelloRoute' with one endpoint: `GET /api/hello` that returns a simple JSON message.

```bash
# fails
$ curl -H "Content-Type: application/json" localhost:8080/api/hello
Invalid JSon payload.

# succeeds
$ curl localhost:8080/api/hello
{"message":"hello world"}
```

If you specify a content type, the request will fail with a 400 saying 'Invalid JSon payload.'.

It seems like there is a problem in the [DefaultRestClientRequestValidator](https://github.com/apache/camel/blob/main/core/camel-support/src/main/java/org/apache/camel/support/processor/DefaultRestClientRequestValidator.java#L89).

The body is resolved as an empty string and not as a `null` value.

If the client request validation is disabled, the request will succeed.

## Tomcat standalone and JNDI

There is a problem if the application uses the `json-validator`spring boot starter and is deployed in a standalone tomcat.

See https://github.com/spring-projects/spring-boot/issues/35091

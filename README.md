# NFL Betting Service

Java/Spring MVC backend for the NFL betting app. The service is packaged as a WAR and uses PostgreSQL through Hibernate/HikariCP.

## Configuration

The Spring datasource reads database settings from environment variables:

```sh
DATABASE_URL=jdbc:postgresql://localhost:5432/nfl_betting
DATABASE_USERNAME=
DATABASE_PASSWORD=
```

Keep local API keys and screenshots outside the repository.

## Build

```sh
mvn clean package
```

The generated WAR is written to `target/` and should not be committed.

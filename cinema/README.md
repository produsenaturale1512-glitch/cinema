# Cinema Application

Spring Boot application for managing cinema films, schedules, and reservations.

## Run locally
1. Ensure Java 17+ is installed.
2. From the project root, start the app:
   ```bash
   ./mvnw spring-boot:run
   ```
3. The server starts on http://localhost:8080 and initializes the SQLite `cinema.db` file automatically.

## Build a runnable JAR
```bash
./mvnw package
java -jar target/cinema-1.0.0.jar
```

## Create a ZIP for IntelliJ IDEA
You can generate a clean archive of the current source using Git:
```bash
git archive --format zip --output ../cinema-intellij.zip HEAD
```
The ZIP will be created one level above the project directory (e.g., `/workspace/cinema/cinema-intellij.zip`). Import that archive into IntelliJ IDEA.

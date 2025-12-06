package cinema;

import cinema.util.DBManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication {
    public static void main(String[] args) {
        DBManager.createTables(); // creează tabele la pornire
        SpringApplication.run(CinemaApplication.class, args);
    }
}

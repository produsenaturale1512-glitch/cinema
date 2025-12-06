package cinema.util;
import java.sql.*;

public class DBManager {
    private static final String URL = "jdbc:sqlite:cinema.db";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTables() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sqlFilm = "CREATE TABLE IF NOT EXISTS Film (id INTEGER PRIMARY KEY AUTOINCREMENT, titlu TEXT NOT NULL, gen TEXT NOT NULL, durata INTEGER NOT NULL, popularitate INTEGER NOT NULL);";
            String sqlSala = "CREATE TABLE IF NOT EXISTS Sala (id INTEGER PRIMARY KEY AUTOINCREMENT, nume TEXT NOT NULL, randuri INTEGER NOT NULL, locuriPeRand INTEGER NOT NULL);";
            String sqlProgram = "CREATE TABLE IF NOT EXISTS ProgramFilm (id INTEGER PRIMARY KEY AUTOINCREMENT, film_id INTEGER NOT NULL, sala_id INTEGER NOT NULL, data TEXT NOT NULL, ora TEXT NOT NULL, FOREIGN KEY(film_id) REFERENCES Film(id), FOREIGN KEY(sala_id) REFERENCES Sala(id));";
            String sqlClient = "CREATE TABLE IF NOT EXISTS Client (id INTEGER PRIMARY KEY AUTOINCREMENT, nume TEXT NOT NULL, email TEXT NOT NULL);";
            String sqlBilet = "CREATE TABLE IF NOT EXISTS Bilet (id INTEGER PRIMARY KEY AUTOINCREMENT, client_id INTEGER NOT NULL, program_id INTEGER NOT NULL, rand INTEGER NOT NULL, loc INTEGER NOT NULL, FOREIGN KEY(client_id) REFERENCES Client(id), FOREIGN KEY(program_id) REFERENCES ProgramFilm(id));";

            stmt.execute(sqlFilm); stmt.execute(sqlSala); stmt.execute(sqlProgram); stmt.execute(sqlClient); stmt.execute(sqlBilet);
            System.out.println("Tabele create.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

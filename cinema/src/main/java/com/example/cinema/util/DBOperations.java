package com.example.cinema.util;

import com.example.cinema.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DBOperations {

    public static int insertFilm(Film film) {
        String sql = "INSERT INTO Film(titlu, gen, durata, popularitate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, film.getTitlu());
            ps.setString(2, film.getGen());
            ps.setInt(3, film.getDurata());
            ps.setInt(4, film.getPopularitate());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static List<Film> getAllFilme() {
        List<Film> list = new ArrayList<>();
        String sql = "SELECT id,titlu,gen,durata,popularitate FROM Film";
        try (Connection conn = DBManager.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Film(rs.getInt("id"), rs.getString("titlu"), rs.getString("gen"),
                        rs.getInt("durata"), rs.getInt("popularitate")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static List<Film> getFilmeFiltrate(String gen, String sort) {
        List<Film> filme = getAllFilme();
        if (gen != null && !gen.isBlank()) {
            String genLower = gen.toLowerCase(Locale.ROOT);
            filme = filme.stream()
                    .filter(f -> f.getGen().toLowerCase(Locale.ROOT).contains(genLower))
                    .collect(Collectors.toList());
        }

        if (sort != null) {
            switch (sort) {
                case "durata" -> filme.sort(Comparator.comparingInt(Film::getDurata));
                case "popularitate" -> filme.sort(Comparator.comparingInt(Film::getPopularitate).reversed());
                default -> {
                }
            }
        }
        return filme;
    }

    public static int insertSala(Sala sala) {
        String sql = "INSERT INTO Sala(nume, randuri, locuriPeRand) VALUES (?, ?, ?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, sala.getNume());
            ps.setInt(2, sala.getRanduri());
            ps.setInt(3, sala.getLocuriPeRand());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) return rs.getInt(1); }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static List<Sala> getAllSali() {
        List<Sala> list = new ArrayList<>();
        String sql = "SELECT id,nume,randuri,locuriPeRand FROM Sala";
        try (Connection conn = DBManager.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(new Sala(rs.getInt("id"), rs.getString("nume"),
                    rs.getInt("randuri"), rs.getInt("locuriPeRand")));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static int insertProgramFilm(ProgramFilm pf, int filmId, int salaId) {
        String sql = "INSERT INTO ProgramFilm(film_id, sala_id, data, ora) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, filmId);
            ps.setInt(2, salaId);
            ps.setString(3, pf.getData().toString());
            ps.setString(4, pf.getOra().toString());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) return rs.getInt(1); }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static List<ProgramFilm> getProgramFilme() {
        List<ProgramFilm> program = new ArrayList<>();
        String sql = "SELECT pf.id, pf.data, pf.ora, " +
                "f.id AS filmId, f.titlu, f.gen, f.durata, f.popularitate, " +
                "s.id AS salaId, s.nume, s.randuri, s.locuriPeRand " +
                "FROM ProgramFilm pf " +
                "JOIN Film f ON pf.film_id = f.id " +
                "JOIN Sala s ON pf.sala_id = s.id " +
                "ORDER BY pf.data, pf.ora";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Film film = new Film(rs.getInt("filmId"), rs.getString("titlu"), rs.getString("gen"),
                        rs.getInt("durata"), rs.getInt("popularitate"));
                Sala sala = new Sala(rs.getInt("salaId"), rs.getString("nume"),
                        rs.getInt("randuri"), rs.getInt("locuriPeRand"));
                ProgramFilm programFilm = new ProgramFilm(
                        rs.getInt("id"),
                        film,
                        sala,
                        LocalDate.parse(rs.getString("data")),
                        LocalTime.parse(rs.getString("ora")));
                program.add(programFilm);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return program;
    }

    public static int insertClient(Client c) {
        String sql = "INSERT INTO Client(nume, email) VALUES (?, ?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNume()); ps.setString(2, c.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) return rs.getInt(1); }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static int insertBilet(int clientId, int programId, int rand, int loc) {
        String sql = "INSERT INTO Bilet(client_id, program_id, rand, loc) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, clientId); ps.setInt(2, programId); ps.setInt(3, rand); ps.setInt(4, loc);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) return rs.getInt(1); }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }
}

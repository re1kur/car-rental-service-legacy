package dao;

import entity.UserEntity;
import db.ConnectionManager;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<UserEntity> {
    @Getter
    private static final UserDao instance = new UserDao();

    private UserDao() {
    }

    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;

    private static final String INSERT_SQL = """
            INSERT INTO users (email, username, password)
            VALUES (?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
            SET
                email = ?,
                username = ?,
                password = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, username, password, email, role
            FROM users
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = FIND_ALL_SQL + """
            WHERE email = ? and password = ?
            """;

    public boolean delete(int id) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void save(UserEntity user) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(UserEntity user) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntity> findAll() {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            List<UserEntity> users = new ArrayList<>();
            while (rs.next()) {
                users.add(
                        UserEntity.builder()
                                .id(rs.getInt("id"))
                                .email(rs.getString("email"))
                                .username(rs.getString("username"))
                                .password(rs.getString("password"))
                                .role(rs.getString("role"))
                                .build()
                );
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserEntity> findById(int id) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Optional.of(UserEntity.builder()
                        .id(rs.getInt("id"))
                        .email(rs.getString("email"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .role(rs.getString("role"))
                        .build());
            else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserEntity user = UserEntity.builder()
                        .id(rs.getInt("id"))
                        .email(rs.getString("email"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .role(rs.getString("role"))
                        .build();
                return Optional.of(user);
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

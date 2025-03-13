package dao;

import entity.CompanyEntity;
import db.handle.ConnectionManager;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class CompanyDao implements Dao<CompanyEntity> {
    @Getter
    private static final CompanyDao instance = new CompanyDao();

    private static final String FIND_ALL_SQL = """
            SELECT id, name, img_key
            FROM package.company
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String INSERT_SQL = """
            INSERT INTO package.company (name, img_key)
            VALUES (?, ?)
            """;

    private CompanyDao() {
    }

    @Override
    @SneakyThrows
    public Optional<CompanyEntity> findById(int id) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(CompanyEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .imgKey(rs.getString("img_key"))
                        .build());
            }
            return Optional.empty();
        }
    }

    @Override
    @SneakyThrows
    public List<CompanyEntity> findAll() {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ALL_SQL)) {
            List<CompanyEntity> companies = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                companies.add(CompanyEntity.builder()
                        .id(rs.getInt("id"))
                        .imgKey(rs.getString("img_key"))
                        .name(rs.getString("name"))
                        .build()
                );
            }
            return companies;
        }
    }

    @Override
    @SneakyThrows
    public void save(CompanyEntity o) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, o.getName());
            ps.setString(2, o.getImgKey());

            ps.executeUpdate();
        }
    }

    @Override
    public int update(CompanyEntity o) {
        return 0;
    }
}

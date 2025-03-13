package dao;

import entity.CarEntity;
import db.handle.ConnectionManager;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao implements Dao<CarEntity> {
    @Getter
    private static final CarDao instance = new CarDao();

    private final CompanyDao companyDao = CompanyDao.getInstance();

    private CarDao() {
    }

    private static final String FIND_ALL_SQL = """
            SELECT id, name, company_id, year_release
            FROM package.car
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String INSERT_SQL = """
                INSERT INTO package.car (name, company_id, year_release)
               VALUES (?, ?, ?)
            """;

    public Optional<CarEntity> findById(int id) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(CarEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .company(companyDao.findById(rs.getInt("company_id")).orElse(null))
                        .yearRelease(rs.getDate("year_release").toLocalDate())
                        .build());
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CarEntity> findAll() {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ALL_SQL)) {

            List<CarEntity> cars = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(CarEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .company(companyDao.findById(rs.getInt("company_id")).orElse(null))
                        .yearRelease(rs.getDate("year_release").toLocalDate())
                        .build()
                );
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(CarEntity o) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, o.getName());
            ps.setInt(2, o.getCompany().getId());
            ps.setDate(3, Date.valueOf(o.getYearRelease()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer saveAndReturnId(CarEntity o) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, o.getName());
            ps.setInt(2, o.getCompany().getId());
            ps.setDate(3, Date.valueOf(o.getYearRelease()));

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt("id");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(CarEntity o) {
        return 0;
    }
}

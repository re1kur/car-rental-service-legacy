package dao;

import entity.RentalEntity;
import db.handle.ConnectionManager;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalDao implements Dao<RentalEntity> {
    @Getter
    private static final RentalDao instance = new RentalDao();

    private final CarDao carDao = CarDao.getInstance();

    private RentalDao() {

    }

    private static final String FIND_ALL_SQL = """
            SELECT id, car_id, price, owner_id, description, img_key
            FROM package.rental
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            where id = ?
            """;

    private static final String FIND_BY_OWNER_ID_SQL = FIND_ALL_SQL + """
            WHERE owner_id = ?
            """;

    private static final String FIND_BY_COMPANY_ID_SQL = """
            SELECT r.id, r.car_id, r.price, r.owner_id, r.description, r.img_key
            FROM package.rental AS r
            JOIN package.car AS c
            ON c.id = r.car_id
            WHERE c.company_id = ?
            """;

    private static final String INSERT_SQL = """
            INSERT INTO package.rental (car_id, price, owner_id, description, img_key)
            VALUES (?, ?, ?, ?, ?)
            """;

    public List<RentalEntity> findByOwnerId(int ownerId) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_OWNER_ID_SQL)) {
            ps.setInt(1, ownerId);

            List<RentalEntity> rentals = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rentals.add(
                        RentalEntity.builder()
                                .id(rs.getInt("id"))
                                .car(carDao.findById(rs.getInt("car_id")).orElse(null))
                                .imgKey(rs.getString("img_key"))
                                .price(rs.getInt("price"))
                                .ownerId(rs.getInt("owner_id"))
                                .description(rs.getString("description"))
                                .build()
                );
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RentalEntity> findByCompanyId(int companyId) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_COMPANY_ID_SQL)) {
            ps.setInt(1, companyId);

            List<RentalEntity> rentals = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rentals.add(
                        RentalEntity.builder()
                                .id(rs.getInt("id"))
                                .car(carDao.findById(rs.getInt("car_id")).orElse(null))
                                .imgKey(rs.getString("img_key"))
                                .price(rs.getInt("price"))
                                .ownerId(rs.getInt("owner_id"))
                                .description(rs.getString("description"))
                                .build()
                );
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public Optional<RentalEntity> findById(int id) {
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        RentalEntity.builder()
                                .id(rs.getInt("id"))
                                .car(carDao.findById(rs.getInt("car_id")).orElse(null))
                                .imgKey(rs.getString("img_key"))
                                .price(rs.getInt("price"))
                                .ownerId(rs.getInt("owner_id"))
                                .description(rs.getString("description"))
                                .build()
                );
            }
            return Optional.empty();
        }
    }

    public List<RentalEntity> findAll() {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ALL_SQL)) {

            List<RentalEntity> rentals = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rentals.add(
                        RentalEntity.builder()
                                .id(rs.getInt("id"))
                                .car(carDao.findById(rs.getInt("car_id")).orElse(null))
                                .imgKey(rs.getString("img_key"))
                                .price(rs.getInt("price"))
                                .ownerId(rs.getInt("owner_id"))
                                .description(rs.getString("description"))
                                .build()
                );
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(RentalEntity o) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, carDao.saveAndReturnId(o.getCar()));
            ps.setInt(2, o.getPrice());
            ps.setInt(3, o.getOwnerId());
            ps.setString(4, o.getDescription());
            ps.setString(5, o.getImgKey());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(RentalEntity o) {
        return 0;
    }
}

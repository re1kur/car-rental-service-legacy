package dao;

import entity.PersonalInfoEntity;
import db.ConnectionManager;
import lombok.Getter;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class PersonalInfoDao implements Dao<PersonalInfoEntity> {
    @Getter
    private static final PersonalInfoDao instance = new PersonalInfoDao();

    private final static String FIND_BY_ID_SQL = """
            SELECT id, name, pass_no, birthday
            FROM personal_info
            WHERE id = ?
            """;
    private final static String INSERT_OR_UPDATE_SQL = """
            INSERT INTO personal_info
            (id, name, pass_no, birthday)
            VALUES (?, ?, ?, ?)
            ON CONFLICT(id)
            DO UPDATE
            SET
            name=EXCLUDED.name,
            pass_no=EXCLUDED.pass_no,
            birthday=EXCLUDED.birthday
            """;

    private PersonalInfoDao() {

    }

    @Override
    public Optional<PersonalInfoEntity> findById(int id) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        PersonalInfoEntity.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .passNo(rs.getString("pass_no"))
                                .birthday(rs.getDate("birthday") == null ?
                                        null : rs.getDate("birthday").toLocalDate())
                                .build()
                );
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PersonalInfoEntity> findAll() {
        return List.of();
    }

    @Override
    public void save(PersonalInfoEntity o) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_OR_UPDATE_SQL)) {
            ps.setInt(1, o.getId());
            ps.setString(2, o.getName());
            ps.setString(3, o.getPassNo());
            ps.setDate(4, Date.valueOf(o.getBirthday()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int update(PersonalInfoEntity o) {
        return 0;
    }
}

import dao.RentalDao;
import db.ConnectionManager;
import org.junit.Before;
import org.junit.Test;
import service.RentalService;

import java.sql.*;

import static org.junit.Assert.*;

public class DatabaseTest  {

    @Before
    public void setUp() {
        ConnectionManager.initConnectionPool();
    }

    private void initTestValues() throws SQLException {
        Connection con = ConnectionManager.getConnection();

        String email = "email@mail.ru";
        String username = "johndoe";
        String password = "password123";
        String role = "USER";

        String query = """
                INSERT INTO users (email, username, password, role)
                VALUES (?, ?, ?, ?);
                """;

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, username);
        ps.setString(3, password);
        ps.setString(4, role);

        ps.executeUpdate();

        System.out.println("TEST INIT VALUES");
    }


    @Test
    public void testConnection() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String name1 = con.getMetaData().getDatabaseProductName();
        String name2 = ConnectionManager.getConnection().getMetaData().getDatabaseProductName();
        assertEquals(name1, name2);
    }

    @Test
    public void testCheckIsValid() throws SQLException {
        deleteTestValues();
        initTestValues();

        String username = "johndoe";
        String password = "password123";
        Connection con = ConnectionManager.getConnection();
        String query = """
                SELECT id FROM users
                WHERE username = ? AND password = ?;
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next());
        System.out.println(rs.getString("id"));

        deleteTestValues();
    }

    @Test
    public void testDao() {
        RentalDao.getInstance().findByOwnerId(2).forEach(System.out::println);

        RentalService.getInstance().readAll().forEach(System.out::println);
    }

    private void deleteTestValues() throws SQLException {
        Connection con = ConnectionManager.getConnection();

        String email = "email@mail.ru";
        String username = "johndoe";
        String password = "password123";

        String query = """
                DELETE FROM users
                WHERE email = ? AND username = ? AND password = ?;
                """;

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, username);
        ps.setString(3, password);

        ps.executeUpdate();

        System.out.println("TEST DELETE INIT VALUES.");
    }
}

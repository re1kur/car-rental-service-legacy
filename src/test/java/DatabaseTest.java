import dao.RentalDao;
import db.handle.ConnectionManager;
import org.junit.Test;
import service.RentalService;
import util.PropertiesUtil;

import java.sql.*;

import static org.junit.Assert.*;

public class DatabaseTest  {

    @Test
    public void testConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                PropertiesUtil.getProperty("db.url"),
                PropertiesUtil.getProperty("db.username"),
                PropertiesUtil.getProperty("db.password"));
        String name1 = connection.getMetaData().getDatabaseProductName();
        String name2 = ConnectionManager.getConnection().getMetaData().getDatabaseProductName();
        assertEquals(name1, name2);
    }

    @Test
    public void testCheckIsValid() throws SQLException {
        String username = "johndoe";
        String password = "password123";
        Connection con = ConnectionManager.getConnection();
        String query = """
                SELECT id FROM package.user
                WHERE username = ? AND password = ?;
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next());
        System.out.println(rs.getString("id"));
    }

    @Test
    public void testDao() {
        RentalDao.getInstance().findByOwnerId(2).forEach(System.out::println);

        RentalService.getInstance().readAll().forEach(System.out::println);
    }
}

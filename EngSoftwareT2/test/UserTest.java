import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.engine.User;
import org.junit.Test;


public class UserTest {

	/* Testar por:
	 * - nulls (nomes e ids) -> delete user.
	 * - valores diferentes (nomes e ids) -> update user
	 * - excepcoes -> SQLException
	 * - valores iguais ao esperado -> inserts / gets
	 */
	
	@Test
	public void testNameInObj() {
		Utilizador u = new Utilizador(1,"Nuno");
		assertEquals(u.getName(),"Nuno");
	}
	
	@Test
	public void testIDInObj() {
		Utilizador u = new Utilizador(1,"Nuno");
		assertEquals(u.getID(),1);
	}
	
	@Test
	public void testNamePersist() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addToDB(dba);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select nome from utilizadores;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("nome");
		}
		assertEquals(x,"Nuno");
	}
	
	@Test
	public void testIDPersist() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addToDB(dba);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id from utilizadores;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("id");
		}
		assertEquals(x,"1");
	}

}

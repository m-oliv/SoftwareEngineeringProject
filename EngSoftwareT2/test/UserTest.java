import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
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
		Utilizador u = new Utilizador("Nuno",1);
		assertEquals(u.getName(),"Nuno");
	}
	
	@Test
	public void testIDInObj() {
		Utilizador u = new Utilizador("Nuno",1);
		assertEquals(u.getID(),1);
	}
	
	@Test
	public void testNamePersist(){
		DBAccess dbaccess = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dbaccess.initialize();
		Utilizador u = new Utilizador("Nuno",1);
		u.persist(dba);
		Connection conn = dbaccess.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select nome from utilizador;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("nome");
		}
		assertEquals(x,"Nuno");
	}
	
	@Test
	public void testIDPersist(){
		DBAccess dbaccess = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dbaccess.initialize();
		Utilizador u = new Utilizador("Nuno",1);
		u.persist(dba);
		Connection conn = dbaccess.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id from utilizador;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("id");
		}
		assertEquals(x,1);
	}

}

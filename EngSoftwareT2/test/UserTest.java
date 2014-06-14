import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public void testAddUserName() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
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
	public void testAddUserID() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
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
	
	@Test
	public void testUpdateName() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		u.updateUser(dba,0,"Marlene",1);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select nome from utilizadores;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("nome");
		}
		assertEquals(x,"Marlene");
	}
	
//	@Test
//	public void testUpdateID() throws ClassNotFoundException, SQLException{
//		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
//		dba.initialize();
//		Utilizador u = new Utilizador(1,"Nuno");
//		u.addUser(dba);
//		u.updateUser(dba,1,"Nuno",2);
//		Connection conn = dba.getConnection();
//		Statement stmt = conn.createStatement();
//		String sqlQuery = "select id from utilizadores;";
//		ResultSet rs = stmt.executeQuery(sqlQuery);
//		String x = "";
//		while(rs.next()){
//			 x = rs.getString("id");
//		}
//		assertEquals(x,"2");
//	}
	
	@Test 
	public void testDelete() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		u.deleteUser(dba,1);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select count(nome) from utilizadores where id = 1;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		int x = 0;
		while(rs.next()){
			 x = rs.getInt(1);
		}
		assertTrue(x==0);
	}
	
	@Test (expected = NullPointerException.class)
	public void testNameNullObj(){
		Utilizador u = new Utilizador(1,null);
	}
	
	@Test (expected = SQLException.class)
	public void testInsertDuplicate() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Utilizador u2 = new Utilizador(1,"Joao");
		u2.addUser(dba);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddUserNullName() throws SQLException, ClassNotFoundException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,null);
		u.addUser(dba);
	}
	
	@Test (expected = NullPointerException.class)
	public void testUpdateUserNullName() throws ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		u.updateUser(dba,0,null,2);
	}
	
	@Test
	public void testListUser() throws ClassNotFoundException, SQLException{

		//rever
		
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id,nome from utilizadores where id = 1";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		int x = 0;
		String y = "";
		while(rs.next()){
			 x = rs.getInt("id");
			 y = rs.getString("nome");
		}
	
		Utilizador u_new = new Utilizador(x,y);
		assertEquals(u_new.toString(),"ID: "+x+"; Nome: "+y+";");
	}
	
	@Test
	public void testListSeveralUsers() throws ClassNotFoundException, SQLException{
		int id_u = 0;
		String nome_u = "";
		
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		
		Utilizador u1 = new Utilizador(1,"Nuno");
		Utilizador u2 = new Utilizador(2,"Ana");
		Utilizador u3 = new Utilizador(3,"Maria");
		Utilizador u4 = new Utilizador(4,"Joao");
		
		ArrayList<Utilizador> a_users = new ArrayList<Utilizador>();
		ArrayList<Utilizador> a_users_db = new ArrayList<Utilizador>();
		a_users.add(u1);
		a_users.add(u2);
		a_users.add(u3);
		a_users.add(u4);
		
		u1.addUser(dba);
		u2.addUser(dba);
		u3.addUser(dba);
		u4.addUser(dba);
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id,nome from utilizadores";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		
		while(rs.next()){
			 id_u = rs.getInt("id");
			 nome_u = rs.getString("nome");
			 Utilizador u = new Utilizador(id_u,nome_u);
			 a_users_db.add(u);
			 id_u = 0;
			 nome_u = "";
		}
		
		assertEquals(a_users.toString(),a_users_db.toString());
		
	}
}

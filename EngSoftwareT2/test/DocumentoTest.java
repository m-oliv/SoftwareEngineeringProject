import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class DocumentoTest {

	@Test (expected=Exception.class)
	public void testFilepathConstructor() throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		new Documento(1,"C:/NotAVirus.exe", new Timestamp(datelong), 1);
	}

	@Test (expected=Exception.class)
	public void testFilepathConstructorNullFilepath() throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		new Documento(1,null, new Timestamp(datelong), 1);
	}

	@Test (expected=FileNotFoundException.class)
	public void testFilepathConstructorWrongDocumentName() throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		new Documento(1,"C:/NotAVirus.txt", new Timestamp(datelong), 1);
	}
	
	@Test
	public void testTitleInObj() throws Exception {
		// verificar se o titulo atribuido esta no objeto
		
		// obter uma data para criacao
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		
		// criar um documento para o teste
		Documento u = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		
		// verificar se o titulo e o pretendido
		assertEquals(u.getTitle(),"ola");
	}
	
	@Test
	public void testIDInObj() throws Exception {
		// verificar se o ID atribuido esta no objeto
		
		// obter uma data para criacao
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		
		// criar um documento para o teste
		Documento u = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		
		// verificar se o ID obtido e o esperado
		assertEquals(u.getID(),1);
	}
	
	@Test
	public void testBodyInObj() throws Exception {
		// verificar se o corpo do documento atribuido esta no objeto
		
		// obter uma data para criacao
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		
		// criar um documento para o teste
		Documento u = new Documento(1,"ola","adeus",new Timestamp(datelong), 1);
		
		// verificar se o corpo do documento obtido e o pretendido
		assertEquals(u.getBody(),"adeus");
	}
	
	@Test
	public void testID_userInObj() throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento u = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		assertEquals(u.getUser(),1);
	}
	
	@Test
	public void testD_criacaoInObj() throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento u = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		assertEquals(u.getD_criacao(),new Timestamp(datelong));
	}
	
	@Test
	public void testAddDocTitle() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		//utilizador para pasar o id
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlQuery = "select title from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("title");
		}
		assertEquals(x,"ola");
	}
	
	
	@Test
	public void testAddDocBody() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		//utilizador para pasar o id
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlQuery = "select body from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("body");
		}
		assertEquals(x,"adeus");
	}
	@Test
	public void testAddDocID() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		int x = 0;
		while(rs.next()){
			 x = rs.getInt("id");
		}
		assertEquals(x,1);
	}
	
	@Test
	public void testAddDocTimestamp() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		//utilizador para pasar o id
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlQuery = "select d_criacao from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		Timestamp x= new Timestamp(0);
		while(rs.next()){
			 x = rs.getTimestamp("d_criacao");
		}
		assertEquals(x, new Timestamp(datelong));
	}
	
	@Test
	public void testAddDocID_user() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id_user from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		int x = 0;
		while(rs.next()){
			 x = rs.getInt("id_user");
		}
		assertEquals(x,1);
	}
	
	
	@Test
	public void testUpdateTitle() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		d.updateDoc(dba, 0, "OLA", 1, 1,new Timestamp(datelong));
		
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select title from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("title");
		}
		assertEquals(x,"OLA");
	}
	
	@Test
	public void testUpdateBody() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		d.updateDoc(dba, 2, "ADEUS", 1, 1,new Timestamp(datelong));
		
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select body from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		String x = "";
		while(rs.next()){
			 x = rs.getString("body");
		}
		assertEquals(x,"ADEUS");
	}
	
	@Test
	public void testUpdateID_user() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Utilizador u1 = new Utilizador(2,"Nuna");
		u1.addUser(dba);

		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		
		
		//mudar o user
		d.updateDoc(dba, 3, null, 2, 1,new Timestamp(datelong));
		
		
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select id_user from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		int x = -1;
		while(rs.next()){
			 x = rs.getInt("id_user");
		}
		assertEquals(x,2);
	}
	
	@Test
	public void testUpdateD_alteracao() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
	

		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		
		
	//mudar o user
		
		Timestamp test=new Timestamp(datelong);
		d.updateDoc(dba, 1, "OLA", 1, 1,test);
		
		
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select d_alteracao from documentos;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		Timestamp temp=new Timestamp(0);
		while(rs.next()){
			 temp = rs.getTimestamp("d_alteracao");
		}
		assertEquals(temp,test);
	}
	
	@Test 
	public void testDeleteDoc() throws Exception, ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);

		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		d.deleteDoc(dba, 1);
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "select count(title) from documentos where id = 1;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		int x = 0;
		while(rs.next()){
			 x = rs.getInt(1);
		}
		assertTrue(x==0);
	}
	
	@Test (expected = NullPointerException.class)
	public void testTitleNullObj()throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento u = new Documento(1,null,"adeus", new Timestamp(datelong), 1);
	}
	@Test (expected = NullPointerException.class)
	public void testBodyNullObj()throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento u = new Documento(1,"ola",null, new Timestamp(datelong), 1);
	}
	
	@Test (expected = NullPointerException.class)
	public void testD_criacaoNullObj()throws Exception {
		Documento u = new Documento(1,"ola","adeus", null, 1);
	
	}
	
	@Test (expected = Exception.class)
	public void testNameNullObj()throws Exception {
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento u = new Documento(1,"ola","adeus", new Timestamp(datelong), -1);
	
	}
	
	@Test (expected = SQLException.class)
	public void testInsertDuplicate() throws Exception,ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d1 = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d1.addDoc(dba);
		Documento d2 = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d2.addDoc(dba);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddDocNullTitle() throws Exception, SQLException, ClassNotFoundException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d1 = new Documento(1,null,"adeus", new Timestamp(datelong), 1);
		d1.addDoc(dba);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddDocNullBody() throws Exception, SQLException, ClassNotFoundException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d1 = new Documento(1,"ola",null, new Timestamp(datelong), 1);
		d1.addDoc(dba);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddDocNullD_criacao() throws Exception, SQLException, ClassNotFoundException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Documento d1 = new Documento(1,"ola","adeus", null, 1);
		d1.addDoc(dba);
	}
	
	@Test (expected = Exception.class)
	public void testAddDocNullId_user() throws Exception, SQLException, ClassNotFoundException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d1 = new Documento(1,"ola","adeus", new Timestamp(datelong), -1);
		d1.addDoc(dba);
	}

	
	@Test (expected = NullPointerException.class)
	public void testUpdateDocNullTitle() throws Exception, ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		d.updateDoc(dba, 0, null, 1, 1,new Timestamp(datelong));
	}
	
	@Test (expected = NullPointerException.class)
	public void testUpdateDocNullBody() throws Exception, ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		d.updateDoc(dba, 2, null, 1, 1,new Timestamp(datelong));
	}
	@Test (expected = Exception.class)
	public void testUpdateDocNullD_criacao() throws Exception, ClassNotFoundException, SQLException{
		DBAccess dba = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		dba.initialize();
		Utilizador u = new Utilizador(1,"Nuno");
		u.addUser(dba);
		Date dat= new Date(System.currentTimeMillis());
		long datelong= dat.getTime();
		Documento d = new Documento(1,"ola","adeus", new Timestamp(datelong), 1);
		d.addDoc(dba);
		d.updateDoc(dba, 3, null, -1, 1,new Timestamp(datelong));
	}
}

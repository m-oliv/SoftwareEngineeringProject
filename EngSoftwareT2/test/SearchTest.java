import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;


public class SearchTest {
	@Test
	public void testTitleSearch() throws ClassNotFoundException, SQLException {
		DBAccess dbaccess=populate();
		DocSearch d = new DocSearch(dbaccess);
		ArrayList<Integer> results = d.searchTitles("tiitulo");
		assertEquals(results.size(), 2);
		results = d.searchTitles("Lorem");
		assertEquals(results.size(), 1);
	}
	

	@Test
	public void testBodySearch() throws ClassNotFoundException, SQLException {
		DBAccess dbaccess=populate();
		DocSearch d = new DocSearch(dbaccess);
		ArrayList<Integer> results = d.searchBodies("foleiruo");
		assertEquals(results.size(), 0);
		results = d.searchBodies("Lorem");
		assertEquals(results.size(), 1);
	}
	
	@Test
	public void testGenericSearch() throws ClassNotFoundException, SQLException {
		DBAccess dbaccess=populate();
		DocSearch d = new DocSearch(dbaccess);
		ArrayList<Integer> results = d.searchGeneric("foleiro");
		assertEquals(results.size(), 2);
		results = d.searchGeneric("Lorem");
		assertEquals(results.size(), 1);
	}
	
	@Test
	public void testSearchById() throws ClassNotFoundException, SQLException {
		DBAccess dbaccess=populate();
		DocSearch d = new DocSearch(dbaccess);
		ArrayList<String> results = d.searchGeneric(1);
		assertEquals(results.size(), 1);
		results = d.searchGeneric(2);
		assertEquals(results.size(), 1);
	}

	@Test 
	public void testSearchByDate() throws ClassNotFoundException, SQLException {
		
		DBAccess dbaccess=populate();
		DocSearch d = new DocSearch(dbaccess);
		long testTime=System.currentTimeMillis();
		ArrayList<String> results = d.searchByDate(new Timestamp(testTime));
		assertEquals(results.size(), 0);
		
		try {
			Documento d1 = new Documento(4, "titulo bonito", "body reles", new Timestamp(testTime+50), 1);
			d1.addDoc(dbaccess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d.updateDB();
		results = d.searchByDate(new Timestamp(testTime));
		assertEquals(results.size(), 1);
	}
	
	private DBAccess populate() throws ClassNotFoundException, SQLException{
		
		/*Cria uma base de dados com dados.
		 * User: 1, Nuno
		 * Documentos: 1, 2, 3; o 3 é lido de C:/documento.txt.
		 * Retorna a base de dados DBAccess criada.*/
		
		DBAccess dbaccess = new DBAccess("org.h2.Driver", "jdbc:h2:mem:", "root", "password");
		DBAccess dbaccessMOCK=Mockito.mock(DBAccess.class);
			
		dbaccess.initialize();
		dbaccessMOCK.initialize();
		
		Utilizador u1=new Utilizador(1,"Nuno");
		u1.addUser(dbaccess);
		u1.addUser(dbaccessMOCK);
		
		try {
			Documento d1 = new Documento(1, "tiitulo bonito", "body foleiro", new Timestamp(System.currentTimeMillis()), 1);
			d1.addDoc(dbaccess);
			//d1.addDoc(dbaccessMOCK);
			Documento d2 = new Documento(2, "tiitulo bonito", "body foleiro", new Timestamp(System.currentTimeMillis()), 1);
			d2.addDoc(dbaccess);
			//d2.addDoc(dbaccessMOCK);
			Documento d3 = new Documento(3, "C:/documento.txt", new Timestamp(System.currentTimeMillis()), 1);
			d3.addDoc(dbaccess);
			//d3.addDoc(dbaccessMOCK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbaccess;
		
	}
}

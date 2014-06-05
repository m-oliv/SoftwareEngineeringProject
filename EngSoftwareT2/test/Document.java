
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.junit.Test;

public class Document 
{
	/*
	 * This class defines a Document object.
	 * */
	private String title;
	private String body;
//	private Timestamp creation;
//	private String user; //Temporary; user is meant to be a data type
	private String readValue=null;
	private File documentFile;
	private String filepath="c:/documento.txt";
	
	public Document()
	{
		
	}
	@Test
	public void verifyFileFormat()
	{	
		documentFile = new File(filepath);
		BufferedReader reader = null;
		try 
		{
			reader = new BufferedReader (new FileReader(documentFile) );
			// Linha 1: "title:"
			readValue=reader.readLine();
			assertEquals(readValue,"title:");
			
			// Linha 2: "sometitle"
			readValue=reader.readLine();
			assertNotNull(readValue);
			
			// Linha 3: ""
			readValue=reader.readLine();
			assertEquals(readValue,"");
			
			// Linha 4: "body:"
			readValue=reader.readLine();
			assertEquals(readValue,"body:");
			
			// Linhas 5+: "anything goes"
			readValue=reader.readLine();
			assertNotNull(readValue);

		}
		catch (Exception e) {
			fail();
		}
		finally 
		{
		    try 
		    {
		        if (reader != null) 
		        {
		            reader.close();
		        }
		    } 
		    catch (Exception e) 
		    {
				fail();
		    }
		}
	}
	@Test
	public void verifyFileExtensionIsTXT()
	{	
		assertTrue(filepath.endsWith(".txt"));
	}
	public void setFilePath(String newFilepath)
	{
		this.filepath=newFilepath;
	}
	public void initialize()
	{	
		
		documentFile = new File(filepath);
		BufferedReader reader = null;
		try 
		{
			reader = new BufferedReader (new FileReader(documentFile) );
			// Linha 1: "title:"
			readValue=reader.readLine();
			// Linha 2: "sometitle"
			readValue=reader.readLine();
			title=readValue;
			readValue=reader.readLine();
			// Linha 4: "body:"
			readValue=reader.readLine();
			// Linhas 5+: "anything goes"
			readValue=reader.readLine();
			body="";
			while(readValue!=null)
			{
				body+=readValue+"\n";
				readValue=reader.readLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
		    try 
		    {
		        if (reader != null) 
		        {
		            reader.close();
		        }
		    } 
		    catch (Exception e) 
		    {
				e.printStackTrace();
		    }
		}
	}
	public void printData()
	{
		System.out.println(title);
		System.out.println(body);
		System.out.println(filepath);
		System.out.println(body.indexOf("justo vitae"));
	}
	
}



import org.junit.Test;
/*import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
*/
public class testDocument {
	@Test
	public void privateMethod() throws Exception
	{		
		Document d = new Document();
		d.setFilePath("C:/documento.txt");
		d.initialize();
		d.printData();
	}
}

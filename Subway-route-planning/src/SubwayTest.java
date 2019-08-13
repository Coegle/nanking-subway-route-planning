
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SubwayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void PAT1() throws IOException, ParseException {
		String tempString = null; 
		String[] args = null;
		
		tempString = "-map subway.txt";
		args = tempString.split(" ");
		Subway.main(args);

		tempString = "-map subway.txt -o s1.txt -o s2.txt -b 新街口 卡子门";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway.txt -o routine.txt -a 1号线 -a 2号线";
		args = tempString.split(" ");
		Subway.main(args);
	}
	
	
	@Test(expected = MissingArgumentException.class)
	public void PAT2_1() throws IOException, ParseException {
		String tempString = "-map";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}
	
	@Test(expected = MissingArgumentException.class)
	public void PAT2_2() throws IOException, ParseException {
		String tempString = "-map subway.txt -o stations.txt -b 新街口";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}	
	
	@Test
	public void PAT2_3() throws IOException, ParseException {
		String tempString = "-map subway.txt -o routine.txt -a 1号线 2号线";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}
	
	@Test(expected = MissingOptionException.class)
	public void PAT3() throws IOException, ParseException {
		String tempString = "-o routine.txt -a 1号线";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}
	
	@Test(expected = UnrecognizedOptionException.class)
	public void PAT4() throws IOException, ParseException {
		String tempString = "-map subway.txt -o routine.txt -c 1号线";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}
	
	@Test(expected = RuntimeException.class)
	public void PAT5_1() throws IOException, ParseException {
		String tempString = "-map subway.txt -o routine.txt -a 1号线 -b 新街口 卡子门";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}
	
	@Test(expected = RuntimeException.class)
	public void PAT5_2() throws IOException, ParseException {
		String tempString = "-map subway.txt -o routine.txt";
		String[] args = tempString.split(" ");
		Subway.main(args);
	}
	
	@Test
	public void RPT() throws IOException, ParseException {
		String tempString = null; 
		String[] args = null;
		
		tempString = "-map subway.txt -o routine.txt -b 新街口 孝陵卫";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway.txt -o routine.txt -b 鼓楼 孝陵卫";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway.txt -o routine.txt -b 孝陵卫 孝陵卫";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway.txt -o routine.txt -b 孝陵卫 孝陵卫";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway1.txt -o routine.txt -b 鼓楼 南理工";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway.txt -o routine.txt -b 王府井 下马坊";
		args = tempString.split(" ");
		thrown.expect(RuntimeException.class);
	    thrown.expectMessage("The station you inquired is NOT found!");
		Subway.main(args);
	}
	
	@Test
	public void RIT() throws IOException, ParseException {
		String tempString = null; 
		String[] args = null;
		
		tempString = "-map subway.txt -o routine.txt -a 1号线";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map subway.txt -o routine.txt -a 9号线";
		args = tempString.split(" ");
		thrown.expect(RuntimeException.class);
	    thrown.expectMessage("The line you inquired is NOT found!");
		Subway.main(args);
	}
	
	@Test
	public void IOT() throws IOException, ParseException {
		String tempString = null; 
		String[] args = null;
		
		tempString = "-map subway.txt -o r1.txt -a 1号线";
		args = tempString.split(" ");
		Subway.main(args);
		
		tempString = "-map s.txt -o routine.txt -a 1号线";
		args = tempString.split(" ");
		thrown.expect(FileNotFoundException.class);
		Subway.main(args);
	}
}

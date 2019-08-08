
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName:Test.java
 * 调试测试使用
 * 正式程序不会从此进入
 * @author yang1
 *
 */

public class Test {
	
	public static void main(String[] args) throws IOException {
		
		FileIO.READ_PATH = "subway.txt";
		FileIO.WRITE_PATH = "routine.txt";

		List<Station> map = new ArrayList<>();
		FileIO.readSubway(map);
		System.out.println(map.size());
		Dijkstra sw = new Dijkstra();
		sw.cal(map.get(map.indexOf(new Station("南京站",""))), map.get(map.indexOf(new Station("汉中门",""))) , map.size());
	}
	
}

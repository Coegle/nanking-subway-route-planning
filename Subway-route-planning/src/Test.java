
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName:Test.java
 * ���Բ���ʹ��
 * ��ʽ���򲻻�Ӵ˽���
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
		sw.cal(map.get(map.indexOf(new Station("�Ͼ�վ",""))), map.get(map.indexOf(new Station("������",""))) , map.size());
	}
	
}

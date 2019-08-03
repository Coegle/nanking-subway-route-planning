import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) throws IOException {
		//line test=new line();
		//test.lineNo="1号线";
		//test.station[]= {"迈皋桥", "红山动物园", " 南京站", "新模范马路", "玄武门", "鼓楼", "珠江路", "新街口", "张府园", "三山街", "中华门", "安德门", "天隆寺", "软件大道", "花神庙", "南京南站", "双龙大道", "河定桥", "胜太路", "百家湖", "小龙湾", "竹山路", "天印大道", "龙眠大道", "南医大·江苏经贸学院", "南京交院", "中国药科大学"};
		String lineNo="S1号线";
		file.READ_PATH = "subway.txt";
		String temp=file.readLine(lineNo);
		file.WRITE_PATH = "station.txt";
		file.writeStations(temp);
		
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * FileName:FileIO.java
 * 处理文件的读写
 */
public class FileIO {

    public static String READ_PATH; //读取路径
    public static String WRITE_PATH; //写入路径

    /**
     * 读取文件初始化地铁线路
     */
    public static void readSubway(List < Station > map) throws IOException {
        File readfile = new File(READ_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(readfile));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            int trim = tempString.indexOf(' ');
            String lineNo = tempString.substring(0, trim);
            tempString = tempString.substring(trim + 1);
            String[] stations = tempString.split(" ");

            for (String s: stations) { //初始化站点
                Station test = new Station(s, lineNo); //要添加的车站
                if (map.contains(test)) { //已经有了
                    int index = (map.indexOf(test));
                    map.get(index).isInterchangeStation = true;
                    map.get(index).line.add(lineNo);
                } else {
                    map.add(test);
                }
            }

            for (int i = 0; i < stations.length; i++) { //添加相邻站点
                Station s = new Station(stations[i], lineNo);
                int index = (map.indexOf(s));
                if(stations.length==1) {
                	break;
                }
                if (i == 0) {
                    Station pres = new Station(stations[i + 1], lineNo);
                    int preindex = (map.indexOf(pres));
                    map.get(index).linkStations.add(map.get(preindex));
                } else if (i == stations.length - 1) {
                    Station nexts = new Station(stations[i - 1], lineNo);
                    int nextindex = (map.indexOf(nexts));
                    map.get(index).linkStations.add(map.get(nextindex));
                } else {
                    Station pres = new Station(stations[i + 1], lineNo);
                    int preindex = (map.indexOf(pres));
                    Station nexts = new Station(stations[i - 1], lineNo);
                    int nextindex = (map.indexOf(nexts));
                    map.get(index).linkStations.add(map.get(preindex));
                    map.get(index).linkStations.add(map.get(nextindex));
                }
            }
        }
        reader.close();
    }

    /**
     * 读取指定线路信息并返回，如果未找到则抛出异常
     */
    public static String readLine(String lineNo) throws IOException {
        File readfile = new File(READ_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(readfile));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            if (tempString.startsWith(lineNo)) {
                reader.close();
                return tempString;
            }
        }
        reader.close();
        throw new RuntimeException("The line you inquired is NOT found!");
    }

    /**
     * 将换乘线路写入文件
     */
    public static void writeStations(String result) {
        try {
            File writeFile = new File(WRITE_PATH);
            writeFile.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(writeFile), "UTF-8"); BufferedWriter writer = new BufferedWriter(writerStream)) {
                writer.write(result);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
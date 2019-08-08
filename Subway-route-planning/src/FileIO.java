
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
 * �����ļ��Ķ�д
 * ����������̬���ԣ�������̬��������ͬ�Ķ�ȡ�ļ�����һ����̬����д���ļ�
 * @author yang1
 *
 */

public class FileIO {
	
	public static String READ_PATH;
	public static String WRITE_PATH;
	/**
	 * 
	 * @param station
	 * @return
	 */
	public static void readSubway(List<Station> map) throws IOException {
		File readfile=new File(READ_PATH);
		BufferedReader reader = null;
		reader=new BufferedReader(new FileReader(readfile));
		String tempString = null;
		while((tempString = reader.readLine()) != null) {
			int trim=tempString.indexOf(' ');
			String lineNo=tempString.substring(0, trim);
			tempString=tempString.substring(trim+1);
			String[] stations=tempString.split(" ");
			
			
			for(String s:stations) {
				Station test=new Station(s,lineNo);//Ҫ��ӵĳ�վ
				if(map.contains(test)) {//�Ѿ�����
					int index=(map.indexOf(test));
					
					map.get(index).isInterchangeStation=true;
					map.get(index).line.add(lineNo);
					
				}
				else {
					map.add(test);
				}
			}
			
			for (int i=0;i<stations.length;i++) {
				Station s=new Station(stations[i],lineNo);
				int index=(map.indexOf(s));
				if(i==0) {
					Station pres=new Station(stations[i+1],lineNo);
					int preindex=(map.indexOf(pres));
					map.get(index).linkStations.add(map.get(preindex));
				}
				else if(i==stations.length-1) {
					Station nexts=new Station(stations[i-1],lineNo);
					int nextindex=(map.indexOf(nexts));
					map.get(index).linkStations.add(map.get(nextindex));
				}
				else {
					Station pres=new Station(stations[i+1],lineNo);
					int preindex=(map.indexOf(pres));
					Station nexts=new Station(stations[i-1],lineNo);
					int nextindex=(map.indexOf(nexts));
					map.get(index).linkStations.add(map.get(preindex));
					map.get(index).linkStations.add(map.get(nextindex));
				}
			}
		}
		reader.close();
	}
	/**
	 * 
	 * @param station
	 * @return
	 */
	public static String readLine(String lineNo) throws IOException {
		File readfile = new File(READ_PATH);
		BufferedReader reader = null;
		reader=new BufferedReader(new FileReader(readfile));
		String tempString = null;
		while((tempString = reader.readLine()) != null) {
			if(tempString.startsWith(lineNo)) {
				reader.close();
				return tempString;
			}	
		}
			reader.close();
			throw new IllegalArgumentException("The line you inquired is NOT found!");
	}
	/**
	 * 
	 * @param station
	 * @return
	 */
	public static void writeStations(String result)  {
		 try {
			 File writeFile = new File(WRITE_PATH); // ���·�������û����Ҫ����һ���µ�output.txt�ļ�
			 writeFile.createNewFile();// �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
			 try(OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(writeFile),"UTF-8");
					 BufferedWriter writer = new BufferedWriter(writerStream)
			){
				 writer.write(result);
					writer.flush(); // �ѻ���������ѹ���ļ� 
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}

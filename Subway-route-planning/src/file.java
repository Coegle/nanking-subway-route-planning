import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class file {
	public static String READ_PATH;
	public static String WRITE_PATH;
	public static void readSubway() {
		File readfile=new File(READ_PATH);
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(readfile),"GBK");
			System.out.println("read successfully");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
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
	public static void writeStations(String result) {
		try {
            File writeFile = new File(WRITE_PATH); // ���·�������û����Ҫ����һ���µ�output.txt�ļ�
            writeFile.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            try (FileWriter writer = new FileWriter(writeFile);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(result);
                out.flush(); // �ѻ���������ѹ���ļ�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}

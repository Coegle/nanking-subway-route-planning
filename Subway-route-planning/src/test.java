import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) throws IOException {
		//line test=new line();
		//test.lineNo="1����";
		//test.station[]= {"������", "��ɽ����԰", " �Ͼ�վ", "��ģ����·", "������", "��¥", "�齭·", "�½ֿ�", "�Ÿ�԰", "��ɽ��", "�л���", "������", "��¡��", "������", "������", "�Ͼ���վ", "˫�����", "�Ӷ���", "ʤ̫·", "�ټҺ�", "С����", "��ɽ·", "��ӡ���", "���ߴ��", "��ҽ�󡤽��վ�óѧԺ", "�Ͼ���Ժ", "�й�ҩ�ƴ�ѧ"};
		String lineNo="S1����";
		file.READ_PATH = "subway.txt";
		String temp=file.readLine(lineNo);
		file.WRITE_PATH = "station.txt";
		file.writeStations(temp);
		
	}
}

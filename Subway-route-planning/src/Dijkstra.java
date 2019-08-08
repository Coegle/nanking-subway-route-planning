
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * FileName:Dijkstra.java
 * 
 * @author yang1
 *
 */

public class Dijkstra {
	private List<Station> outList = new ArrayList<Station>();//�Ѿ���������վ��
	
	/**
	 * 
	 * @param station
	 * @return
	 */
	public void cal(Station s1, Station s2,int num) {

		if(outList.size()==num) {//���
			finish(s1,s2);
			return;
		}
		
		if(!outList.contains(s1)) {//��ʼ��
			outList.add(s1);
		}
		if(s1.solutions.isEmpty()) {
			for(Station s:s1.linkStations) {
				s1.getAllPassedStations(s).add(s);
			}
		}
		Station parent = getShortestPath(s1);
		if(parent==s2) {//�ҵ�
			finish(s1,s2);
			return;
		}
		for(Station child:parent.linkStations) {
			if(outList.contains(child)) {
				continue;
			}
			int shortestPath = s1.getAllPassedStations(parent).size();
			if(s1.getAllPassedStations(child).contains(child)){
				//���s1�Ѿ����������child�ľ������룬��ô�Ƚϳ���С�ľ���
				if((s1.getAllPassedStations(child).size()-1) > shortestPath){
					//����S1����Χ��վ����С·��
					s1.getAllPassedStations(child).clear();
					s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
					s1.getAllPassedStations(child).add(child);
				}
			} else {
				//���s1��û�м��������child�ľ�������
				s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
				s1.getAllPassedStations(child).add(child);
			}
		}
		outList.add(parent);
		cal(s1,s2,num);//�ظ����㣬������վ����չ
	}
	
	/**
	 * 
	 * @param station
	 * @return
	 */
	private Station getShortestPath(Station station){
		int minPatn = Integer.MAX_VALUE;
		Station rets = null;
		for(Station s :station.solutions.keySet()){
			if(outList.contains(s)){
				continue;
			}
			LinkedHashSet<Station> set  = station.getAllPassedStations(s);//����station��s������������վ��ļ���
			if(set.size() < minPatn){
				minPatn = set.size();
				rets = s;
			}
		}
		return rets;
	}
	
	/**
	 * 
	 * �����������Ľ������result
	 * ��getAllPassStations�л�����·������վ��
	 * ͨ��isInterchangeStation�ж��Ƿ������˲����뻻����Ϣ
	 * @param 
	 * @return 
	 */
	private void finish(Station s1,Station s2) {
		String result= "";
		Station[] s =new Station[s1.getAllPassedStations(s2).size()];
		s1.getAllPassedStations(s2).toArray(s);
		result +=s.length+"\n"+s[0].name;
		
		for(int i=1;i<s.length-1;i++) {
			result+="\n"+s[i].name;
			if(s[i].isInterchangeStation && s[i].isInterchange(s[i-1],s[i+1])) {
				for(String x:s[i].line) {
					for(String y:s[i+1].line) {
						if(x==y) result+="\n"+x;
					}
				}
			}
		}
		result+="\n"+s[s.length-1].name;
		System.out.println(result);
		FileIO.writeStations(result);
	}
}

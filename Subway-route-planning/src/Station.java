
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * FileName:Station.java
 * 
 * @author yang1
 *
 */
public class Station {
	public String name;
	public List<String> line = new ArrayList<>();//所属路线
	public List<Station> linkStations = new ArrayList<>();//相邻站点
	public boolean isInterchangeStation=false;//是否是换乘站
	public Map<Station, LinkedHashSet<Station>> solutions = new HashMap<Station, LinkedHashSet<Station>>();//到各个车站的最短路线
	
	/**
	 * 判断是否发生了换乘
	 * @param station
	 * @return 
	 */
	public boolean isInterchange(Station s1, Station s2) {
		for(String x:s1.line) {
			for(String y:s2.line) {
				if(x==y) return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param station
	 * @return
	 */
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		} else if(obj instanceof Station){
			Station s = (Station) obj;
			if(s.name.equals(this.name)){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @param station
	 * @return
	 */
	public int hashCode() {
		return this.name.hashCode();
	}
	/**
	 * 
	 * @param station
	 * @return
	 */
	public LinkedHashSet<Station> getAllPassedStations(Station station) {
		if(solutions.get(station) == null){
			LinkedHashSet<Station> set = new LinkedHashSet<Station>(); 
			set.add(this);
			solutions.put(station, set);
		}
		return solutions.get(station);
	}

	/**
	 * 
	 * @param station
	 * @return
	 */
	public Station(String n, String l) {
		name=n;
		line.add(l);
	}
	public Station() {
		
	}
	
//	public String toString() {
//		String temp="";
//		for(Station s:this.linkStations) {
//			temp+=" "+s.name;
//		}
//		return this.name+":"+this.line+",相邻车站："+temp;
//	}
}

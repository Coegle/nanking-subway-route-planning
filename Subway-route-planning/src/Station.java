import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * FileName:Station.java
 * 站点类：包括站点的各种域及其方法
 */
public class Station {
    public String name; //名称
    public List < String > line = new ArrayList < > (); //所属路线
    public List < Station > linkStations = new ArrayList < > (); //相邻站点
    public boolean isInterchangeStation = false; //是否是换乘站
    public Map < Station, LinkedHashSet < Station >> solutions = new HashMap < Station, LinkedHashSet < Station >> (); //到各个车站的最短路线

    /**
     * 判断是否发生了换乘
     */
    public boolean isInterchange(Station s1, Station s2) {
        for (String x: s1.line) {
            for (String y: s2.line) {
                if (x == y) return false;
            }
        }
        return true;
    }

    /**
     *@Override 
     *重写equals方法：当且仅当Station的name域相同时返回true
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Station) {
            Station s = (Station) obj;
            if (s.name.equals(this.name)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @Override
     * 重写hashCode方法：返回该Station对象name域的hashCode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * 获取该站到指定站点依次经过的站点
     */
    public LinkedHashSet < Station > getAllPassedStations(Station station) {
        if (solutions.get(station) == null) {
            LinkedHashSet < Station > set = new LinkedHashSet < Station > ();
            set.add(this);
            solutions.put(station, set);
        }
        return solutions.get(station);
    }

    /**
     * 构造器：初始化name和line域
     */
    public Station(String n, String l) {
        name = n;
        line.add(l);
    }

    /**
     * 无参构造器
     */
    public Station() {}

    /**
     * @Override
     * 重写toString方法：返回Station对象的各个域信息
     */
//    public String toString() {
//        String temp = "";
//        for (Station s: this.linkStations) {
//            temp += " " + s.name;
//        }
//        return this.name + ":" + this.line + ",相邻车站：" + temp;
//    }
}
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * FileName:Dijkstra.java
 * 算法模块，利用Dijkstra算法寻找最短路径
 */
public class Dijkstra {
    private List < Station > outList = new ArrayList < Station > (); //已经分析过的站点

    /**
     *Dijkstra算法 
     */
    public void cal(Station s1, Station s2, int num) {

        if (outList.size() == num) { //未找到
            FileIO.writeStations("没有可达路线");
            return;
        }

        if (!outList.contains(s1)) { //初始化
            outList.add(s1);
        }

        if (s1.solutions.isEmpty()) {
            for (Station s: s1.linkStations) {
                s1.getAllPassedStations(s).add(s);
            }
        }

        Station parent = getShortestPath(s1);

        if (parent == s2) { //找到
            finish(s1, s2);
            return;
        }
        if(parent != null) {
        	for (Station child: parent.linkStations) {
        		if (outList.contains(child)) {
        			continue;
        		}

        		int shortestPath = s1.getAllPassedStations(parent).size();

        		if (s1.getAllPassedStations(child).contains(child)) {
        			//如果s1已经计算过到此child的经过距离，那么比较出最短的距离
        			if ((s1.getAllPassedStations(child).size() - 1) > shortestPath) {
        				s1.getAllPassedStations(child).clear(); //重置S1到周围各站的最短路径
        				s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
        				s1.getAllPassedStations(child).add(child);
        			}
        		} else {
        			//如果s1还没有计算过到此child的经过距离
        			s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
        			s1.getAllPassedStations(child).add(child);
        		}
        	}
        }


        outList.add(parent);
        cal(s1, s2, num); //递归，向相邻站点扩展
    }

    /**
     * Dijkstra算法的部分功能：返回当前状态下的最短路径
     */
    private Station getShortestPath(Station station) {
        int minPatn = Integer.MAX_VALUE;
        Station rets = null;

        for (Station s: station.solutions.keySet()) {
            if (outList.contains(s)) {
                continue;
            }

            LinkedHashSet < Station > set = station.getAllPassedStations(s); //参数station到s所经过的所有站点的集合

            if (set.size() < minPatn) {
                minPatn = set.size();
                rets = s;
            }
        }

        return rets;
    }

    /**
     * 处理生成最后的结果串：由途经站点和换乘信息组成
     */
    private void finish(Station s1, Station s2) {
        String result = "";
        Station[] s = new Station[s1.getAllPassedStations(s2).size()];
        s1.getAllPassedStations(s2).toArray(s);
        result += s.length + "\n" + s[0].name;

        for (int i = 1; i < s.length - 1; i++) {
            result += "\n" + s[i].name;
            if (s[i].isInterchangeStation && s[i].isInterchange(s[i - 1], s[i + 1])) {
                for (String x: s[i].line) {
                    for (String y: s[i + 1].line) {
                        if (x == y) result += "\n" + x;
                    }
                }
            }
        }
        result += "\n" + s[s.length - 1].name;
//        System.out.println(result);
        FileIO.writeStations(result);
    }
}
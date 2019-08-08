
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.*;

/**
 * FileName:Subway.java
 * ��������ڣ����ڽ��������в���
 * @author yang1
 *
 */
 class Subway {
	public static void main(String[] args) throws IOException {
		String[] args1 = {"-map", "subway.txt", "-o", "station.txt", "-b","������", "������"};
		// ���������в�������Option���󣬵�1/2/3�������ֱ���ָ�����в�������д���Ƿ��в���ֵ����������
		Option opt_map = new Option("map",true,"input file name");
        opt_map.setRequired(true);
        Option opt_o = new Option("o",true,"output file name");
        opt_o.setRequired(false);    // ���øò����Ƿ��Ǳ����
        Option opt_a = new Option("a",true,"inquire stations");
        opt_a.setRequired(false);
        Option opt_b = OptionBuilder.withArgName("args")
        		.hasArgs(2)
        		.withDescription("inquire the shortest route")
        		.create("b");
        //hasArgs()��ʾ�����ĸ���

        Options options = new Options();
        options.addOption(opt_map);
        options.addOption(opt_o);
        options.addOption(opt_a);
        options.addOption(opt_b);

        CommandLine cli = null;
        CommandLineParser cliParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            cli = cliParser.parse(options, args1);
        } catch (ParseException e) {
            // ����ʧ������ HelpFormatter ��ӡ ������Ϣ
            helpFormatter.printHelp(">>>>>>WRONG OPTIONS!", options);
            e.printStackTrace();
        }
        String readfile=null, writefile=null;
        //���ݲ�ͬ����ִ�в�ͬ�߼�
        
        readfile = cli.getOptionValue("map");  // ��ȡ������map����Ӧ�Ĳ���ֵ
        FileIO.READ_PATH=System.getProperty("user.dir") + File.separator + "\\" +readfile;
        List<Station> map = new ArrayList<>();
        FileIO.readSubway(map);
        
        if(cli.hasOption("o") && cli.hasOption("a") && !cli.hasOption("b")){//��ѯ��·
            writefile = cli.getOptionValue("o","1");
            String lineNo = cli.getOptionValue("a");
            FileIO.WRITE_PATH=System.getProperty("user.dir") + File.separator + "\\" +writefile;
            String result = FileIO.readLine(lineNo);
            FileIO.writeStations(result);
        }
        else if(cli.hasOption("o") && cli.hasOption("b") && !cli.hasOption("a")){//��ѯ��̻���
        	writefile = cli.getOptionValue("o","1");
        	FileIO.WRITE_PATH=System.getProperty("user.dir") + File.separator + "\\" +writefile;
        	String[] a = cli.getOptionValues("b");
        	if(!map.contains(new Station(a[0],"")) || !map.contains(new Station(a[1],""))) {
        		throw new IllegalArgumentException("The station you inquired is NOT found!");
        	}
    		Dijkstra sw = new Dijkstra();
    		sw.cal(map.get(map.indexOf(new Station(a[0],""))), map.get(map.indexOf(new Station(a[1],""))) , map.size());
        }
        else {
        	throw new IllegalArgumentException("WRONG ARGUMENTS MATCHING!");
        }
	}
}

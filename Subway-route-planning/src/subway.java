
import java.io.File;
import java.util.Arrays;

import org.apache.commons.cli.*;

 class subway {
	public static void main(String[] args) {
		String[] args1 = {"-map", "subway.txt", "-o", "station.txt", "-b", "�Ͼ���վ", "�껨̨վ"};
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
        file.READ_PATH=System.getProperty("user.dir") + File.separator + "\\" +readfile;
        file.readSubway();
        
        if(cli.hasOption("o") && cli.hasOption("a") && !cli.hasOption("b")){//��ѯ��·
            writefile = cli.getOptionValue("o","1");
            file.writeStations(null);
        }
        else if(cli.hasOption("o") && cli.hasOption("b") && !cli.hasOption("a")){//��ѯ��̻���
        	String[] a = cli.getOptionValues("b");
            System.out.println(String.format("%s", Arrays.asList(a)));
            file.writeTransferPlan(null);
        }
        else {
        	throw new IllegalArgumentException("WRONG ARGUMENTS MATCHING!");
        }
	}
}

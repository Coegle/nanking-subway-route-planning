
import java.io.File;
import java.util.Arrays;

import org.apache.commons.cli.*;

 class subway {
	public static void main(String[] args) {
		String[] args1 = {"-map", "subway.txt", "-o", "station.txt", "-b", "南京南站", "雨花台站"};
		// 根据命令行参数定义Option对象，第1/2/3个参数分别是指命令行参数名缩写、是否有参数值、参数描述
		Option opt_map = new Option("map",true,"input file name");
        opt_map.setRequired(true);
        Option opt_o = new Option("o",true,"output file name");
        opt_o.setRequired(false);    // 设置该参数是否是必须的
        Option opt_a = new Option("a",true,"inquire stations");
        opt_a.setRequired(false);
        Option opt_b = OptionBuilder.withArgName("args")
        		.hasArgs(2)
        		.withDescription("inquire the shortest route")
        		.create("b");
        //hasArgs()表示参数的个数

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
            // 解析失败是用 HelpFormatter 打印 帮助信息
            helpFormatter.printHelp(">>>>>>WRONG OPTIONS!", options);
            e.printStackTrace();
        }
        String readfile=null, writefile=null;
        //根据不同参数执行不同逻辑
        
        readfile = cli.getOptionValue("map");  // 获取参数“map”对应的参数值
        file.READ_PATH=System.getProperty("user.dir") + File.separator + "\\" +readfile;
        file.readSubway();
        
        if(cli.hasOption("o") && cli.hasOption("a") && !cli.hasOption("b")){//查询线路
            writefile = cli.getOptionValue("o","1");
            file.writeStations(null);
        }
        else if(cli.hasOption("o") && cli.hasOption("b") && !cli.hasOption("a")){//查询最短换乘
        	String[] a = cli.getOptionValues("b");
            System.out.println(String.format("%s", Arrays.asList(a)));
            file.writeTransferPlan(null);
        }
        else {
        	throw new IllegalArgumentException("WRONG ARGUMENTS MATCHING!");
        }
	}
}

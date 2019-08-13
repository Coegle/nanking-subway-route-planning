import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.*;

/**
 * FileName:Subway.java
 * 程序主入口，用于解析命令行参数
 */
class Subway {
    public static void main(String[] args) throws IOException, ParseException {
        // 根据命令行参数定义Option对象，第1/2/3个参数分别是指命令行参数名缩写、是否有参数值、参数描述
        Option opt_map = new Option("map", true, "input file name");
        opt_map.setRequired(true);
        Option opt_o = new Option("o", true, "output file name");
        opt_o.setRequired(false); // 设置该参数是否是必须的
        Option opt_a = new Option("a", true, "inquire stations");
        opt_a.setRequired(false);
        Option opt_b = OptionBuilder.withArgName("args")
            .hasArgs(2)
            .withDescription("inquire the shortest route")
            .create("b");
        opt_b.setRequired(false);
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
            cli = cliParser.parse(options, args);
        } catch (ParseException e) {
            // 解析失败用 HelpFormatter 打印 帮助信息
            helpFormatter.printHelp(">>>>>>WRONG OPTIONS!", options);
            e.printStackTrace();
            throw e;
        }
        String readfile = null, writefile = null;
        //根据不同参数执行不同逻辑
        
        readfile = cli.getOptionValue("map"); // 获取参数“map”对应的参数值
        FileIO.READ_PATH = System.getProperty("user.dir") + File.separator + "\\" + readfile;
        List < Station > map = new ArrayList < > ();
        FileIO.readSubway(map);

        if (cli.hasOption("o") && cli.hasOption("a") && !cli.hasOption("b")) { //查询线路
            writefile = cli.getOptionValue("o", "1");
            String lineNo = cli.getOptionValue("a");
            FileIO.WRITE_PATH = System.getProperty("user.dir") + File.separator + "\\" + writefile;
            String result = FileIO.readLine(lineNo);
            FileIO.writeStations(result);
        } else if (cli.hasOption("o") && cli.hasOption("b") && !cli.hasOption("a")) { //查询最短换乘
            writefile = cli.getOptionValue("o", "1");
            FileIO.WRITE_PATH = System.getProperty("user.dir") + File.separator + "\\" + writefile;
            String[] a = cli.getOptionValues("b");
            if (!map.contains(new Station(a[0], "")) || !map.contains(new Station(a[1], ""))) {
                throw new RuntimeException("The station you inquired is NOT found!");
            }
            Dijkstra sw = new Dijkstra();
            sw.cal(map.get(map.indexOf(new Station(a[0], ""))), map.get(map.indexOf(new Station(a[1], ""))), map.size());
        } else if(!cli.hasOption("o") && !cli.hasOption("b") && !cli.hasOption("a")) {
        } else {
            throw new RuntimeException("WRONG ARGUMENTS MATCHING!");
        }
    }
}
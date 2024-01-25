import web.ParseException_Exception;
import web.ServerInterfaceImplService;
import web.Todo;

import java.util.Scanner;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Lenovo]
 * @version : [v1.0]
 * @createTime : [2023/12/2 19:49]
 */
//wsimport -encoding utf8 -keep -s "D:\javaprograms\test88\src" -p web -Xnocompile http://localhost:6868/01-ws-java-server/ws?wsdl
// a a 2021-1-1-1:0 2021-1-1-2:0 a
// a a 2021-1-1-3:0 2021-1-1-4:0 qw
public class Client {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 为维护方便，所有操作结果信息写在此处
     */
    private static final String WRONG_PARAMETER = "参数错误!";
    private static final String TIME_FORMAT = "时间格式：yyyy-MM-dd-HH:mm";
    private static ServerInterfaceImplService serverInterfaceImplService = new ServerInterfaceImplService();
    public static Todo todo = serverInterfaceImplService.getTodoPort();

    public static void main(String[] args) throws ParseException_Exception {
        helpMenu();

        while (true) {
            String line = scanner.nextLine();
            int choice = Integer.parseInt(line);
            service(choice);
            System.out.println("请输入一个操作：");
        }
    }

    private static void service(int choice) throws ParseException_Exception {
        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                add();
                break;
            case 3:
                query();
                break;
            case 4:
                delete();
                break;
            case 5:
                clear();
                break;
            case 6:
                helpMenu();
                break;
            case 7:
                System.exit(0);
            default:
                System.err.println(WRONG_PARAMETER);
        }
    }

    private static void helpMenu() {
        System.out.println("帮助菜单:");
        System.out.println(TIME_FORMAT);
        System.out.println("\t 1.用户注册");
        System.out.println("\t\t 参数: <username> <password>");
        System.out.println("\t 2.添加待办事项");
        System.out.println("\t\t 参数: <username> <password> <start> <end> <tag>");
        System.out.println("\t 3.查询待办事项");
        System.out.println("\t\t 参数: <username> <password> <start> <end>");
        System.out.println("\t 4.删除待办事项");
        System.out.println("\t\t 参数: <username> <password> <ID>");
        System.out.println("\t 5.清空待办事项");
        System.out.println("\t\t 参数: <username> <password>");
        System.out.println("\t 6.帮助菜单");
        System.out.println("\t\t 参数: no args");
        System.out.println("\t 7.退出");
        System.out.println("\t\t 参数: no args");
        System.out.println("请输入一个操作：");
    }
    private static void register() {
        System.out.println("请输入参数：<username> <password>");
        String line = scanner.nextLine();
        String[] cmds = line.split(" ");

        if (cmds.length != 2) {
            System.out.println(WRONG_PARAMETER);
        } else {
            System.out.println(todo.register(cmds[0], cmds[1]));
        }
    }

    private static void add() {
        System.out.println("请输入参数：<username> <password> <start> <end> <tag>");
        String line = scanner.nextLine();
        String[] cmds = line.split(" ");

        if (cmds.length != 5) {
            System.out.println(WRONG_PARAMETER);
        } else {
            System.out.println(todo.addTodo(cmds[0], cmds[1], cmds[2], cmds[3], cmds[4]));
        }
    }

    private static void query() throws ParseException_Exception {
        System.out.println("请输入参数：<username> <password> <start> <end>");
        String line = scanner.nextLine();
        String[] cmds = line.split(" ");

        if (cmds.length != 4) {
            System.out.println(WRONG_PARAMETER);
        } else {
            System.out.println(todo.queryTodo(cmds[0], cmds[1], cmds[2], cmds[3]));
        }
    }

    private static void delete() {
        System.out.println("请输入参数：<username> <password> <ID>");
        String line = scanner.nextLine();
        String[] cmds = line.split(" ");

        if (cmds.length != 3) {
            System.out.println(WRONG_PARAMETER);
        } else {
            System.out.println(todo.deleteTodo(cmds[0], cmds[1], cmds[2]));
        }
    }

    private static void clear() {
        System.out.println("请输入参数：<username> <password>");
        String line = scanner.nextLine();
        String[] cmds = line.split(" ");

        if (cmds.length != 2) {
            System.out.println(WRONG_PARAMETER);
        } else {
            System.out.println(todo.clearTodo(cmds[0], cmds[1]));
        }
    }
}

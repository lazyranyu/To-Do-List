import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Lenovo]
 * @version : [v1.0]
 * @createTime : [2023/12/2 10:53]
 */

@WebService(name = "todo", portName = "todoPort", targetNamespace = "http://www.todo.com")
public class ServerInterfaceImpl implements ServerInterface{
    private Vector<User> users = new Vector<>();
    private Vector<TodoItem> todos = new Vector<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
    private static int ID = 0;

    /**
     * 为维护方便，所有返回信息写在此处
     */
    private static final String CRLF = "\r\n";
    private static final String TAB = "\t";
    private static final String REGISTER_SUCCESS = "注册成功！";
    private static final String REGISTER_FAILURE = "用户名已存在，请选择另一个用户名！";
    private static final String ADD_SUCCESS = "添加待办事项成功！";
    private static final String DELETE_SUCCESS = "删除待办事项成功！";
    private static final String DELETE_FAILURE = "查无该待办事项！";
    private static final String CLEAR_SUCCESS = "清除待办事项成功!";

    private static final String INVALID_TIME = "无效时间!";
    private static final String INVALID_USER = "用户名或密码错误";
    private static final String LOGIN_SUCCESS = "登录成功！";
    private static final String INVALID_TIME_FORMAT = "无效时间格式! (yyyy-MM-dd-HH:mm)";
    @Override
    public String register(String username, String password) {

        User user = new User(username, password);
        if (users.isEmpty())
        {
            users.add(user);
            System.out.println(user.toString() + REGISTER_SUCCESS);
            return username+REGISTER_SUCCESS;
        }else {
            for (User user0: users) {
                if (user0.getUserName().equals(username))
                {
                    return REGISTER_FAILURE;
                }
            }
            users.add(user);
            System.out.println(user.toString() + REGISTER_SUCCESS);
            return username+REGISTER_SUCCESS;
        }
    }

    @Override
    public String login(String username, String password) {
        String out=null;
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                out = username + LOGIN_SUCCESS;
                break;
            }else {
                out =  INVALID_USER;
            }
        }
        System.out.println(username + LOGIN_SUCCESS);
        return out;
    }
    @Override
    public String addTodo(String username, String password, String start, String end, String introduce) {
        if (login(username, password).endsWith(LOGIN_SUCCESS))
        {
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = simpleDateFormat.parse(start);
                endDate = simpleDateFormat.parse(end);
                if (startDate.compareTo(endDate) > 0){
                    System.out.println(INVALID_TIME);
                    return INVALID_TIME;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                System.err.println(INVALID_TIME_FORMAT);
                System.out.println(INVALID_TIME_FORMAT);
                return INVALID_TIME_FORMAT;
            }
            TodoItem todo = new TodoItem(username, startDate, endDate, introduce,ID++);
            todos.add(todo);
            System.out.println(ADD_SUCCESS);
            return ADD_SUCCESS;
        }else {
            System.out.println(INVALID_USER);
            return INVALID_USER;
        }
    }

    @Override
    public String queryTodo(String username,String password, String start, String end) throws ParseException {
        Vector<TodoItem> queryResults = new Vector<TodoItem>();
        Date startDate = simpleDateFormat.parse(start);
        Date endDate = simpleDateFormat.parse(end);
        if (login(username, password).endsWith(LOGIN_SUCCESS))
        {
            for (TodoItem todo:todos) {
                if (todo.getUsername().equals(username))
                {
                    if (startDate.compareTo(todo.getStartTime()) < 0 && endDate.compareTo(todo.getEndTime()) > 0)
                    {
                        queryResults.add(todo);
                    }
                }
            }
        }else {
            System.out.println(INVALID_USER);
            return  INVALID_USER;
        }
        Comparator<TodoItem> comparator = new Comparator<TodoItem>() {
            public int compare(TodoItem o1, TodoItem o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        };
        queryResults.sort(comparator);
        if (queryResults.size()!=0)
        {
            StringBuilder out = new StringBuilder();
            for (TodoItem todoItem:queryResults) {
                out.append(todoItem.toString()).append(CRLF);
            }
            System.out.println("查询结果："+CRLF+out.toString());
            return "查询结果："+CRLF+out.toString();
        }else {
            System.out.println(DELETE_FAILURE);
            return DELETE_FAILURE;
        }
    }

    @Override
    public String deleteTodo(String username, String password, String todoId) {
        String result = null;
        if (!login(username, password).endsWith(LOGIN_SUCCESS)) {
            System.out.println(INVALID_USER);
            result = INVALID_USER;
            return result;
        }
        for (TodoItem todo : todos) {
                if (todo.getUsername().equals(username) && todo.getTodoId().equals(todoId)) {
                    todos.remove(todo);
                    System.out.println("todo"+todoId+DELETE_SUCCESS);
                    result =  DELETE_SUCCESS;
                    break;
                }else {
                    System.out.println(DELETE_FAILURE);
                    result =  DELETE_FAILURE;
                }
            }
        System.out.println(result);
        return result;
    }
    @Override
    public String clearTodo(String username,String password) {
        String result = DELETE_FAILURE;
        if (!login(username, password).endsWith(LOGIN_SUCCESS)) {
            System.out.println(INVALID_USER);
            result = INVALID_USER;
            return result;
        }
        todos.removeIf(todo -> todo.getUsername().equals(username));
        result = CLEAR_SUCCESS;
        System.out.println(result);
        return result;
    }
}

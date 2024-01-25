import java.text.ParseException;
import java.util.Date;

public interface ServerInterface {
    public String register(String username, String password);
    public String login(String username, String password);
    public String addTodo(String username, String password, String start, String end, String introduce);
    public String queryTodo(String username,String password,String start,String end) throws ParseException;
    public String deleteTodo(String username, String password, String todoId);
    public String clearTodo(String username,String password);
}

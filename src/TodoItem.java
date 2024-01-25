import java.util.Date;
import java.util.UUID;

import static java.lang.Math.random;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Lenovo]
 * @version : [v1.0]
 * @createTime : [2023/12/2 11:07]
 */
public class TodoItem {
    String username;
    Date startTime;
    Date endTime;
    String introduce;
    String todoId;

    public TodoItem(String username, Date startTime, Date endTime, String introduce,int ID) {
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
        this.introduce = introduce;
        this.todoId = Integer.toString(ID);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void  setTodoId(String todoId) {
        this.todoId = todoId;
    }
    public String getTodoId() {
        return todoId;
    }


    @Override
    public String toString() {
        return "TodoItem{" +
                "username='" + username + '\'' +
                ", todoId=" + todoId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}

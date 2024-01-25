import javax.xml.ws.Endpoint;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Lenovo]
 * @version : [v1.0]
 * @createTime : [2023/12/2 10:53]
 */
public class Server{
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:6868/01-ws-java-server/ws", new ServerInterfaceImpl());
        //wsimport -keep http://127.0.0.1:8001/webservice/todoList?wsdl
        System.out.println("服务开始");
        System.out.println("webservice发布成功：");
    }
}

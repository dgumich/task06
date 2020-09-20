package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    /**
     * Метод для создания сервера с использованием класса HttpServer.
     * @throws IOException
     */
    public static void createServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001),0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

}

package server;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Класс обработчик HTTP запросов
 *
 */

public class MyHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        //строка с ответом
        String response = null;

        //проверка на тип запроса
        if ("GET".equals(httpExchange.getRequestMethod())) {

            //если запрос GET выбирается текущая директория
            File file = new File(".");
            ArrayList<String> result = new ArrayList<>();

            // создается массив файлов, которые есть в текущей директории
            File[] files = file.listFiles();

            //если массив не равен null и длина его не равна 0
            if (files != null && files.length != 0) {

                //массив обходится и в ArrayList помещаются имена файлов
                for (File file1 : files) {
                    result.add(file1.getName());
                }
                response = result.toString();
            } else {
                response = "There is no files/dir";
            }

            //В консоль выводится информация о том, что кто-то сделал GET запрос
            System.out.println("Somebody try GET request");
            httpExchange.sendResponseHeaders(200, response.length());

        } else {

            //Для всех остальных запросов, отличных от GET передаем 404
            httpExchange.sendResponseHeaders(404,-1);

            //В консоль выводится информация о том, что кто-то сделал запрос, отличный от GET
            System.out.println("Somebody tried not GET request");
            response = "404";

        }
        //В блоке try-with-resources записывает ответ
        try (OutputStream outputStream = httpExchange.getResponseBody()) {
            outputStream.write(response.getBytes());
            outputStream.flush();

        }
    }
}



package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SampleThread extends Thread{
    Socket client = null;
    BufferedReader input;
    PrintWriter output;

    public SampleThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            output = new PrintWriter(client.getOutputStream());
            while (!input.ready());
            String entry = input.readLine().replace("/", "");
            System.out.println(entry);
            if(entry.startsWith("GET")){
                String[] str = entry.split(" ");
                Text text = new Text(str[1]);
                text.Calculate();
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<p>Работу выполняли: Карчевский Дмитрий Сергеевич</p><p>Номер группы: ИКБО-03-17</p>" +
                        "<p>Номер индивидуального задания: 9</p><p>Текст индивидуального задания: Подсчет одинаковых символов</p>");
                output.println(text.getResult());
                output.flush();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

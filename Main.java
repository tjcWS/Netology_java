package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://api.nasa.gov/planetary/apod"
                + "?api_key=oMIeFWhC6eDfABqAQcDOSZx280mkf28ezPLIjgNZ";
                //"&date=2024-08-06";

        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);

//        Scanner scanner = new Scanner(response.getEntity().getContent());
//        String imageInfo = scanner.nextLine();
//        System.out.println(imageInfo); //вывели данные

        NasaAnswer answer = mapper.readValue(response.getEntity().getContent(), NasaAnswer.class);
        String imageUrl = answer.url;
        String[] splitted = imageUrl.split("/");
        String fileName = splitted[splitted.length - 1];

        HttpGet imageReq = new HttpGet(imageUrl);
        response = client.execute(imageReq);

        FileOutputStream fos = new FileOutputStream("Nasa/" + fileName);
        response.getEntity().writeTo(fos);

//        System.out.println(answer.date);
//        System.out.println(answer.title);
//        System.out.println(answer.url);
    }
}
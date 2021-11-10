package com.haiduk;

import com.haiduk.WebService.MyHttpClient;
import com.haiduk.WebService.MyURLConnection;

public class MainApp {
    public static void main(String[] args) {


                 String request = args[0];
         int id = Integer.parseInt(args[1]);

        MyHttpClient myHttpClient = new MyHttpClient();
//        myHttpClient.doPost(1);


        if(!request.equals("GET") && !request.equals("POST") ) {
            System.out.println("Incorrect request");
            return;
        }

        if ( id < 1){
            System.out.println("Incorrect id");
            return;
        }
        switch(request){
            case "GET":
                myHttpClient.doGet(id);
                break;
            case  "POST":
                myHttpClient.doPost(id);
                break;

        }


//        MyURLConnection myURLConnection = new MyURLConnection();
//        myURLConnection.doGet(1);
    }

}

package com.haiduk;

import com.haiduk.InputValidationService.InputValidation;
import com.haiduk.WebService.MyHttpClient;
import com.haiduk.WebService.MyURLConnection;

public class MainApp {
    public static void main(String[] args) {

        String request = args[0];
        int id = Integer.parseInt(args[1]);
        InputValidation inputValidation = new InputValidation();
        inputValidation.input(args,request,id);
        MyHttpClient myHttpClient = new MyHttpClient();

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

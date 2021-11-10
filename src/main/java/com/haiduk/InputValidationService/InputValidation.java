package com.haiduk.InputValidationService;

public class InputValidation {
    public void input(String [] arr, String str, int id ){
        if(arr.length> 2 ){
            System.out.println("Incorrect Data");
            System.exit(0);
        }

        if(!str.equals("GET") && !str.equals("POST") ) {
            System.out.println("Incorrect request");
            System.exit(0);
        }
        if ( id < 1){
            System.out.println("Incorrect id");
            System.exit(0);
        }
    }

}

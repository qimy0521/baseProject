package com.gcx.api;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        try {
            Process pwd = Runtime.getRuntime().exec("ls -l");
            try {
                String n="";
                byte a[] = new byte[100];
                int i = pwd.waitFor();
                InputStream inputStream = pwd.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                while ( (n=bufferedReader.readLine()) != null){
                    System.out.println(n);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

package Proxy;

import java.io.*;

public class Yrok_5_1 {
    public static void main(String[] args) {

        /*File file = new File("proxy.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        String IP="";
        String port="";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("proxy.txt"));
            String Line;//переменная для считывания файла
            while ((Line = br.readLine()) != null) {
                //System.out.println(Line);
                String []  newLine = Line.split("\\s+");
                for (int i = 0; i <newLine.length ; i++) {
                    newLine[i]= newLine[i].replaceAll("[^\\w]"," ");

                    IP = newLine[0];
                    port = newLine[1];
                } System.out.println(" IP: "+ IP + "  " +" port: "+ port);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






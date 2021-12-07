package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    public static void writeToFile(String[] data){
        try {
            FileWriter writer = new FileWriter("file.txt");
            for(String i: data){
                writer.write(i + "\r\n");
            }
            writer.close();
            //System.out.println("Escrito");
        }
        catch (Exception e){

        }
    }


    public static String[] readData(){
        String[] data = new String[10];
        try {
            File obj = new File("file.txt");
            Scanner reader = new Scanner(obj);

            int n = 0;
            while (reader.hasNextLine()){
                String info = reader.nextLine();
                data[n] = info;
                n++;
            }
            reader.close();
        }
        catch (Exception e){

        }
        return data;
    }

}

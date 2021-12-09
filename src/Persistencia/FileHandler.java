package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
/**
 * La clase FileHandler es el manejador de archivos
 * para el juego
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
 */

public class FileHandler {
    /**
     * Este metodo escribe en el archivo que se crea
     * @param data, es una lista de cadenas
     */
    public static void writeToFile(String[] data){
        try {
            FileWriter writer = new FileWriter("records.txt");
            for(String i: data){
                writer.write(i + "\r\n");
            }
            writer.close();
            //System.out.println("Escrito");
        }
        catch (Exception e){

        }
    }

    /**
     * Este metodo retorna la lista de cadenas de
     * los records de los jugadores
     * @return data, la lista de cadenas
     */
    public static String[] readData(){
        String[] data = new String[10];
        try {
            File obj = new File("records.txt");
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

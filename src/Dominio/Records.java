package Dominio;

import Persistencia.FileHandler;
import Presentacion.Tetris;
/**
 * La clase Records es la encargada de mostrar
 * los matyores puntajes obtenidos en el juego
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 09, 2021)
 */

public class Records {
    private static Records record;
    private int[] scores;
    private String[] data;
    private String[] nickNames;
    private Tetris tetris;
    private int currentScore;
    private String currentNickname;

    public static Records getRecord(Tetris tetris){
        if (record == null) record = new Records(tetris);
        return record;
    }

    /*
     * Este metodo crea el objeto tetris para
     * obtener informacion de este
     * @param tetris, es el JDialog del juego
     */
    private Records(Tetris tetris){
        this.tetris = tetris;
    }
//    public static void main(String[] args){
//        extractData();
//        orderData();
//        showData();
//    }

    /*
     * Este metodo se encarga de mostrar los datos
     */
    private void showData(){
        String[] info = convertData();
        for (String i: info){
            System.out.println(i);
        }
        FileHandler.writeToFile(info);
    }

    /**
     * Este metodo consigue los puntajes y apodos de los jugadores
     */
    public void getScores(){
        try{
            extractData();
            currentNickname = tetris.getnickname();
            currentScore = tetris.getScore();
            nickNames[0] = currentNickname;
            scores[0] = currentScore;
            orderData();
            convertData();
            showData();
        }
        catch (TetrisException e){

        }
    }

    /**
     *
     * @return True or False
     */
    public boolean isWinner(){
        if(scores[data.length] == currentScore) return true;
        return false;
    }

    /**
     * Este metodo se encarga de enviar los datos al amnejador de archivos
     */
    private void retrieveScoresFromFile(){
        data = FileHandler.readData();
    }

    /*
     * Este metodo se encarga de extraer los datos
     */
    private void extractData(){
        retrieveScoresFromFile();
        scores = new int[data.length];
        nickNames = new String[data.length];
        String[] partes;
        for (int i = 0; i < data.length; i++){
            partes = data[i].split(" ");
            String nickname = partes[0];
            String score = partes[1];
            int iscore = Integer.parseInt(score);
            scores[i] = iscore;
            nickNames[i] = nickname;
        }
    }

    /*
     * Este metodo se encarga de ordenar los datos
     */
    private void orderData(){
        for(int i = 0; i < data.length; i++){
            int index = i;
            for (int j = i + 1; j < data.length; j++){
                if (scores[j] < scores[index]){
                    index = j;
                }
            }
            int smallerNumber = scores[index];
            String nickNamescoresmaller = nickNames[index];
            scores[index] = scores[i];
            nickNames[index] = nickNames[i];
            scores[i] = smallerNumber;
            nickNames[i] = nickNamescoresmaller;
        }
    }

    /*
     * Este metodo es el encargado de convertir los datos
     * de una lista a tipo cadena
     * @return
     */
    private String[] convertData(){
        String[] datos = new String[data.length];
        for (int i = 0; i < data.length; i++){
            datos[i] = toString(scores[i],nickNames[i]);
        }
        return datos;
    }

    /*
     *
     * @param score, es el puntaje conseguido por el jugador
     * @param nickname, es el apodo del jugador
     * @return datos, es la cadena con el puntaje del jugador y su apodo
     */
    private static String toString(int score, String nickname){
        String datos = nickname + " → " + score;
        return datos;
    }


}

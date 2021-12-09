package Dominio;

import Persistencia.FileHandler;
import Presentacion.Tetris;


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
    private Records(Tetris tetris){
        this.tetris = tetris;
    }
//    public static void main(String[] args){
//        extractData();
//        orderData();
//        showData();
//    }

    private void showData(){
        String[] info = convertData();
        for (String i: info){
            System.out.println(i);
        }
        FileHandler.writeToFile(info);
    }


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

    public boolean isWinner(){
        if(scores[data.length] == currentScore) return true;
        return false;
    }

    private void retrieveScoresFromFile(){
        data = FileHandler.readData();
    }

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

    private String[] convertData(){
        String[] datos = new String[data.length];
        for (int i = 0; i < data.length; i++){
            datos[i] = toString(scores[i],nickNames[i]);
        }
        return datos;
    }


    private static String toString(int score, String nickname){
        String datos = nickname + " " + score;
        return datos;
    }


}

package Dominio;

import Persistencia.FileHandler;
import Presentacion.Tetris;

import java.io.File;

public class Records {
    private static Records record;
    private int currentScore;
    private String currentNickname;
    private Tetris tetris;
    private String[] data;
    private String nickNames[];
    private int[] scores;

    public static Records getRecord(Tetris tetris){
        if (record == null) record = new Records(tetris);
        return record;
    }

    private Records(Tetris tetris){
        this.tetris = tetris;
    }
    public void getScores(){
        try {
            currentScore = tetris.getScore();
            currentNickname = tetris.getnickname();
        }
        catch (TetrisException e){

        }
    }
    public void retrieveScoresFromFile(){
        data = FileHandler.readData();
    }

    private void extractData(){
        retrieveScoresFromFile();
        scores = new int[data.length];
        nickNames = new String[data.length];
        String[] partes = {"",""};

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

    public void writeDatatoFile(String[] data){
        FileHandler.writeToFile(data);
    }

    private String toString(int score, String nickname){
        String datos = nickname + " " + score;
        return datos;
    }

    public String toString(){
        String datos = currentNickname + " " + currentScore;
        return datos;
    }
}

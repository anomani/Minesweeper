package org.cis1200.minesweeper;
import java.io.*;
public class FileIO {

    public void writeGameStateToFile(Board b, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(b.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Board readGameStateFromFile(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String gameStateString = bufferedReader.readLine();
            bufferedReader.close();
            return new Board();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

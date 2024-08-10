import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Esercizio1{
    public static void main(String[] args) {
        int[][] oggetto1 = lettoreParentChild(args[0]);

        

    }


public static int[][] lettoreParentChild(String path){
        
         List<int[]> coppiaList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) { //fintanto esiste una linea non nulla la legge
                String[] elementi = line.split(","); //la separa attendendosi "," come separatore
                    try {
                        int[] paio = new int[2]; //da descrizione esercizio
                        paio[0] = Integer.parseInt(elementi[0].trim()); //tolgo spazi e convergo a int
                        paio[1] = Integer.parseInt(elementi[1].trim());
                        coppiaList.add(paio);
                    } catch (NumberFormatException e) {
                        System.err.println("Non riesce a convertire a int");
                    }
                
            }
        } catch (IOException e) {
            System.out.println("Problema input/output");
            e.printStackTrace();
        }

        return coppiaList.toArray(new int[coppiaList.size()][]);
    }
    ///TODO:
    /*
    a) crea metodo per lettura file nested list 
        valuta se sensato fare un confronto durante la lettura invece che leggere e confrontare 
    
    
    */ 

}
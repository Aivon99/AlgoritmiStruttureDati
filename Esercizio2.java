import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
class Nodo {                /// non tutti i prefissi sono condivisi da tutte le lettere Es 00 permette B,C,E,H ma preclude D --> non ha senso controllare sempre tutti ---> faccio un albero 

    Map<Character, Nodo> children = new HashMap<>();
    String lettera = null; 
}

class Albero {
    Nodo radice = new Nodo();
    

    public void inserisci(String code, String lettera){   //Inserichi codice e relativa lettera, la lettera viene posta come foglia nel albero
        Nodo nodo = radice;
        for (char c : code.toCharArray()) { //Il codice binario è dato come stringa, viene convertito a char[], e aggiunto all'albero 
            nodo.children.putIfAbsent(c, new Nodo()); //qualora non esistesse già, lega al nodo un nuovo nodo corrispondente al valore c
            nodo = nodo.children.get(c); //in ogni caso prosegue prendendo tra i figli del nodo quello corrispondente al char c preso in esame 
        }
        nodo.lettera = lettera; //alla fine del loop (quindi quando il codice binario è finito) aggiunge valore al parametro lettera
    }

    public Nodo getRadice() {
        return radice;
    }
}

public class Esercizio2 {
    public static void main(String[] args) {
        String[] codici = {"0","00","001","010","0010","0100","0110","0001"};
        String[] lettere = {"A","B","C","D","E","F","G","H"};

        
        // AggiungO lettere
        Albero alberoLettere = new Albero();
        for (int i = 0; i < codici.length; i++) {
            alberoLettere.inserisci(codici[i], lettere[i]);
        }
        
        String input = letturaFile(args[0]);


        List<String> risultati = Decodifica(input, alberoLettere);
        if(risultati == null){
            System.out.println(0); }
        else{ 
            System.out.println(risultati.size());
            for (String risultato : risultati) {
                System.out.println(risultato);
            }}
    }
    
    public static List<String> Decodifica(String input, Albero alberoLettere) {
    int n = input.length();
   if(n == 0){
        return null;
    }
    else{
    List<List<String>> dp = new ArrayList<>(n + 1);

    for (int i = 0; i <= n; i++) {
        dp.add(new ArrayList<>());
    }
    dp.get(0).add(""); // caso base >> una stringa di lunghezza nulla non ha possibili interpretazioni
   

    for (int i = 0; i < n; i++) {  ///Attraverso la stringa 
        if (dp.get(i).isEmpty()) continue; 
        Nodo node = alberoLettere.getRadice();
        for (int j = i; j < n; j++) {
            char c = input.charAt(j);
            if (!node.children.containsKey(c)) break;

            node = node.children.get(c);
            if (node.lettera != null) {
                

                for (String decoding : dp.get(i)) {
                    dp.get(j + 1).add(decoding + node.lettera);
            System.out.println(decoding +"  "+ node.lettera +"--"+ "outer round "+ i+" inner round " +j );
                }
            }
        }
    }
    
     return dp.get(n); 
        }
    }
    public static String letturaFile(String path){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String contenuto = br.readLine();
            br.close();
        return contenuto;}
        catch(FileNotFoundException e){
            System.out.println("Non riesce a trovare il file della Stringa");
        }
        catch(IOException ioE){
            System.out.println("IOException, stack segue");
            ioE.printStackTrace();
        }
        return null;
    }

}

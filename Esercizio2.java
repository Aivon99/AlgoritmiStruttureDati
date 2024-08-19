import java.util.*;

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

        //TODO:  mettere file e lettura, più veloce per prove
        String input = "00100";

        // AggiungO lettere
        Albero alberoLettere = new Albero();
        for (int i = 0; i < codici.length; i++) {
            alberoLettere.inserisci(codici[i], lettere[i]);
        }

        String[] risultati = {"ABA"};
        System.out.println(risultati.length);
        for (String risultato : risultati) {
            System.out.println(risultato);
        }
    }

}

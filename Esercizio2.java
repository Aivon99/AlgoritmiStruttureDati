/*
 * After some tries time complexity came outtoo high, next will try a different approach with dinamic programming
 Also issues with reused variables etc, code is horrible, comments missing, names are ugly
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Iterator;


class Decodificatore{
    private  StringBuilder combinazione;
    private Queue<Character> arrayCod;

    public Decodificatore(){ //Con campi vuoti
        arrayCod = new LinkedList<>();
        this.combinazione = new StringBuilder();
    }

    public Decodificatore( char[] Input){
        arrayCod = new LinkedList<>(); //Lo uso come FIFO, aggiungo tutti i valori, in seguito man mano che il codice binario viene interpretato li rimuovo 
        for(char c : Input){
            this.arrayCod.add(c);
        }
        this.combinazione = new StringBuilder(); ///Man mano che interpreto il codice aggiungo le lettere allo StringBuilder;
        
    }
    public Decodificatore(Decodificatore other) {
        this.combinazione = new StringBuilder(other.combinazione);
        this.arrayCod = new LinkedList<>(other.arrayCod);
    }
    public void setCombinazione(String lettere){
        this.combinazione.setLength(0); //Svuoto il Stringbuilder precedente
        this.combinazione.append(lettere); //Aggiungo le lettere 
    }
    public void setCodice(char[] codice){
        arrayCod.clear(); //Svuoto la coda
        for(int i = 0; i < codice.length; i++){
            arrayCod.add(codice[i]); //Aggiungo nuovo codice
        }

    }
    public char[] getCod(){ 
        Character[] arrayCharacter = arrayCod.toArray(new Character[0]);

            char[] charArray = new char[arrayCharacter.length];
        for (int i = 0; i < arrayCharacter.length; i++) {
            charArray[i] = arrayCharacter[i]; // Unboxing del
        }    
        return charArray;
    }
    public void printCombinazione(){
        System.out.println();
       System.out.print(combinazione.toString());
    }
    public void addLettera(char lettera){
        combinazione.append(lettera);
    }
    public String getLetter(){
        return  combinazione.toString();
    }
    public void rimCodice(int n){   //metodo che dato un elemento char[] "es", elimina i primi es.length char dal codice   
    for(int i = 0; i < n; i++){
        this.arrayCod.poll();
    }    
}
    public boolean match(char[] lettera){ //metodo che verifica se i caratteri in cima alla coda corrispondono a quelli del codice della lettera
        Iterator<Character> iteratore = this.arrayCod.iterator(); //Definisco un iteratore per parsare la coda senza modificarla
        for(int i = 0; i < lettera.length;i++){
            if(iteratore.hasNext()){
                if(iteratore.next() != lettera[i] ){
                    return false;
                }
            }
            else return false; //Qualora la parola finisse prima della lettera ritorno falso. 
        }
        return true;
    }
    public int getLunghezzaCodice(){
        return this.arrayCod.size();
    } 
}

public class Esercizio2 {
    public static void main(String[] args) {
        char[][] Lettere = {
            {'0'}, {'0', '0'}, {'0', '0', '1'}, {'0', '1', '0'},
            {'0', '0', '1', '0'}, {'0', '1', '0', '0'}, {'0', '1', '1', '0'}, {'0', '0', '0', '1'}
        };
        char[] lettereChar = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        
        char[] arrayCodice = leggiFile("args[0]"); //leggo il file con gli input
        
        Queue<Decodificatore> Albero = new LinkedList<>();  
            Decodificatore origine = new Decodificatore(arrayCodice);
        Albero.add(origine); //Inizializzo la coda
        ArrayList<String> Iterazioni = new ArrayList<>(); //per tenere le iterazioni valide
        
        Decodificatore elemento = Albero.poll(); 
        do{
            for(int i = 0; i < 7; i++){
                if(elemento.match(Lettere[i])){
                  
                 int n = Lettere[i].length;
                 Decodificatore newElemento = new Decodificatore(elemento);
                    newElemento.rimCodice(Lettere[i].length);
                    newElemento.addLettera(lettereChar[i]);


                    if(newElemento.getLunghezzaCodice() == 0){ //Se non rimangono 0 o 1 aggiungo la iterazione al ArrayList dei risolto 
                        Iterazioni.add(newElemento.getLetter());
                    }
                    else{   ///se ne rimangono lo aggiungo alla coda
                        Albero.add(newElemento);
                    }
                    
                }
            }
            elemento = Albero.poll(); //Fetch prossimo elemento
        }while(elemento != null);         //Quando l'elemento seguente è null esco dal ciclo 

        int n =Iterazioni.size();

        System.out.println(n);  //Stampo il numero di combinazioni trovate
        for(int i = 0; i < n; i++){
            System.out.println(Iterazioni.get(i)); //Stampo le combinazioni 
        }
    }
    public static char[] leggiFile(String path){
        char[] arrayCodice = {'0','0'};

        return arrayCodice;
        //TODO: scrivere metodo lettura file
    }
}    

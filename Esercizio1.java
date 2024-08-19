import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Nodo{
     int id;
     Map<Integer, Nodo> figli; //Non li metto privati per renderne i mettodi accessibili nelle classi statiche --> mi risparmio la scrittura di metodi addFiglio e simili. 
    
     public Nodo(int id){
        this.id = id;
        this.figli= new HashMap<>();
    }
}


public class Esercizio1{
    public static void main(String[] args) {
       Nodo daParentChild = ParentChild(args[0]);
       Nodo daAnnidata = listaAnnidata(args[1]);
        

    }


public static Nodo ParentChild(String path){
        Map<Integer, Nodo> nodi = new HashMap<>(); 
        ArrayList<Integer> idFigli = new ArrayList<>(); //Non mi era chiaro (non specificato nel testo) se il nodo radice fosse quello con 
        //valore più basso, 1 o 0, quindi tengo registro dei nodi identificati come figli e ritorno il nodo non incluso in questa lista


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String riga;
                        
            while ((riga = br.readLine()) != null) { //operiamo fintanto esiste una linea non nulla 
                
                
                    riga = riga.replaceAll("\\s", ""); //Per sicurezza pulisco da spazi
                    String[] stringhe = riga.split(","); // "," come separatore, splitta in array stringhe 

                    int idPadre = Integer.parseInt(stringhe[0]);
                    int idFiglio = Integer.parseInt(stringhe[1]);
                    
                    idFigli.add(idFiglio); //aggiungo id a lista nodi figli di altri nodi 8alternativamente si poteva utilizzare una SortedList 

                    nodi.putIfAbsent(idPadre, new Nodo(idPadre));
                    nodi.putIfAbsent(idFiglio,new  Nodo(idFiglio)); //aggiungo i due nodi alla hashmap e aggiungo il nodo figlio ai figli del padre
                        Nodo padre = nodi.get(idPadre);
                        Nodo figlio = nodi.get(idFiglio); //Ricavo gli stessi oggetti 
                    padre.figli.putIfAbsent(idFiglio, figlio);

            }
            br.close();
            
            for (Integer id : nodi.keySet()) { //Uso un SortList perchè mi aspetto che in qualche modo un numro basso sia correlato ad essere radice
                if (!idFigli.contains(id)) {
                    return nodi.get(id);    
                }
            }
           //alternativamente (qualora la radice fosse sempre il numero 1 o 0) usiamo .get(1/0)
 
         } catch (IOException e) {
            System.out.println("Problema input/output");
            e.printStackTrace();
        }

        return null; //fuori dal try catch
    }

    public static Nodo listaAnnidata(String path){
        try{
            Map<Integer, Nodo> nodi = new HashMap<>(); //preventivo 

        BufferedReader br = new BufferedReader(new FileReader(path)); //leggo la riga dal file
        String riga = br.readLine();
            br.close();

        char[] listaAnnidata = riga.toCharArray();
        if(listaAnnidata[0] != '[') return null;   
            
        //secondo me si può usare una funz ricorsiva






        }
        catch(IOException e){
            System.out.println("Problema input/output");
                e.printStackTrace();
        }
        return null;
    }
    
    
}

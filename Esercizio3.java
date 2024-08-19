/*questo sarà divertente
 * goditelo
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Approccio: crei grafo, BFS,, NOTA: se la variabile attesa ai semafori è definita in un unico oggetto random o no cambia, segnale 
class Nodo{
    private int id;
    private Map<Nodo, Double> archi;     
    
    public Nodo(int id){
        this.id = id;
        this.archi = new HashMap<>();
        
    }
    public void addArco(Nodo nodoArrivo, double peso){
            this.archi.putIfAbsent(nodoArrivo, peso);
        
    }
    public int getId(){
        return this.id;
    }  
    public double getDist(Nodo arrivo){  
        return this.archi.get(arrivo);
    }
    public Set<Nodo> getNodiVicini(){

        return this.archi.keySet();
    }
    public double attesa1(int i, double t){
        return 5.0; 
    }
    public double attesa2(int i, double t){ ////Ragiona su come far si che a parità di input ci sia parità di output
        Random rnd = new Random(1144818);
        return rnd.nextDouble(); 

    }
}

class Grafo{
    private Nodo[] listaNodi;

    public Grafo(int nNodi){
        this.listaNodi = new Nodo[nNodi];
        for(int i = 0; i < nNodi; i++){
            this.listaNodi[i] = new Nodo(i);
     }

    }
    public void addArco(int a, int b, double w){  //Aggiungiamo archi con pesi
        this.listaNodi[a].addArco(listaNodi[b], w); 
        this.listaNodi[b].addArco(listaNodi[a], w); //in entrambe le direzioni
    }
    public int getNumeroNodi(){
        return this.listaNodi.length;
    }
    /*
    public double getDist(int id){ //Metodo per ottenere le distanze tra un nodo e i nodi a lui collegati
        
        return this.listaNodi[id].;
    }
         */
}
public class Esercizio3 {
    public static void main(String[] args) {
       double[][] dati = leggiFile(args[0]);
        int nNodi = (int) dati[0][0];
        int nArchi = (int) dati[1][0];
       Grafo grafo = new Grafo(nNodi);
       
       
    }
    
    public static void BFS1(Grafo grafo){
        String catena = "0";
        double tempo = 0.01;
        
        Queue<Nodo> coda = new LinkedList<>();

        boolean[] visitati = new boolean[grafo.getNumeroNodi()];
        visitati[0] = true;
        
        for(int i = 1; i < grafo.getNumeroNodi(); i++){
            visitati[i] = false;
        }
        while(code.hasNext()){
            Nodo nodo = coda.poll();
        }



        System.out.println("Caso 1:");
        System.out.println(tempo);
        System.out.println(catena);
    }
    public static void BFS2(Grafo partenza){
        String catena = "0";
        double tempo = 0.01;
        System.out.println("Caso 2:");
        System.out.println(tempo);
        System.out.println(catena);
    }


    public static double[][] leggiFile(String path){ //Nel testo esempio la distanza/secondi paiono essere di tipo doule ---> calcolo in double il metodo poi faccio casting nelle parti necessarie
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            int nNodi = Integer.parseInt(br.readLine());
            int nArchi = Integer.parseInt(br.readLine()); // TODO:Manca gestione errore NumberFormatException
            String riga;
            double[][] matr = new double[2+nArchi][3];
            matr[0][0] = (double) nNodi;
            matr[1][0] = (double) nArchi;
 
                Scanner sc;
                double nodo1;
                double nodo2; //Elemeenti per leggere il file
                double secondi; 

             for(int i = 0; i < nArchi; i++){ //Il resto del file dovrebbe aver numero di righe uguale al numero di archi
               riga  =   br.readLine();
      
                    sc = new Scanner(riga);
        
               nodo1 = (double) sc.nextInt();
               nodo2 = (double) sc.nextInt();
               secondi = sc.nextDouble(); 
                
                matr[i+2][1] = nodo1;
                matr[i+2][2] = nodo2;
                matr[i+2][3] = secondi;
                br.close();
            }
                
            return matr; //tieni pagina 
            }
        
        catch(IOException e){
            System.out.println(" "); //messaggio di sfogo e disperazione 
            e.printStackTrace();    
        }
        return null;
    }
   }


/**
 *@autor Eva Matabosch Vidal
 *La classe Graph representa un graf no dirigit amb les seves operacions bàsiques
 *Cada aresta és bidireccional, és a dir, es pot anar de n1->n2 i de n2->n1
 */
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Aresta> arestes;//Llista d'arestes
    /**
    *Constructor de la classe Graph.
    *Pre: cert
    *Post: s'inicialitza la llista com una llista buida
    */
    public Graph() {
        arestes = new ArrayList<>();
    }
    /**
     *Afegeix una aresta al graf
     * En un graf no dirigit, cada aresta es representa com a dues direccions, pot ser bidireccional
     * Pre: els paràmetres n1 i n2 són identificadors dels nodes vàlids i cost és el pes de la connexió
     * Post: s'afegeixen dues instàncies de cada classe per n1->n2 i n2->n1
     */

    public void afegirAresta(int n1, int n2, int cost) {
        arestes.add(new Aresta(n1, n2, cost)); //Aresta de n1 a n2
        arestes.add(new Aresta(n2, n1, cost)); //Aresta de n2 a n1
    }

    /**
     *Obté una llista de nodes del graf
     *Pre: la representació interna del graf té instàncies vàlides
     *Post: retorna una llista amb els nodes sense duplicats trobats a les arestes

    */

    public List<Integer> obtenirNodes() {
        List<Integer> nodes = new ArrayList<>(); //Crear una array d'una llista
        for (Aresta aresta : arestes) {
            if (!nodes.contains(aresta.obtenirNode1())) {//Mirem si està present el primer node, sinó l'afegeixo
                nodes.add(aresta.obtenirNode1());
            }
            if (!nodes.contains(aresta.obtenirNode2())) {//Mirem si està present el segon node, sinó l'afegeixo
                nodes.add(aresta.obtenirNode2());
            }
        }
        return nodes;
    }

    /**
     *Obtinc una llista d'arestes que surten d'un node determinat
     *Pre: el paràmetre és un enter que representa un node vàlid
     *Post: retorna una llista amb les arestes que tenen el node d'origen corresponent
     */
    public List<Aresta> obtenirArestesDelNode(int node) {
        List<Aresta> result = new ArrayList<>();
        for (Aresta aresta : arestes) {
            if (aresta.obtenirNode1() == node) {//Per cada aresta comprovar si el node inicial és igual el que especifiquem
                result.add(aresta);//Afegir a l'aresta
            }
        }
        return result;
    }

    /**
     *Cada classe interna static representa una aresta del graf que guarda informació sobre la connexió entre dos nodes del graf
     */
    public static class Aresta {
        //Guardo node1, node2 i cost
        private int node1;
        private int node2;
        private int cost;

       /**
        *Constructor d'aresta
        *Pre: els paràmetres node1 i node2 són enters que representen nodes vàlids i cost representa el pes de la connexió
        *Post: crea una instància amb valors passats
        */
        public Aresta(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
        /**
         *Retorna el primer node origen de l'aresta
         *Pre: cert
         *Post retorna el valor de node1
         */

        public int obtenirNode1() {
            return node1;
        }
        /**
         *Retorna el segon node de l'aresta (destí)
         *Pre: cert
         *Post: retorna el valor de node2
         */
        public int obtenirNode2() {
            return node2;
        }

        /**
         *Retorna el cost l'aresta
         *Pre: cert
         *Post: retorna el cost o pes de l'aresta
         */
        public int obtenirCost() {
            return cost;
        }

        
    }
}

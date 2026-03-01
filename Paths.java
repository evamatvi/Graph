/**
 *@autor Eva Matabosch Vidal
 *La classe abstracta permet tenir operacions per trobar camins dins d'un graf
 *Defineix mètodes statics per calcular els camins possibles tenint en compte el màxim de passos o opcionalment un límit de cost
 */

import java.util.ArrayList;
import java.util.List;

public abstract class Paths {
    /**
     *Calcula i retorna una llista de camins que parteixen d'un node d'origen i que compleixen uns passos màxims i un cost màxim opcional
     *Pre: els paràmetres d'entrada són vàlids i existeixen
     *Post: retorna una subllista que és una següència de nodes que compleixen amb les restriccions de passos i cost (opcional)
     */
    public static List<List<Integer>> caminsFinsPassos(Graph g, int origen, int maxPassos, Integer maxCost) {
        List<List<Graph.Aresta>> arestes = new ArrayList<>();
        List<Graph.Aresta> camiActual = new ArrayList<>();
        trobarCamins(g, origen, maxPassos, maxCost, arestes, camiActual, 0, 0);//invoca el mètode recursiu
        List<List<Integer>> result = new ArrayList<>();
        for (List<Graph.Aresta> caminsArestes : arestes) {//va extraient els nodes de la llista
            result.add(extreureNodes(caminsArestes));
        }
        return result;
    }

    /**
     *Mètode recursiu que busca els camins possibles a partir d'un node
     *Pre: els paràmetres d'entrada són vàlids i existeixen
     *Post: s'afegeixen les següències d'arestes  que representen camins vàlids segons els límits de passos i cost
     */

    private static void trobarCamins(Graph g, int nodeActual, int maxPassos, Integer maxCost,
                                     List<List<Graph.Aresta>> camins,
                                     List<Graph.Aresta> camiActual,
                                     int transicions, int costAcumulat) {
        boolean afegirCami = transicions >= 1;
        boolean continuarExplorant = transicions < maxPassos;

        if (afegirCami) {
            camins.add(new ArrayList<>(camiActual));
        }

        if (continuarExplorant) {
            List<Graph.Aresta> arestes = g.obtenirArestesDelNode(nodeActual);
            for (Graph.Aresta aresta : arestes) {
                int nouCost = costAcumulat + aresta.obtenirCost();
                boolean costAcceptable = maxCost == null || nouCost <= maxCost;

                if (costAcceptable) {
                    camiActual.add(aresta);
                    trobarCamins(g, aresta.obtenirNode2(), maxPassos, maxCost, camins, camiActual, transicions + 1, nouCost);
                    camiActual.remove(camiActual.size() - 1);
                }
            }
        }
    }

    /**
     *Extreu els nodes de la llista
     *Pre: llista d'arestes que representa un camí vàlid
     *Post: obté una llista que extreu els nodes
     */

    private static List<Integer> extreureNodes(List<Graph.Aresta> caminsArestes) {
        List<Integer> nodes = new ArrayList<>();
        if (caminsArestes.isEmpty()) {
            return nodes;
        }
        // El primer node és l'origen de la primera aresta.
        nodes.add(caminsArestes.get(0).obtenirNode1());
        for (Graph.Aresta aresta : caminsArestes) {
            nodes.add(aresta.obtenirNode2());
        }
        return nodes;
    }
}

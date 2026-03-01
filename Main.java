/**
 *@autor Eva Matabosch Vidal
 *Programa que s'ocupa de llegir el fitxer del graf, crear el seu objecte i cridar les operacions de Paths
 *mostrant-ho per pantalla
 */

import java.io.File;
import java.util.Scanner;
import java.util.List;


public class Main {
    /**
     *Punt d'entrada del programa, valida el nombre d'arguments correctes, sinó crida executarPrograma
     *Pre: els arguments passats són entre 3 i 4
     *Post: si el nombre d'arguments no són correctes es mostra les instruccions del programa, sinó es crida executarPrograma
     */
    public static void main(String[] args) {
        if (args.length < 3 || args.length > 4) {
            instruccionsPrograma();
            return;
        }
        executarPrograma(args);
    }
    /**
     *LLegeix el fitxer, valida el node d'origen, calcula els camins i imprimeix el resultat
     *Pre: els arguments passats són entre 3 i 4
     *Post: es mostren els camins de Path per pantalla
     */
    private static void executarPrograma(String[] args) {
        String nomFitxer = args[0];
        int origen = Integer.parseInt(args[1]);
        int maxPassos = Integer.parseInt(args[2]);
        Integer maxCost = (args.length == 4) ? Integer.parseInt(args[3]) : null; //Si no es passa el cost, es posa a null

        // Carregar el graf des del fitxer.
        Graph graf = llegirGraf(nomFitxer);
        if (graf == null) {
            System.out.println("Error llegint el fitxer.");
            return;
        }

        // Validar que el node d'origen existeix.
        List<Integer> nodes = graf.obtenirNodes();
        if (!nodes.contains(origen)) {
            instruccionsPrograma();
            return;
        }
        //Si els passos (transicions) són 0 o menors es mostra l'ajuda
        if (maxPassos <= 0) {
            instruccionsPrograma();
            return;
        }
        // Cridar la funció del mòdul Paths per obtenir els camins.
        List<List<Integer>> camins = Paths.caminsFinsPassos(graf, origen, maxPassos, maxCost);

        if (camins.isEmpty()) {
            instruccionsPrograma();
            return; // Si no hi ha camins, mostra l'ajuda
        }
        // Mostrar el resultat per pantalla.
        System.out.print("Paths from " + origen + " of length between 1 and " + maxPassos);
        if (maxCost != null) {
            System.out.print(", and cost smaller or equal to " + maxCost);
        }
        System.out.println(":");
        for (List<Integer> cami : camins) {
            System.out.println(cami);
        }
    }
    /**
     *Mètode per llegir el graf d'un fitxer
     *Pre: el fitxer existeixi
     *Post: es llegeix el fitxer
     */
    private static Graph llegirGraf(String nomFitxer) {
        Graph graf = null;
        File fitxer = new File(nomFitxer);
        boolean fitxerValid = fitxer.exists();

        if (!fitxerValid) {
            System.out.println("Error: No s'ha trobat el fitxer '" + nomFitxer + "'.");
        } else {
            graf = new Graph();
            try {
                Scanner scanner = new Scanner(fitxer);
                processarLinies(scanner, graf);
                scanner.close();
            } catch (Exception e) {
                System.out.println("Error llegint el fitxer: " + e.getMessage());
                graf = null;
            }
        }

        return graf;
    }

    /**
     *Mètode per processar el graf d'un fitxer
     *Pre: el fitxer existeixi i s'hagi llegit
     *Post: es processi les línees del fitxer
     */
    private static void processarLinies(Scanner scanner, Graph graf) {
        while (scanner.hasNextLine()) {
            String linia = scanner.nextLine().trim();
            if (!(linia.isEmpty() || linia.startsWith("c") || linia.startsWith("p"))) {
                if (linia.startsWith("a")) {
                    String[] parts = linia.split("\\s+");
                    if (parts.length >= 4) {
                        try {
                            int u = Integer.parseInt(parts[1]);
                            int v = Integer.parseInt(parts[2]);
                            int w = Integer.parseInt(parts[3]);
                            graf.afegirAresta(u, v, w);
                        } catch (NumberFormatException e) {
                            System.out.println("Error de format en la línia: " + linia);
                        }
                    }
                }
            }
        }
    }


    /**
     *Quan es crida el programa sense paràmetres o malament cal mostrar les instruccions del programa
     *Pre: cert
     *Post: mostra com s'ha de mostrar el programa
     */
    private static void instruccionsPrograma() {
        System.out.println("prompt> java Main");
        System.out.println("Lists all paths in a graph from a given source node with a maximum length and cost.");
        System.out.println("Usage: java Main <file> <source> <length> [<cost>]");
        System.out.println("Where:");
        System.out.println("    <file> is the file containing the graph, in DIMACS .gr format (http://www.diag.uniroma1.it/challenge9/format.shtml#graph)");
        System.out.println("    <source> is the source node");
        System.out.println("    <length> is the maximum length of the path");
        System.out.println("    <cost> is the maximum cost of the path");
        System.out.println("Example: java Main graph.gr 1 10 100");
    }
}

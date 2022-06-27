import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Kruskal {

    public void solve_kruskal(Grafo grafo){
        HashSet<List<Float>> a = new HashSet<>();
        HashMap<Float, HashSet<Float>> S = new HashMap();
        for (int i = 1; i < (grafo.qtdVertices()+1); i++ ){
            HashSet<Float> aux = new HashSet<>();
            aux.add((float) i);
            S.put((float) i, aux);
        }
        LinkedList<List<Float>> Elinha = grafo.ordena_peso();
        for (List<Float> uv: Elinha) {
            if (!S.get(uv.get(0)).equals(S.get(uv.get(1)))){
                HashSet<List<Float>> aux = new HashSet<>();
                aux.add(uv);
                a.addAll(aux);
                HashSet<Float> aux1 = S.get(uv.get(0));
                aux1.addAll(S.get(uv.get(1)));
                for (Float i:aux1) {
                    S.put(i, aux1);
                }

            }
        }
        Float soma = 0f;
        String arestas = "";
        for (List<Float> i:a) {
            soma += i.get(2);
            arestas+=" "+i.get(0)+"-"+i.get(1);
        }
        System.out.println("Soma do caminho: "+soma);
        System.out.println("Arestas: "+arestas);
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Grafo g = new Grafo();

        String separator = System.getProperty("file.separator");
        // Lendo arquivo de teste
        // Exemplo retirado de https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/mst.html
        // É o exemplo B sobre arvore de custo minímo e o resultado do problema
        // foi usado para validar nossa solução
        g.lerArquivo(separator+"testes"+separator+"arvore_geradora_minima.txt");
        Kruskal k = new Kruskal();
        k.solve_kruskal(g);

    }
}

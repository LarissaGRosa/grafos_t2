import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CFC {

    void CFC(GrafoDirigido grafo) {
        ReturnOfDFS R = DFS(grafo);
        
    }

    ReturnOfDFS DFS(GrafoDirigido grafo) {
        List<Boolean> C = new ArrayList<>();
        List<Integer> T = new ArrayList<>();
        List<Integer> F = new ArrayList<>();
        List<Integer> A = new ArrayList<>();

        int tempo = 0;
        int quantidadeVertices = grafo.V.size();

        for (int i = 0; i < quantidadeVertices; i++) {
            C.add(false);
            T.add(2147483647);
            F.add(2147483647);
            A.add(null);
        }

        for (int u = 0; u < quantidadeVertices; u++) {
            if (C.get(u) == false) {
                DFSVisit(grafo, u, C, T, A, F, tempo);
            }
        }

        return new ReturnOfDFS(C, T, A, F).getTypes();
    }

    void DFSVisit(GrafoDirigido grafo, int v, List<Boolean> C, List<Integer> T, List<Integer> F, List<Integer> A, int tempo) {
        C.set(v, true);
        tempo = tempo + 1;
        T.set(v, tempo);

        float vFloat = v;
        for (Float u: grafo.vizinhos(vFloat)) {
            int uInt = Math.round(u);
            if (!C.get(uInt)) {
                A.set(uInt, v);
                DFSVisit(grafo, uInt, C, T, A, F, tempo);
            }
        }

        tempo = tempo + 1;
        F.set(v, tempo);

    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        GrafoDirigido grafoDirigido = new GrafoDirigido();

        String separator = System.getProperty("file.separator");
        // Lendo arquivo de teste
        grafoDirigido.lerArquivo(separator+"testes"+separator+"dirigido1.txt");


        CFC algoritmo = new CFC();
    }

}

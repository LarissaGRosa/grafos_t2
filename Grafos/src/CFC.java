import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CFC {

    void CFC(GrafoDirigido grafo) {
        ReturnOfDFS R = DFS(grafo);
        System.out.println(R.getA());

        GrafoDirigido G_T = new GrafoDirigido();
        G_T.V = grafo.V;
        G_T.R = grafo.R;
        G_T.E = grafo.E;

        for (int i = 0; i < G_T.qtdVertices(); i++) {
            List<Float> listaVertice = new ArrayList<>();

            float value1 = grafo.E.get(i).get(0);
            float value2 = grafo.E.get(i).get(1);
            float value3 = grafo.E.get(i).get(2);

            listaVertice.add(value2);
            listaVertice.add(value1);
            listaVertice.add(value3);

            G_T.E.set(i, listaVertice);
        }

        ReturnOfDFS newReturn = DFSAdaptado(G_T);
        System.out.println(newReturn.getA());

    }

    ReturnOfDFS DFSAdaptado(GrafoDirigido grafo) {
        List<Boolean> C = new ArrayList<>();
        List<Integer> T = new ArrayList<>();
        List<Integer> F = new ArrayList<>();
        List<Integer> A = new ArrayList<>();

        int tempo = 0;
        int quantidadeVertices = grafo.qtdVertices();

        for (int i = 0; i < quantidadeVertices; i++) {
            C.add(false);
            T.add(2147483647);
            F.add(2147483647);
            A.add(null);
        }

        for (int u = quantidadeVertices-1; u >= 0 ; u--) {
            if (!C.get(u)) {
                DFSVisit(grafo, u, C, T, F, A, tempo);
            }
        }

        return new ReturnOfDFS(C, T, F, A).getTypes();
    }

    ReturnOfDFS DFS(GrafoDirigido grafo) {
        List<Boolean> C = new ArrayList<>();
        List<Integer> T = new ArrayList<>();
        List<Integer> F = new ArrayList<>();
        List<Integer> A = new ArrayList<>();

        int tempo = 0;
        int quantidadeVertices = grafo.qtdVertices();

        for (int i = 0; i < quantidadeVertices; i++) {
            C.add(false);
            T.add(Integer.MAX_VALUE);
            F.add(Integer.MAX_VALUE);
            A.add(null);
        }

        for (int u = 0; u < quantidadeVertices; u++) {
            if (!C.get(u)) {
                DFSVisit(grafo, u, C, T, F, A, tempo);
            }
        }

        return new ReturnOfDFS(C, T, F, A).getTypes();
    }

    void DFSVisit(GrafoDirigido grafo, int v, List<Boolean> C, List<Integer> T, List<Integer> F, List<Integer> A, int tempo) {
        C.set(v, true);
        tempo = tempo + 1;
        T.set(v, tempo);

        float vFloat = v+1;

        //System.out.println(vFloat);
        //System.out.println(grafo.vizinhos(vFloat));
        List<Float> vizinhos = grafo.vizinhos(vFloat);
        for (Float u: vizinhos) {
            int uInt = Math.round(u) - 1;
            //System.out.println(uInt);
            if (!C.get(uInt)) {
                A.set(uInt, v);
                DFSVisit(grafo, uInt, C, T, F, A, tempo);
            }
        }

        tempo = tempo + 1;
        F.set(v, tempo);

    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        GrafoDirigido grafoDirigido = new GrafoDirigido();

        String separator = System.getProperty("file.separator");
        // Lendo arquivo de teste
        grafoDirigido.lerArquivo(separator+"testes"+separator+"dirigido2.txt");


        CFC algoritmo = new CFC();
        algoritmo.CFC(grafoDirigido);
    }

}

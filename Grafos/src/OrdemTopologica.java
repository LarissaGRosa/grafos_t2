import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrdemTopologica {

    public void dfs_to_topologic_order(GrafoDirigido gd){

        HashMap<Float, Boolean> cvertice = new HashMap<Float, Boolean>();
        HashMap<Float, Float> tvertice = new HashMap<Float, Float>();
        HashMap<Float, Float> fvertice = new HashMap<Float, Float>();
        for (int i = 1; i < (gd.qtdVertices()+1); i++ ){
            cvertice.put((float) i, false);
            tvertice.put((float) i, Float.MAX_VALUE);
            fvertice.put((float) i, Float.MAX_VALUE);
        }
        int tempo = 0;
        LinkedList<Float> O = new LinkedList<Float>();
        for (int i = 1; i < (gd.qtdVertices()+1); i++ ){
            if (!cvertice.get((float)i)){
                O = dfs_visit_ot(gd, (float) i, cvertice, tvertice, fvertice, tempo, O);
            }
        }
        String result = "";
        for (Float k: O.subList(0, O.size()-1)) {
            result = result+gd.rotulo(k.intValue())+"-->";
        }
        result = result + gd.rotulo(O.getLast().intValue());
        System.out.println(result);
    }

    public LinkedList<Float> dfs_visit_ot(GrafoDirigido gd, float v, HashMap<Float, Boolean> c,
                                                  HashMap<Float, Float> t, HashMap<Float, Float> f,
                                                  int tempo, LinkedList<Float> o){
        c.put(v, true);
        tempo = tempo + 1;
        t.put(v, (float)tempo);
        List<Float> vizinhos = gd.vizinhos(v);
        for (Float i:vizinhos) {
            if (!c.get(i)){
                dfs_visit_ot(gd, i, c, t, f, tempo, o);
            }
        }
        tempo = tempo + 1;
        f.put(v, (float)tempo);
        o.addFirst(v);
        return o;

    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        GrafoDirigido grafoDirigido = new GrafoDirigido();

        String separator = System.getProperty("file.separator");
        // Lendo arquivo de teste
        grafoDirigido.lerArquivo(separator+"testes"+separator+"dirigido1.txt");
        OrdemTopologica ot = new OrdemTopologica();
        ot.dfs_to_topologic_order(grafoDirigido);

    }

}

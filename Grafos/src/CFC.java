import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class CFC {

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        GrafoDirigido grafoDirigido = new GrafoDirigido();

        String separator = System.getProperty("file.separator");
        // Lendo arquivo de teste
        grafoDirigido.lerArquivo(separator+"testes"+separator+"dirigido1.txt");


        CFC algoritmo = new CFC();
    }

}

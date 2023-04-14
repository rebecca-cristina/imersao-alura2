import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class APPFilmes {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão http e guardar uma resposta dentro de uma string e buscar os top 250 filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação) - parciar os dados
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);


        // exibir e manipular os dados

        var geradora = new GeradoraDeFigurinhas();
        
        var diretorio = new File("aula-figurinhas/figurinhas/");
        diretorio.mkdir();

        for (int index = 0; index < 5; index++) {

        var filme = listaDeFilmes.get(index);

        String urlImagem = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeArquivo = "aula-figurinhas/figurinhas/" + titulo + ".png";

        geradora.cria(inputStream, nomeArquivo);

        System.out.println(titulo);
        System.out.print("\n");

        }
    }
}


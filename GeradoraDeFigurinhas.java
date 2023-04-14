import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
// import java.io.FileInputStream;
import java.io.InputStream;
// import java.net.URL;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    // leitura da imagem

    // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
    // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // cria nova imagem em memória com transparencia e com tamanho novo

    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    
    // copiar a imagem original para nova imagem (em memoria)

    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // configurar a fonte
    var fonte = new Font("Impact", Font.BOLD, 80);
    graphics.setColor(Color.MAGENTA);
    graphics.setFont(fonte);

    // escrever uma frase na nova imagem

    String texto = "TOPZERA";
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();
    int posicaoTextoX = (largura - larguraTexto) / 2;
    int posicaoTextoY = novaAltura - 100;

    graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    TextLayout textLayout = new TextLayout(texto, fonte, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(posicaoTextoX, posicaoTextoY);
    graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

    // escrever a nova imagem em um arquivo
    
    // ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
    // public static void main(String[] args) throws Exception {
    //     var geradora = new GeradoraDeFigurinhas();
    //     geradora.cria();
        
    // }
    
}

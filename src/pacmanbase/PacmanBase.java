package pacmanbase;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author viniciusduarte
 */
public class PacmanBase extends base.Jogo {

    //Objeto para armazenar o PNG do pacman.
    BufferedImage pacman, bloco, pilula;

    //Tamanho fixo de cada boneco, em pixels.
    final int TAM = 28;

    //Mapa.
    private ArrayList<String> mapa;

    //Lugares onde o pacman já passou.
    private boolean[][] passei;

    public static void main(String[] args) {
        base.JogoApp.inicia(new PacmanBase());
    }
    private int totalLinhas;
    private int totalColunas;

    /*
     pac_pix_x -> posicao do pacman no eixo x em pixels.
     pac_pix_y -> posicao do pacman no eixo y em pixels.
     pac_map_x -> posicao do pacman no eixo x com base no mapa de caracteres.
     pac_map_y -> posicao do pacman no eixo y com base no mapa de caracteres.
     */
    private int pac_pix_x, pac_pix_y, pac_map_x, pac_map_y;
    private int direcao, quadro, contador_de_atualizacoes;

    public PacmanBase() {
        this.titulo = "Pacman Base";
        contador_de_atualizacoes = 0;

        //Carregando todas as imagens básicas.
        try {
            pacman = ImageIO.read(new File("resources/imagens/packman.png"));
        } catch (IOException ex) {
            System.err.println("Imagem pacman falhou: " + ex);
        }

        try {
            bloco = ImageIO.read(new File("resources/imagens/block.jpg"));
        } catch (IOException ex) {
            System.err.println("Imagem bloco falhou: " + ex);
        }

        try {
            pilula = ImageIO.read(new File("resources/imagens/coin.jpg"));
        } catch (IOException ex) {
            System.err.println("Imagem pilula falhou: " + ex);
        }

        //Leitura do mapa.
        Scanner s;
        try {
            s = new Scanner(new File("resources/mapas/simples_1.txt"));
            mapa = new ArrayList<>();
            while (s.hasNextLine()) {
                mapa.add(s.next());
            }
            s.close();

            totalLinhas = mapa.size();
            totalColunas = mapa.get(0).length();

            System.out.println("Resolução da tela:");
            System.out.println(TAM * totalLinhas + " x " + TAM * totalColunas);

            //Pegar a posição inicial do pacman. Onde está o 0 no mapa.
            int linhaInicial = 0;
            while (!mapa.get(linhaInicial).contains("0")) {
                linhaInicial++;
            }
            pac_pix_y = linhaInicial * TAM;
            pac_pix_x = mapa.get(linhaInicial).indexOf('0') * TAM;
            pac_map_x = mapa.get(linhaInicial).indexOf('0');
            pac_map_y = linhaInicial;

            //Inicializando a matriz que armazena posicoes que o pacman ja passou
            passei = new boolean[totalColunas][totalLinhas];
            //Nao passou em lugar nenhum, no inicio
            for (int i = 0; i < totalColunas; i++) {
                for (int j = 0; j < totalLinhas; j++) {
                    passei[i][j] = false;
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Mapa falhou: " + ex);
        }

        //Começa indo para a direita.
        direcao = KeyEvent.VK_RIGHT;
        quadro = 0;
    }

    @Override
    public void inicia() {
        this.atraso = 40;
    }

    @Override
    public void desenha(Graphics2D g) {
        //Tem que desenhar os blocos e as pilulas aqui.
        //Se o pacman já passou na posicao da pilua, nao desenha.

        g.drawImage(pacman.getSubimage(quadro * 30, (direcao - 37) * 30, TAM, TAM),
                pac_pix_x, pac_pix_y, null);

        //Para desenhar uma imagem adaptando-a ao tamanho TAMxTAM (redimencionando):
        /*
         g.drawImage(imagem, posicao_inicial_do_desenho_x, 
                    posicao_inicial_do_desenho_y, 
                    posicao_inicial_do_desenho_x + TAM, 
                    posicao_inicial_do_desenho_y + TAM,
                    posicao_inicial_do_recorte_x, 
                    posicao_inicial_do_recorte_y, 
                    posicao_final_do_recorte_x, 
                    posicao_final_do_recorte_y, null);
        
        EXEMPLO:
        g.drawImage(bloco, x, y, x + TAM, y + TAM,
                0, 0, bloco.getWidth(), bloco.getHeight(), null);
         */
    }

    @Override
    public void atualiza() {
        quadro++;
        contador_de_atualizacoes++;

        if (quadro == 3) {
            quadro = 0;
        }

        switch (direcao) {
            case KeyEvent.VK_RIGHT:
                //código aqui
                break;
            case KeyEvent.VK_LEFT:
                //código aqui
                break;
            case KeyEvent.VK_DOWN:
                //código aqui
                break;
            case KeyEvent.VK_UP:
                //código aqui
                break;
        }
    }

    //Retorna o caracter que está na posição linha x coluna
    private char charAt(int linha, int coluna) {
        return mapa.get(linha).charAt(coluna);
    }

    //Método para responder ao teclado, somente setas.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key > 36 && key < 41) {
            direcao = key;
        }
    }
}

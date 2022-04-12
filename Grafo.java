import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
//Não funciona em grafos não orientados!!

public class Grafo {
    private int n;
    private int matriz[][];
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private int[] color;

    public Grafo() throws FileNotFoundException, InterruptedException {
        Scanner scl;
        scl = new Scanner(new File("G:\\Meu Drive\\4 Semestre\\Grafos\\trab02\\grafo.txt"));
        String l = scl.nextLine().trim().replaceAll("[,]", "");
        scl.close();
        this.setN(l.length());
        this.matriz = new int[this.getN()][this.getN()];
        for (int i = 1; i <= this.n; i++) {
            Vertice v = new Vertice(i);
            this.vertices.add(v);
        }
        ler_txt();
        coloreGrafo(this.matriz, 4);
        System.out.println();

    }

    private boolean verificaAdj(int v, int[][] matriz, int[] color, int count) {
        for (int i = 0; i < this.getN(); i++)
            if (matriz[v][i] == 1 && count == color[i])
                return false;
        return true;
    }

    private void printaCores(int color[]) throws InterruptedException {
        System.out.println();
        System.out.println("*** COLORINDO O GRAFO ***");
        TimeUnit.MILLISECONDS.sleep(500);
        for (int i = 0; i < this.getN(); i++) {
            if (color[i] == 1) {
                vertices.get(i).cor = "branco";
            } else if (color[i] == 2) {
                vertices.get(i).cor = "preto";
            } else if (color[i] == 3) {
                vertices.get(i).cor = "verde";
            } else if (color[i] == 4) {
                vertices.get(i).cor = "vermelho";
            }

            System.out.println(
                    "Vertice [" + vertices.get(i).indice + "] --> cor: " + vertices.get(i).cor);
            TimeUnit.MILLISECONDS.sleep(500);

        }
    }

    private boolean verificaPlanar(int[][] matriz, int num_cor, int[] color, int v) {
        if (v == this.getN())
            return true;

        for (int i = 1; i <= num_cor; i++) {
            if (verificaAdj(v, matriz, color, i)) {
                color[v] = i;
                if (verificaPlanar(matriz, num_cor, color, v + 1))
                    return true;
                color[v] = 0; // verificar essa linha
            }
        }
        return false;
    }

    private boolean coloreGrafo(int[][] matriz, int num_cor) throws InterruptedException {
        color = new int[this.getN()];
        Arrays.fill(color, 0);

        if (!verificaPlanar(matriz, num_cor, color, 0)) {
            System.out.println(
                    "Solução impossível!!");
            return false;
        }
  
        printaCores(color);
        return true;
    }

    private void ler_txt() {
        try {
            Scanner sc = new Scanner(new File("G:\\Meu Drive\\4 Semestre\\Grafos\\trab02\\grafo.txt"));
            while (sc.hasNextLine()) {
                for (int i = 0; i < this.getN(); i++) {
                    String[] linha = sc.nextLine().trim().split(",");
                    for (int j = 0; j < this.getN(); j++) {
                        this.matriz[i][j] = Integer.valueOf(linha[j]);
                    }
                }
            }
            sc.close();
        } catch (Exception error) {
            System.out.println("Erro: " + error);
        }
    }

    public int getN() {
        return n;
    }

    private void setN(int n) {
        this.n = n;
    }

}
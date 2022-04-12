public class Vertice {
    public String cor;
    public int indice;

    public Vertice(int indice) {
        this.indice = indice;
        this.cor = "vazio";
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
}

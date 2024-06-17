public class Player {
    private Caverna cavernaAtual;
    private int flechas;
    private boolean vivo;

    public Player(Caverna cavernaAtual) {
        this.cavernaAtual = cavernaAtual;
        this.flechas = 3;
        this.vivo = true;
    }

    public Caverna getCavernaAtual() {
        return cavernaAtual;
    }

    public void setCavernaAtual(Caverna cavernaAtual) {
        this.cavernaAtual = cavernaAtual;
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void moverPara(Caverna caverna) {
        setCavernaAtual(caverna);
    }
}

public class Caverna {
    private Caverna norte;
    private Caverna sul;
    private Caverna leste;
    private Caverna oeste;
    private Inimigo inimigo;
    private boolean temFlecha;

    public void conectar(Caverna outraCaverna, Direcao direcao) {
        switch (direcao) {
            case NORTE:
                this.norte = outraCaverna;
                outraCaverna.sul = this;
                break;
            case SUL:
                this.sul = outraCaverna;
                outraCaverna.norte = this;
                break;
            case LESTE:
                this.leste = outraCaverna;
                outraCaverna.oeste = this;
                break;
            case OESTE:
                this.oeste = outraCaverna;
                outraCaverna.leste = this;
                break;
        }
    }

    public Caverna getConexao(Direcao direcao) {
        switch (direcao) {
            case NORTE:
                return norte;
            case SUL:
                return sul;
            case LESTE:
                return leste;
            case OESTE:
                return oeste;
            default:
                return null;
        }
    }


    public Inimigo getInimigo() {
        return inimigo;
    }

    public void setInimigo(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    public boolean isTemFlecha() {
        return temFlecha;
    }

    public void setTemFlecha(boolean temFlecha) {
        this.temFlecha = temFlecha;
    }

}

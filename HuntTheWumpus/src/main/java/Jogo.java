import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Jogo {
    private static final int TAMANHO = 5;
    private List<Caverna> cavernas;
    private Player player;
    private Random random;

    public Jogo() {
        cavernas = new ArrayList<>();
        random = new Random();
        inicializarCavernas();
        posicionarElementos();
        player = new Player(cavernas.get(random.nextInt(cavernas.size())));
    }

    private void inicializarCavernas() {
        // Cria uma grade 4x4 de cavernas
        Caverna[][] grade = new Caverna[TAMANHO][TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                grade[i][j] = new Caverna();
                cavernas.add(grade[i][j]);
            }
        }

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (i > 0) grade[i][j].conectar(grade[i - 1][j], Direcao.NORTE);
                if (i < TAMANHO - 1) grade[i][j].conectar(grade[i + 1][j], Direcao.SUL);
                if (j > 0) grade[i][j].conectar(grade[i][j - 1], Direcao.OESTE);
                if (j < TAMANHO - 1) grade[i][j].conectar(grade[i][j + 1], Direcao.LESTE);
            }
        }
    }

    private void posicionarElementos() {
        cavernas.get(random.nextInt(cavernas.size())).setInimigo(new Wumpus());

        cavernas.get(random.nextInt(cavernas.size())).setInimigo(new Poco());

        cavernas.get(random.nextInt(cavernas.size())).setInimigo(new Morcego());

        int numFlechas = 3;
        for (int i = 0; i < numFlechas; i++) {
            cavernas.get(random.nextInt(cavernas.size())).setTemFlecha(true);
        }
    }

    public void jogar() {
        while (player.isVivo()) {
            Output.mostrarCavernaAtual(player);
            verificarProximidadeInimigos();
            Output.mostrarOpcoes();
            int opcao = Input.lerOpcao();
            processarOpcao(opcao);
        }
        Output.mostrarMensagem("Fim do jogo.");
    }

    private void processarOpcao(int opcao) {
        Caverna cavernaAtual = player.getCavernaAtual();
        Caverna novaCaverna = null;

        switch (opcao) {
            case 1:
                novaCaverna = cavernaAtual.getConexao(Direcao.NORTE);
                break;
            case 2:
                novaCaverna = cavernaAtual.getConexao(Direcao.SUL);
                break;
            case 3:
                novaCaverna = cavernaAtual.getConexao(Direcao.LESTE);
                break;
            case 4:
                novaCaverna = cavernaAtual.getConexao(Direcao.OESTE);
                break;
            case 5:
                if (player.getFlechas() > 0) {
                    lançarFlecha();
                } else {
                    Output.mostrarMensagem("Você não tem flechas para lançar!");
                }
                return;
            default:
                Output.mostrarMensagem("Opção inválida.");
                return;
        }

        if (novaCaverna == null) {
            Output.mostrarMensagem("Não há passagem nessa direção.");
        } else {
            player.moverPara(novaCaverna);
            verificarCaverna();
        }
    }

    private void lançarFlecha() {
        player.setFlechas(player.getFlechas() - 1);
        Caverna cavernaAtual = player.getCavernaAtual();
        Caverna cavernaProxima = cavernaAtual.getConexao(escolherDirecao());

        if (cavernaProxima != null) {
            if (cavernaProxima.getInimigo() instanceof Wumpus) {
                Output.mostrarMensagem("Parabéns! Você capturou o Wumpus. Você venceu!");
                player.setVivo(false);
            } else {
                Output.mostrarMensagem("Você lançou a flecha, mas não acertou o Wumpus.");
                moverWumpus();
                verificarCaverna();
            }
        } else {
            Output.mostrarMensagem("Você lançou a flecha na parede. Escolha outra direção na próxima vez.");
        }
    }

    private Direcao escolherDirecao() {
        Output.mostrarMensagem("Escolha a direção para lançar a flecha:");
        Output.mostrarMensagem("1. Norte");
        Output.mostrarMensagem("2. Sul");
        Output.mostrarMensagem("3. Leste");
        Output.mostrarMensagem("4. Oeste");

        int opcao = Input.lerOpcao();
        switch (opcao) {
            case 1:
                return Direcao.NORTE;
            case 2:
                return Direcao.SUL;
            case 3:
                return Direcao.LESTE;
            case 4:
                return Direcao.OESTE;
            default:
                Output.mostrarMensagem("Opção inválida. Lançando flecha para o norte por padrão.");
                return Direcao.NORTE;
        }
    }

    private void moverWumpus() {
        for (Caverna caverna : cavernas) {
            if (caverna.getInimigo() instanceof Wumpus) {
                Caverna novaCaverna = caverna.getConexao(Direcao.values()[random.nextInt(4)]);
                caverna.setInimigo(null);
                novaCaverna.setInimigo(new Wumpus());
                break;
            }
        }
    }

    private void verificarCaverna() {
        Caverna cavernaAtual = player.getCavernaAtual();
        if (cavernaAtual.getInimigo() != null) {
            if (cavernaAtual.getInimigo() instanceof Wumpus) {
                Output.mostrarMensagem("Você entrou na caverna do Wumpus! Game over.");
                player.setVivo(false);
            } else if (cavernaAtual.getInimigo() instanceof Poco) {
                Output.mostrarMensagem("Você caiu em um poço sem fundo! Game over.");
                player.setVivo(false);
            } else if (cavernaAtual.getInimigo() instanceof Morcego) {
                Output.mostrarMensagem("Você foi transportado por um morcego para outra caverna.");
                player.moverPara(cavernas.get(random.nextInt(cavernas.size())));
                verificarCaverna();
            }
        } else if (cavernaAtual.isTemFlecha()) {
            Output.mostrarMensagem("Você encontrou uma flecha!");
            player.setFlechas(player.getFlechas() + 1);
            cavernaAtual.setTemFlecha(false);
        }

    }

    private void verificarProximidadeInimigos() {
        Caverna cavernaAtual = player.getCavernaAtual();
        for (Direcao direcao : Direcao.values()) {
            Caverna cavernaAdjacente = cavernaAtual.getConexao(direcao);
            if (cavernaAdjacente != null && cavernaAdjacente.getInimigo() != null) {
                if (cavernaAdjacente.getInimigo() instanceof Wumpus) {
                    Output.mostrarMensagem("Você sente um forte odor vindo de uma caverna próxima.");
                } else if (cavernaAdjacente.getInimigo() instanceof Poco) {
                    Output.mostrarMensagem("Você sente uma brisa vindo de uma caverna próxima.");
                } else if (cavernaAdjacente.getInimigo() instanceof Morcego) {
                    Output.mostrarMensagem("Você ouve o bater de asas de um morcego vindo de uma caverna próxima.");
                }
            }
        }
    }

    public void iniciar() {
        Output.mostrarMensagem("Bem-vindo ao Hunt the Wumpus!");
        jogar();
    }
}

public class Output {
    public static void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public static void mostrarCavernaAtual(Player player) {
        Caverna caverna = player.getCavernaAtual();

        if (caverna.isTemFlecha()) {
            System.out.println("Você encontrou uma flecha!");
        }
    }


    public static void mostrarOpcoes() {
        System.out.println("Opções disponíveis:");
        System.out.println("1. Mover para o norte");
        System.out.println("2. Mover para o sul");
        System.out.println("3. Mover para o leste");
        System.out.println("4. Mover para o oeste");
        System.out.println("5. Lançar flecha");
    }
}

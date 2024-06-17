# Regras Implementadas
## labirinto de cavernas 5x5 conectadas
## O jogador pode detectar a presença do Wumpus, poços sem fundo ou morcegos nas cavernas adjacentes através de pistas visuais (odor, brisa, som das asas).
## Jogador: Inicia o jogo em uma caverna aleatória.
## Wumpus: Um monstro que, se o jogador entrar em sua caverna, o jogo termina com derrota. Se errar uma flecha o Wumpus se move para uma direção aleatória, podendo entrar na sua caverna.
## Poço: Um poço sem fundo, se o jogador entrar nessa caverna e fim de jogo.
## Morcegos: Transportam o jogador para uma caverna aleatória se ele entrar na caverna do morcego.
## Flechas: O jogador começa com 3 flechas e pode encontrar outras em algumas cavernas.As flechas viajam ate a proxima caverna, onde caso acerte o Wumpus, vence o jogo.


# Estrutura de dados
## Classe Caverna:

### Representa cada caverna no labirinto.
### Utiliza referências para as cavernas vizinhas (Norte, Sul, Leste, Oeste).
### Mantém uma referência para o inimigo presente na caverna (Wumpus, Poço sem Fundo, Morcego).

## Enums:

### Direcao: Define as direções possíveis (Norte, Sul, Leste, Oeste) para facilitar a navegação entre as cavernas.

## Inimigo: 
### Superclasse para os tipos específicos de inimigos (Wumpus, Poço sem Fundo, Morcego), usando herança para especialização.

## Jogo:
### Lógica, inicialização e gerenciamento do Jogo

## Player:
### Localização e atributos do Jogador

## Output:
### Mostrar mensagens

## Input:
### ler opções

## Listas
### Utilização de ArrayList para armazenar as cavernas e informações do jogo como flecha disponiveis.


# Outras informações
## Exclusao da Classe Menu
## Adição da classe Main para Iniciar o jogo


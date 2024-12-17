package Task05.Terminal.View;

import Task05.Cards.Model.MonsterCard;
import Task05.Cards.Controller.MonsterManager;
import Task05.Model.Player;
import java.util.ArrayList;
import java.util.Scanner;

import static Task05.Cards.Controller.MonsterManager.getColorForMonsterType;


public class Terminal {

    Scanner scanner = new Scanner(System.in);

    private int round;
    private MonsterManager monsterManager;
    private ArrayList<MonsterCard> cemiterioP1;
    private ArrayList<MonsterCard> cemiterioP2;
    private Player player1;
    private Player player2;
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[34m";
    public static final String RED = "\033[31m";

    public Terminal() {
        this.round = 0;
        this.cemiterioP1 = new ArrayList<>();
        this.cemiterioP2 = new ArrayList<>();
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.monsterManager = new MonsterManager();
        monsterManager.deck.shuffle();

    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void title() {
        boolean started = true;
        while (started) {
            System.out.print("""
                    
                    ----    BEM VINDO     ----
                               AO
                             POCKET
                             VENCER
                              TCG
                    ---------------------------
                    
                    1. Iniciar
                    2. Regras
                    3. sair
                    ->   """);
 
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    setRound(0);
                    defaultMenu();
                    return;
                case 2:
                    rules();
                    break;
                case 3:
                    started = false;
                    break;
            }
        }
    }

    public void defaultMenu()
    {
        int count = 0;
        ArrayList<MonsterCard> deckParaP1 = new ArrayList<>();
        ArrayList<MonsterCard> deckParaP2 = new ArrayList<>();

        for (MonsterCard monstro : monsterManager.deck.getMonstros()) {
            if (count < (monsterManager.deck.getMonstros().size()) / 2) {
                deckParaP1.add(monstro);
            } else if (count >= (monsterManager.deck.getMonstros().size()) / 2) {
                deckParaP2.add(monstro);
            }
            count++;
        }
        player1.setDeckPlayer(deckParaP1);
        player2.setDeckPlayer(deckParaP2);

        while(!(cemiterioP1.size() >= 3) || !(cemiterioP2.size() >= 3))
        {
            switch(round % 2)
            {
                case 0:
                    if((player1.getCampoPlayer().getCartaEmCampo()) == null)
                    {
                        System.out.println("Deck do " + BLUE + "Player 1" + RESET + ": ");
                        player1.showCard();
                        initOptions(player1);
                    }
                    else
                    {
                        if(cemiterioP1.size() >= 3)
                        {
                            System.out.println("Player 1 Perdeu!");
                            return;
                        }

                        menuPlayer1();
                        markDead(player2);

                    }

                    break;
                case 1:
                    if(player2.getCampoPlayer().getCartaEmCampo() == null)
                    {
                        System.out.println("Deck do " + RED + "Player 2" + RESET + ": ");
                        player2.showCard();
                        initOptions(player2);
                    }
                    else
                    {
                        if(cemiterioP2.size() >= 3)
                        {
                            System.out.println("Player 2 Perdeu!");
                            return;
                        }

                        menuPlayer2();
                        markDead(player1);

                    }

                    break;

            }
            round++;
        }
    }

    public void menuPlayer1()
    {
        boolean menuPlayer1Turn = true;

        while(menuPlayer1Turn)
        {
            System.out.printf("""
                                        ----- POCKET VENCER TCG -----
                                                 Round %d
                                                 %s
                """, round
                    ,player1.getName());

            cardTerminal(player1);
            cardBench(player1);

            boolean turnFinished = turnOptions(player1, player2);

            if(turnFinished)
            {
                menuPlayer1Turn = false;
            }
            clearTerminal();
        }

    }

    public void menuPlayer2()
    {
        boolean menuPlayer2Turn = true;

        while(menuPlayer2Turn)
        {

            System.out.printf("""
                                        ----- POCKET VENCER TCG -----
                                                 Round %d
                                                 %s
                """
                    , round
                    ,player2.getName());

            cardTerminal(player2);
            cardBench(player2);

            boolean turnFinished = turnOptions(player2, player1);

            if(turnFinished)
            {
                menuPlayer2Turn = false;
            }
            clearTerminal();
        }

    }

    public void rules()
    {
                System.out.print("""
                1. Cada jogador tem suas cartas sortidas
                2. 3 cartas são mandadas para o banco 1 uma para o campo como ativa
                3. todas cartas possuem 3 ataques
                    3.1 Ataque básico, Ataque que não custa energia
                    3.2 Ataque forte, custa energia e é mais forte
                    3.2 Ataque Especial, custa energia e é mais forte
                4. Recuar, carta troca de lugar com uma no banco
                5. Alocar energia para carta ativa
                6. esperar para encerrar turno
                 """);
        System.out.print("Aperte qualquer tecla para continuar...");
        scanner.nextLine();
    }

    public boolean turnOptions(Player player, Player enemy)
    {
        boolean turnoSucesso = false;
        while(!turnoSucesso)
        {
            System.out.print("""
                1. Ataque Básico
                2. Ataque Forte
                3. Ataque Especial
                4. Recuar
                5. Usar Energia
                6. esperar
                -> """);
 

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch(escolha)
            {
                case 1:
                    turnoSucesso = player.getCampoPlayer().getCartaEmCampo().basicAttack(enemy.getCampoPlayer().getCartaEmCampo());
                    break;
                case 2:
                    turnoSucesso = player.getCampoPlayer().getCartaEmCampo().strongAttack(enemy.getCampoPlayer().getCartaEmCampo());
                    break;
                case 3:
                    turnoSucesso = player.getCampoPlayer().getCartaEmCampo().specialAttack(enemy.getCampoPlayer().getCartaEmCampo());
                    break;
                case 4:
                    turnoSucesso = player.retreat(player.getCampoPlayer().getCartaEmCampo(),scanner);
                    break;
                case 5:
                    turnoSucesso = player.getCampoPlayer().getCartaEmCampo().allocateEnergy();
                    break;
                case 6:
                    System.out.printf("%s não fez nada...%n",player.getName());
                    turnoSucesso = true;
                    break;
                default:
                    System.out.println("Escolha invalida.");
                    turnoSucesso = false;
                    break;
            }
        }
        return turnoSucesso;
    }

    public void initOptions(Player player) {
        boolean found = false;
        while(!found)
        {
            System.out.printf("%n%s, selecione três Carta para entrar no banco.%n", player.getName());
            System.out.print("1-> ");
            String escolha1 = scanner.nextLine();
            found = validateInit(escolha1, player);
            if(!found)
            {
                System.out.println("ERROR: Nome invalido, verifique novamente seu deck!");
                continue;
            }

            System.out.print("2-> ");
            String escolha2 = scanner.nextLine();
            found = validateInit(escolha2, player);
            if(!found)
            {
                System.out.println("ERROR: Nome invalido, verifique novamente seu deck!");
                continue;
            }

            System.out.print("3-> ");
            String escolha3 = scanner.nextLine();
            found = validateInit(escolha3, player);
            if(!found)
            {
                System.out.println("ERROR: Nome invalido, verifique novamente seu deck!");
                continue;
            }

            player.selectBench(escolha1,escolha2,escolha3);

            System.out.print("Escolha um para já entrar em campo: ");
            String escolhaCampo = scanner.nextLine();
            found = validateInit(escolhaCampo, player);
            if(!found)
            {
                System.out.println("ERROR: Nome invalido, verifique novamente seu deck!");
                continue;
            }

            found = player.selectCard(escolhaCampo);
            if(!found)
            {
                System.out.println("ERROR: Nome invalido, verifique novamente seu deck!");
                continue;
            }

        }

    }

    public boolean validateInit(String name, Player player)
    {
        for(MonsterCard monster : player.getDeckPlayer())
        {
            if(monster.getName().equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }

    public void clearTerminal()
    {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    public void cardTerminal(Player player)
    {
         final int TAMANHO = 15;
         final int LARGURA = TAMANHO + 5;

        String color = getColorForMonsterType(player.getCampoPlayer().getCartaEmCampo().getType());
        String reset = "\033[0m";

        String linhaTopoEFim = color + "-".repeat(LARGURA) + reset;
        System.out.println(linhaTopoEFim);

        String msg = player.getCampoPlayer().getCartaEmCampo().getName();

        String emoji = " ";

        switch(player.getCampoPlayer().getCartaEmCampo().getType())
        {
            case Fire -> emoji = "🔥";
            case Leaf -> emoji = "🍃";
            case Rock -> emoji = "🗿";
            case Water -> emoji = "💧";
            case Lightning -> emoji = "⚡";
        }

        for (int i = 0; i < msg.length(); i += TAMANHO) {
            String msg_carta = msg.substring(i, Math.min(i + TAMANHO, msg.length()));
            System.out.println(color + String.format("|  %-"+ (TAMANHO -2) +"s%s  |", msg_carta, emoji) + reset);
        }

        int linhasVazias = 7 - (msg.length() / TAMANHO);
        for (int i = 0; i < linhasVazias; i++) {
            System.out.println(color + "|  " + " ".repeat(TAMANHO) + "  |" + reset);
        }

        String vidaEEnergia = String.format("E:%d  V:%.0f/%.0f",
                player.getCampoPlayer().getCartaEmCampo().getEnergy(),
                player.getCampoPlayer().getCartaEmCampo().getCurrentLife(),
                player.getCampoPlayer().getCartaEmCampo().getLife());

        System.out.println(color + "|  " + String.format("%-" + (TAMANHO) + "s", vidaEEnergia) + "  |" + reset);
        System.out.println(linhaTopoEFim);

        System.out.printf("""
                        Dano Base: %.2f
                        Dano Forte: %.2f - Energia: %d
                        Dano Especial: %.2f - Energia: %d
                        Custo para recuar - Energia: %d
                        %n""",player.getCampoPlayer().getCartaEmCampo().getBasicDmg()
                            ,player.getCampoPlayer().getCartaEmCampo().getStrongDmg()
                            ,player.getCampoPlayer().getCartaEmCampo().getEnergyCostStrong()
                            ,player.getCampoPlayer().getCartaEmCampo().getSpecialDmg()
                            ,player.getCampoPlayer().getCartaEmCampo().getEnergyCostSpecial()
                            ,player.getCampoPlayer().getCartaEmCampo().getRetreatCost());
    }

    public void cardBench(Player player)
    {
        for(MonsterCard carta : player.getCampoPlayer().getCartasEmBanco())
        {
            System.out.printf("Banco: {%s com %.2f vida de %.2f e %d energia}%n"
                    ,carta.getName()
                    ,carta.getCurrentLife()
                    ,carta.getLife()
                    ,carta.getEnergy());
        }
    }

    /*
    public void cardHand(Player player)
    {
        System.out.print("Mão: { ");
        for(MonsterCard carta : player.getDeckPlayer())
        {
            System.out.print(carta.getName() + ", ");
        }
        System.out.println("}");
    }
    */

    public void markDead(Player player)
    {
        if(player.isMonsterDead())
        {
            if(player.getName().equalsIgnoreCase("Player 1"))
            {
                cemiterioP1.add(player.getCampoPlayer().getCartaEmCampo());
            }
            else if(player.getName().equalsIgnoreCase("Player 2"))
            {
                cemiterioP2.add(player.getCampoPlayer().getCartaEmCampo());
            }

            cardBench(player);
            player.change(player.getCampoPlayer().getCartaEmCampo(), scanner);
        }
    }


}

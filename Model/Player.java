package Task05.Model;

import Task05.Cards.Model.Card;
import Task05.Cards.Model.MonsterCard;
import Task05.PlayerCommands;
import Task05.Cards.Model.Campo;
import Task05.Cards.Controller.MonsterManager;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements PlayerCommands {
    private String name;
    private ArrayList<MonsterCard> deckPlayer;
    private Campo campoPlayer;
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[34m";
    public static final String RED = "\033[31m";

    public Player(String name) {
        this.name = name;
        this.deckPlayer = new ArrayList<>();
        this.campoPlayer = new Campo();
    }

    public Campo getCampoPlayer() { return campoPlayer; }
    public void setCampoPlayer(Campo campoPlayer) { this.campoPlayer = campoPlayer; }

    public ArrayList<MonsterCard> getDeckPlayer() { return deckPlayer; }
    public void setDeckPlayer(ArrayList<MonsterCard> deckPlayer) { this.deckPlayer = deckPlayer; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public void showCard() {

        for(MonsterCard monstro : deckPlayer)
        {
            String color = MonsterManager.getColorForMonsterType(monstro.getType());

            System.out.print(color + monstro.getName() + RESET + " ");
        }
        System.out.println();

    }

    @Override
    public boolean selectCard(String nome) {
        for(MonsterCard carta : getCampoPlayer().getCartasEmBanco())
        {
            if(carta.getName().equalsIgnoreCase(nome))
            {
                System.out.printf("Você escolheu %s para o combate!%n%n",carta.getName());
                getCampoPlayer().insertCartaEmCampo(carta);
                return true;
            }
        }
        System.out.println("Nome de carta invalido no banco.");
        return false;
    }

    @Override
    public void selectBench(String nome1, String nome2, String nome3) {

        for(MonsterCard carta : deckPlayer)
        {
            if(nome1.equalsIgnoreCase(carta.getName()))
            {
                getCampoPlayer().insertCartaEmBanco(carta);
            }
            else if(nome2.equalsIgnoreCase(carta.getName()))
            {
                getCampoPlayer().insertCartaEmBanco(carta);
            }
            else if(nome3.equalsIgnoreCase(carta.getName()))
            {
                getCampoPlayer().insertCartaEmBanco(carta);
            }
        }
    }

    @Override
    public void allocateEnergy() {
        getCampoPlayer().getCartaEmCampo().allocateEnergy();
    }

    @Override
    public boolean retreat(MonsterCard card, Scanner scanner) {

        boolean found = false;
        while(!found)
        {
            int switcher = card.getEnergy() >= card.getRetreatCost() ? 1 : 2;
            switch (switcher)  {

                case 1:
                    System.out.printf("Qual carta assumirá o lugar de %s? ",card.getName());

                    if(getCampoPlayer().getCartaEmCampo() != null)
                    {
                        String trocar = scanner.nextLine();
                        found = campoPlayer.substituirCampo(trocar);
                    }
                    break;
                case 2:
                    System.out.printf("%s não tem energia o suficiente para recuar!%n", card.getName() );
                    return false;
                default:
                    System.out.println("De alguma forma você chegou ao default, parabéns eu acho");
                    return false;
            }
        }
        return found;
    }

    public void change(MonsterCard card, Scanner scanner)
    {
        if(getCampoPlayer().getCartasEmBanco().isEmpty())
        {
            return;
        }

        boolean found = false;
        while(!found)
        {
            System.out.printf("Qual carta assumirá o lugar de %s? ",card.getName());
            String remover = scanner.nextLine();
            found = campoPlayer.removerCampo(remover);
        }

    }

    public boolean isMonsterDead()
    {
        if(getCampoPlayer().getCartaEmCampo().getCurrentLife() <= 0)
        {
            return true;
        }
        return false;
    }

}

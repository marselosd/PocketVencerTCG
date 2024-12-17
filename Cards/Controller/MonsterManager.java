package Task05.Cards.Controller;

import Task05.Cards.Model.DeckModel;
import Task05.Cards.Model.MonsterCard;
import Task05.Cards.Type;

import java.util.List;

public class MonsterManager{


    public final DeckModel deck;


    public MonsterManager() {
        this.deck = new DeckModel();
        insert();
    }

    public void showCard(String cardName){

        for (MonsterCard monstro : deck.getMonstros() ){
            if (monstro.getName().equalsIgnoreCase(cardName)){
                System.out.printf("""
                        Card: %s
                        Life: %.2f
                        Energy: %d
                        Retreat: %d
                        Basic attack: %.2f
                        Strong attack: %.2f
                        Spacial attack: %.2f
                        """, monstro.getName(), monstro.getCurrentLife(), monstro.getEnergy(), monstro.getRetreatCost(), monstro.getBasicDmg(), monstro.getStrongDmg(), monstro.getSpecialDmg());
            }
        }
    }
    public static String getColorForMonsterType(Task05.Cards.Type type) {
        switch (type) {
            case Lightning: return "\033[33m";
            case Rock: return "\033[37m";
            case Fire: return "\033[31m";
            case Water: return "\033[34m";
            case Leaf: return "\033[32m";
            default: return "\033[0m";
        }
    }


    public void testFunction(){ // metodo pra testar algumas funcoes

        for(MonsterCard monstro : deck.getMonstros())
        {
            if(monstro.getName().equalsIgnoreCase("Gabiruzz"))
            {
                MonsterCard monster2 = deck.getMonstros().get(1);
                System.out.println(monster2.getCurrentLife());
                monstro.allocateEnergy();
                monstro.specialAttack(monster2);
                System.out.println(monster2.getCurrentLife());
            }
        }

    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void printDeck(){

        deck.printDeck();
    }

    private void insert() {
        List<MonsterCard> monsters = List.of(
                new MonsterCard("Gabiruzz", 50, Type.Lightning, 1, 1, 8, 12, 18, 2),
                new MonsterCard("Avestruz", 50, Type.Leaf, 1, 1, 8, 10, 16, 2),
                new MonsterCard("Cyber-Avestruz", 50, Type.Lightning, 1, 1, 8, 10, 16, 2),
                new MonsterCard("Bardo", 55, Type.Leaf, 2, 2, 12, 20, 35, 4),
                new MonsterCard("Sarib-Nivek", 60, Type.Lightning, 2, 3, 10, 18, 32, 5),
                new MonsterCard("Petruk", 60, Type.Rock, 2, 3, 10, 15, 28, 4),
                new MonsterCard("Diluvion", 55, Type.Water, 2, 2, 10, 15, 35, 4),
                new MonsterCard("Gasoré", 45, Type.Water, 1, 1, 8, 14, 16, 2),
                new MonsterCard("Gunj", 58, Type.Fire, 2, 3, 12, 20, 28, 4),
                new MonsterCard("Faísco", 45, Type.Fire, 1, 1, 8, 10, 18, 2),
                new MonsterCard("Balboa", 55, Type.Rock, 1, 2, 10, 12, 20, 3),
                new MonsterCard("Ervo", 60, Type.Leaf, 2, 1, 12, 15, 35, 4),
                new MonsterCard("Goto", 55, Type.Water, 1, 1, 8, 10, 18, 3),
                new MonsterCard("Lampo", 55, Type.Lightning, 2, 3, 10, 25, 22, 2),
                new MonsterCard("Pebblito", 58, Type.Rock, 2, 3, 10, 20, 18, 2),
                new MonsterCard("Palmer", 58, Type.Leaf, 2, 2, 10, 18, 28, 4),
                new MonsterCard("Cinrizard", 55, Type.Fire, 2, 2, 12, 20, 35, 4),
                new MonsterCard("Olecram", 45, Type.Fire, 2, 1, 10, 10, 16, 2),
                new MonsterCard("Elebasi", 45, Type.Water, 1, 2, 6, 12, 20, 3),
                new MonsterCard("Suehtam", 60, Type.Lightning, 3, 3, 12, 18, 28, 5),
                new MonsterCard("Egroj", 55, Type.Lightning, 2, 2, 10, 15, 18, 3),
                new MonsterCard("Sirc", 45, Type.Water, 1, 2, 12, 15, 18, 3),
                new MonsterCard("Leafar-Somar", 55, Type.Fire, 1, 3, 14, 18, 28, 4)
        );

            deck.addMonsters(monsters);
        }
}

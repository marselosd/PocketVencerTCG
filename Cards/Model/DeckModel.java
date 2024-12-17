package Task05.Cards.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckModel {

    private ArrayList<MonsterCard> monstros;

    public DeckModel() {
        this.monstros = new ArrayList<>();
    }

    public ArrayList<MonsterCard> getMonstros() {
        return monstros;
    }

    public MonsterCard getMonstro(String name) {
        for(MonsterCard monster : monstros)
        {
            if(monster.getName().equalsIgnoreCase(name))
            {
                return monster;
            }
        }
        return null;
    }

    public void addMonsters(List<MonsterCard> monsters) {
        this.monstros.addAll(monsters);
    }

    public void shuffle() {
        Collections.shuffle(monstros);
    }

    public void printDeck() {
        if (monstros.isEmpty()) {
            System.out.println("O deck está vazio!");
            return;
        }

        System.out.println("Cartas no deck:");
        for (MonsterCard monster : monstros) {
            System.out.println("Nome: "+ monster.getName() + " | ");
        }
    }

}

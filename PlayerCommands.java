package Task05;

import Task05.Cards.Model.Card;
import Task05.Cards.Model.MonsterCard;

import java.util.ArrayList;
import java.util.Scanner;

public interface PlayerCommands {

    void showCard();
    boolean selectCard(String nome);
    void selectBench(String nome1, String nome2, String nome3);
    void allocateEnergy();
    boolean retreat(MonsterCard card, Scanner scanner);
    boolean isMonsterDead();
}

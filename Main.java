/*
Projeto desenvolvido por 
Matheus Borgmann, Jorge Ferreira, Marcelo Dalcin e Isabele Torres
*/

package Task05;


import Task05.Cards.Controller.MonsterManager;
import Task05.Terminal.View.Terminal;

public class Main {
    public static void main(String[] args) {

        MonsterManager monsterManager = new MonsterManager();
        monsterManager.shuffleDeck();

        Terminal terminal = new Terminal();
        terminal.title();

    }
}

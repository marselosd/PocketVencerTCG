package Task05.Cards.Model;

import Task05.Model.Player;

public abstract class Card {
    private String name;
    private final double life;
    private double currentLife;
    private Task05.Cards.Type Type;
    private int energy;
    private int retreatCost;

    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GRAY = "\u001B[37m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";

    public Card(String name, double life, Task05.Cards.Type type, int retreatCost) {
        this.name = name;
        this.life = life;
        this.currentLife = life;
        Type = type;
        this.retreatCost = retreatCost;
        this.energy = 0;
    }

    private String getColorForCardType() {
        switch (Type) {
            case Lightning: return YELLOW;
            case Rock: return GRAY;
            case Fire: return RED;
            case Water: return BLUE;
            case Leaf: return GREEN;
            default: return RESET;
        }

    }

    public String getName() {
        return name;
    }

    public String getNameByColor()
    {
        String color = getColorForCardType();
        return color + this.name + RESET;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean allocateEnergy() {
        this.energy += 1;
        return true;
    }

    public double getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(double currentLife) {
        this.currentLife = currentLife;
    }

    public double getLife() { return life; }

    public Task05.Cards.Type getType() {
    return Type;
    }


    public void setType(Task05.Cards.Type type) {
    Type = type;
    }

    public abstract boolean basicAttack(Card cardEnemy);
    public abstract boolean strongAttack(Card cardEnemy);
    public abstract boolean specialAttack(Card cardEnemy);

    public int getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(int retreatCost) {
        this.retreatCost = retreatCost;
    }
}

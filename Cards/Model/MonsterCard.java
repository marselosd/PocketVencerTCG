package Task05.Cards.Model;

import Task05.Cards.Type;

public class MonsterCard extends Card {

    private int energy = 0;
    private int energyCostStrong;
    private int energyCostSpecial;
    private double basicDmg;
    private double strongDmg;
    private double specialDmg;

    public MonsterCard(String name, double life, Task05.Cards.Type type, int retreatCost, int energyCostStrong, double basicDmg, double strongDmg, double specialDmg, int energyCostSpecial) {
        super(name, life, type, retreatCost);
        this.basicDmg = basicDmg;
        this.strongDmg = strongDmg;
        this.specialDmg = specialDmg;
        this.energyCostStrong = energyCostStrong;
        this.energyCostSpecial = energyCostSpecial;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public int getEnergyCostStrong() { return energyCostStrong; }
    public double getBasicDmg() { return basicDmg; }
    public double getStrongDmg() { return strongDmg; }
    public double getSpecialDmg() { return specialDmg; }
    public int getEnergyCostSpecial() { return energyCostSpecial; }

    public void setEnergyCostStrong(int EnergyCostStrong) { this.energyCostStrong = EnergyCostStrong; }
    public void setBasicDmg(double basicDmg) { this.basicDmg = basicDmg; }
    public void setStrongDmg(double strongDmg) { this.strongDmg = strongDmg; }
    public void setSpecialDmg(double specialDmg) { this.specialDmg = specialDmg; }
    public void setEnergyCostSpecial(int energyCostSpecial) { this.energyCostSpecial = energyCostSpecial; }
    public void setEnergy(int energy) {this.energy = energy;}

    public boolean basicAttack(Card cardEnemy) {
        attackAnimation(cardEnemy);
 
        double dano = getBasicDmg() * getWeakness(cardEnemy);
        System.out.printf("%s atacou %s e causou %.2f de dano!%n",getName(),cardEnemy.getName(),dano);
        cardEnemy.setCurrentLife(cardEnemy.getCurrentLife() - dano);
        return true;
      
    }

    public boolean strongAttack(Card cardEnemy)
    {
        if(getEnergy() < getEnergyCostStrong())
        {
            System.out.println("Você não tem energia suficiente para isto.");
            return false;
        }
        attackAnimation(cardEnemy);
        double dano = getStrongDmg() * getWeakness(cardEnemy);
        System.out.printf("%s atacou %s e causou %.2f de dano!%n",getName(),cardEnemy.getName(),dano);
        setEnergy(getEnergy() - getEnergyCostStrong());
        cardEnemy.setCurrentLife(cardEnemy.getCurrentLife() - dano);
        return true;

    }

    public boolean specialAttack(Card cardEnemy) {
        if(getEnergy() < (getEnergyCostSpecial()))
        {
            System.out.println("Você não tem energia suficiente para isto.");
            return false;
        }
        attackAnimation(cardEnemy);
        double dano = getSpecialDmg() * getWeakness(cardEnemy);
        System.out.printf("%s atacou %s e causou %.2f de dano!%n",getName(),cardEnemy.getName(),dano);
        setEnergy(getEnergy() - getEnergyCostSpecial());
        cardEnemy.setCurrentLife(cardEnemy.getCurrentLife() - dano);
        return true;
      
    }

    public double getWeakness(Card cardenemy){

        switch(this.getType()){
            case Water-> {
                if(cardenemy.getType().equals(Task05.Cards.Type.Fire)){
                    System.out.println("CRÍTICO");
                    return 1.5;
                } else{
                    return 1;
                }

            }
            case Fire -> {
                if(cardenemy.getType().equals(Type.Leaf)){
                    System.out.println("CRÍTICO");
                    return 1.5;
                }else{
                    return 1;
                }
            }
            case Lightning -> {
                if(cardenemy.getType().equals(Type.Water)){
                    System.out.println("CRÍTICO");
                    return 1.5;
                }
                else{
                    return 1;
                }
            }
            case Rock -> {
                if(cardenemy.getType().equals(Type.Lightning)){
                    System.out.println("CRÍTICO");
                    return 1.5;
                }else{
                    return 1;
                }
            }
            case Leaf -> {
                if(cardenemy.getType().equals(Type.Rock)){
                    System.out.println("CRÍTICO");
                    return 1.5;
                }else{
                    return 1;
                }
            }
            default -> {
                System.out.println("Tipo inválido");
                return 0;
            }

        }

    }

    public void attackAnimation(Card cardEnemy) {
        String attackerName = this.getName();
        String enemyName = cardEnemy.getName();
        int distance = 30;
        String elemento = "";
        switch (this.getType()) {
            case Fire -> elemento = "🔥";
            case Water -> elemento = "💧";
            case Leaf -> elemento = "🍃";
            case Lightning -> elemento = "⚡";
            case Rock -> elemento =  "🗿";
        }
            try {

                for (int i = 0; i <= distance; i++) {
                System.out.print("\r" + attackerName + " ");

                for (int j = 0; j < i; j++) {
                    System.out.print(elemento);
                }

                for (int j = i; j < distance; j++) {
                    System.out.print(" ");
                }

                System.out.print(enemyName);

                Thread.sleep(200);
            }

            System.out.println("\n" + enemyName + " não conseguiu fugir e levou um ataque de " + attackerName);
        } catch (InterruptedException e) {
            System.out.println("A animação foi interrompida.");
        }
    }


    public boolean allocateEnergy() {
        this.energy += 1;
        return true;
    }

    @Override
    public String toString() {
        return "MonsterCard{" +
                "energyCostStrong=" + energyCostStrong +
                ", energyCostSpecial=" + energyCostSpecial +
                ", basicDmg=" + basicDmg +
                ", strongDmg=" + strongDmg +
                ", specialDmg=" + specialDmg +
                '}';
    }
}

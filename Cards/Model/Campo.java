package Task05.Cards.Model;

import java.util.ArrayList;


public class Campo
{
    private MonsterCard cartaEmCampo;
    private ArrayList<MonsterCard> cartasEmBanco;

    public Campo()
    {
        cartasEmBanco = new ArrayList<>();
        cartaEmCampo = null;
    }

    public MonsterCard getCartaEmCampo() { return cartaEmCampo; }
    public void setCartaEmCampo(MonsterCard cartaEmCampo) { this.cartaEmCampo = cartaEmCampo; }

    public ArrayList<MonsterCard> getCartasEmBanco() { return cartasEmBanco; }
    public void setCartasEmBanco(ArrayList<MonsterCard> cartasEmBanco) { this.cartasEmBanco = cartasEmBanco; }

    public void insertCartaEmBanco(MonsterCard carta)
    {
        if((cartaEmCampo != null) && (cartasEmBanco.size() >= 3 ) && (cartasEmBanco.size() <= 1 ))
        {
            System.out.println("Você não pode botar cartas no banco.");
            return;
        }

        cartasEmBanco.add(carta);

    }

    public void insertCartaEmCampo(MonsterCard carta)
    {
        for(int i = 0; i < cartasEmBanco.size(); i++)
        {
            if(cartasEmBanco.get(i).getName().equalsIgnoreCase(carta.getName()))
            {
                cartaEmCampo = cartasEmBanco.get(i);
                cartasEmBanco.remove(i);
                break;
            }
        }
    }

    public boolean substituirCampo(String nome)
    {
        boolean found = false;
        for(int i = 0; i < cartasEmBanco.size(); i++)
        {
            if(cartasEmBanco.get(i).getName().equalsIgnoreCase(nome))
            {
                insertCartaEmBanco(cartaEmCampo);
                insertCartaEmCampo(cartasEmBanco.get(i));
                found = true;
                break;
            }
        }
        if(!found)
        {
            System.out.println("Nome invalido.");
        }

        return found;
    }

    public boolean removerCampo(String nome)
    {
        boolean found = false;
        cartaEmCampo = null;
        for(int i = 0; i < cartasEmBanco.size(); i++)
        {
            if(cartasEmBanco.get(i).getName().equalsIgnoreCase(nome))
            {
                cartaEmCampo = cartasEmBanco.get(i);
                cartasEmBanco.remove(i);
                return true;
            }
        }

        System.out.println("Nome invalido.");
        return false;
    }

}

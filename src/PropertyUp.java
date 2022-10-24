public class PropertyUp extends Square{
    public String owner;
    public int price;
    public int rent;

    public PropertyUp(String name, int position, int price)
    {
        super(name, position);
        this.price = price;
        rent = price / 10;
    }

    public void Purchase(Player owning)
    {
        System.out.println(owning.name + " has purchased " + name + " for $" + price + "!");
        owning.money -= price;
        owner = owning.name;
    }

    public void Rent(Player payer)
    {
        System.out.println(payer.name + " has paid " + owner + " $" + rent + " as rent!");
        payer.money -= rent;
    }

}

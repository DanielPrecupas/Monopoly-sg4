public class PropertyNon extends Square{
    public String owner;
    public int price;
    public int rent;

    public PropertyNon(String name, int position, int price)
    {
        super(name, position);
        this.price = price;
        rent = price / 10;
    }

    public void Purchase(Player player)
    {
        System.out.println(player.name + " has purchased " + name + " for $" + price + "!");
        player.money -= price;
        owner = player.name;
    }

    public void Rent(Player player)
    {
        System.out.println(player.name + " has paid " + owner + " $" + rent + " as rent!");
        player.money -= rent;
    }
}

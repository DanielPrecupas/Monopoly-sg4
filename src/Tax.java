public class Tax extends Square{
    public String owner;
    public int price;

    public Tax(String name, int position, int price)
    {
        super(name, position);
        this.price = price;
    }

    public void payTax(Player player)
    {
        System.out.println(player.name + " has purchased " + name + " for $" + price + "!");
        player.money -= price;
        owner = player.name;
    }

}

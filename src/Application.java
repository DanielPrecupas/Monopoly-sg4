public class Application {
    InputDevice input;
    OutputDevice output;
    private int numPlayers, rounds;
    private Board b;
    private Player[] players;

    Application(InputDevice input, OutputDevice output, int num){
        this.input = input;
        this.output = output;
        String[] spaces = {"GO",
                "Mediterranean Avenue",
                "Community Chest",
                "Baltic Avenue",
                "Income Tax",
                "Reading Railroad",
                "Oriental Avenue",
                "Chance",
                "Vermont Avenue",
                "Connecticut Avenue",
                "Jail",
                "St. Charles Place",
                "Electrical Company",
                "States Avenue",
                "Virginia Avenue",
                "Pennsylvania Railroad",
                "St. James Place",
                "Community Chest",
                "Tennessee Avenue",
                "New York Avenue",
                "Park",
                "Kentucky Avenue",
                "Chance",
                "Indiana Avenue",
                "Illinois Avenue",
                "B & O Railroad",
                "Atlantic Avenue",
                "Ventnor Avenue",
                "Water Works",
                "Marvin Gardens",
                "Go to Jail",
                "Pacific Avenue",
                "North Carolina Avenue",
                "Community Chest",
                "Pennsylvania Avenue",
                "Short Line",
                "Chance",
                "Park Place",
                "Luxury Tax",
                "Boardwalk"};
        PropertyUp properties[] = new PropertyUp[spaces.length];
        //b = new Board(spaces);
        numPlayers=num;
        players = new Player[10];
        for (int i = 0; i < getPlayers(); i++)
            players[i]= new Player(input.getName());
    }

    public int getPlayers() {
        return numPlayers;
    }

    public void setPlayers(int players) {
        this.numPlayers = players;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
    public void run(){
        for(int i=0;i<getPlayers();i++) {
            for (int j = 0; j < getRounds(); j++) {
                int nr=input.throwDice();
                players[i].position=players[i].position+nr;
            }
            players[i].money=players[i].money+(players[i].position/40)*200;
            b.bank=b.bank-(players[i].position/40)*200;
            output.writeMessage("Name: " + players[i].name + "\ncash: " + players[i].money );
            output.writeMessage("Position: " + b.properties[players[i].position%40] + "\n");
        }
        Player winner=null;
        winner=new Player(input.getName());
        for(int i=0;i<getPlayers();i++) {
            if (players[i].money > winner.money) {
                winner.money = players[i].money;
                winner.position = players[i].position % 40;
                winner.name = players[i].name;
            }
            if (players[i].money == winner.money) {
                if(players[i].position % 40>winner.position)
                {
                    winner.name = players[i].name;
                    winner.position = players[i].position % 40;
                }

            }
        }
        output.writeMessage(" bank: " + b.bank+ "\n\nPlaces: ");
        for(int i=0;i<40;i++)
            output.writeMessage(b.properties[i]+" ");
        output.writeMessage("\n"+"Players:\n");

        input.declare();
        output.writeMessage("The Winner of the game is: \n"+ "Name: " + winner.name);
    }
}

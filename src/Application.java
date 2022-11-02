public class Application {
    InputDevice input;
    OutputDevice output;
    private int numPlayers, rounds;
    private Board b;
    private Player[] players;

    Application(InputDevice input, OutputDevice output){
        this.input = input;
        this.output = output;

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
    int turn = 0;
    int current_round = 0;
    public void run(int num){

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
        String[] prices = {
                "0",
                "60",
                "0",
                "60",
                "200",
                "200",
                "100",
                "0",
                "100",
                "120",
                "0",
                "140",
                "150",
                "140",
                "160",
                "200",
                "180",
                "0",
                "180",
                "200",
                "0",
                "220",
                "0",
                "220",
                "240",
                "200",
                "260",
                "260",
                "150",
                "280",
                "0",
                "300",
                "300",
                "0",
                "320",
                "200",
                "0",
                "350",
                "100",
                "400"
        };

        Square[] squares = new Square[spaces.length];
        for(int i = 0; i < spaces.length; i++)
        {
            switch(spaces[i])
            {
                case "GO":
                    squares[i] = new Square(spaces[i], i);
                    break;

                case "Community Chest":
                    squares[i] = new Square(spaces[i], i);
                    break;

                case "Income Tax":
                    squares[i] = new Tax(spaces[i], i, Integer.parseInt(prices[i]));
                    break;

                case "Luxury Tax":
                    squares[i] = new Tax(spaces[i], i, Integer.parseInt(prices[i]));
                    break;

                case "Chance":
                    squares[i] = new Square(spaces[i], i);
                    break;

                case "Park":
                    squares[i] = new Square(spaces[i], i);
                    break;

                case "Go to Jail":
                    squares[i] = new Square(spaces[i], i);
                    break;

                case "Jail":
                    squares[i] = new Square(spaces[i], i);
                    break;

                default:
                    if(spaces[i].contains("Railroad") || spaces[i].contains("Line"))
                        squares[i] = new PropertyNon(spaces[i], i, Integer.parseInt(prices[i]));
                    else if(spaces[i].contains("Company") || spaces[i].contains("Works"))
                        squares[i] = new PropertyNon(spaces[i], i, Integer.parseInt(prices[i]));
                    else
                        squares[i] = new PropertyUp(spaces[i], i, Integer.parseInt(prices[i]));

            }
        }

        b = new Board(squares);
        numPlayers = num;
        players = new Player[num];
        for (int i = 0; i < getPlayers(); i++)
            players[i]= new Player(input.getName());

        //game running
        while(current_round < rounds) {
            while(turn < getPlayers()) {
                int dice1 = input.throwDice(1, 6);
                int dice2 = input.throwDice(1, 6);
                int sum = dice1 + dice2;
                players[turn].position += dice1 + dice2;

                //check type
                b.check(players[turn], players, squares, turn, sum, getPlayers());
                if(dice1 == dice2)
                    turn--;
            }
            turn -= getPlayers();
            current_round++;
        }

        //deciding winner
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

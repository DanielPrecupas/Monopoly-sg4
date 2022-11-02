public class Main {
    public static void main(String[] args) {
        InputDevice input = new InputDevice();
        OutputDevice output = new OutputDevice();
        Application app = new Application(input, output);

        app.setRounds(Integer.parseInt(args[1]));
        app.run(Integer.parseInt(args[0]));



    }
}
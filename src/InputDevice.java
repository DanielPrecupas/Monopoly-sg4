import java.util.Random;

public class InputDevice {

    static int[] s= new int[10];
    public String getType(){ return "random"; }
    public void declare()
    {
        for(int i=0; i<10; i++)
            s[i]=0;
    }
    public Integer nextInt() {
        Random random = new Random();
        int n = random.nextInt(4);
        return n;
    }
    public String getName(){

        String[] names = {"Dog", "Cat", "Battleship", "Penguin", "Rubber Ducky", "Top Hat", "Race Car", "Thimble"};
        Random random = new Random();
        int r=random.nextInt(8);
        while(s[r]!=0)
            r= random.nextInt(8);
        s[r]=1;

        return names[r];
    }
    public int throwDice(int lower, int upper) {
        Random random = new Random();
        int n = random.nextInt(1,upper);
        return n;
    }
}

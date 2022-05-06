import java.util.*;

public class Encounters {
    Scanner scanner = new Scanner(System.in);
    Random rand = new Random();
    public void allClear() {
        System.out.print("No threats detected. ALL CLEAR.");
    }

    public int fuelLeak(int fuel, int k) {
        int leak = k % 5 + 1;
        System.out.print("Oh no! The fuel tank has sprung a leak!\n");
        System.out.print("The ship has lost " + leak + " tonnes of fuel\n");
        int ofuel = fuel - leak;


        if (ofuel <= 0) {
            ofuel = 0;
            System.out.print("You have no fuel left");
        } else {
            System.out.print("The ship now has " + ofuel + " tonnes of fuel.");
        }
        return ofuel;
    }

    public int marauders(int health, int weapons,int engines, int k) {
        int foe = k;
        int turn = 1;
        System.out.print("You are being attacked defend your ship!\n" +
                "There are " + foe + " marauders.\n\n");

        while(foe>0){
            System.out.print("What actions do you take this turn?\n" +
                    "1. Fire on the enemies\n" +
                    "2. Make makeshift repairs\n" +
                    "3. Attempt to flee\n\n");
            int temp = scanner.nextInt();
            if(temp == 1){
                int hit = weapons * (rand.nextInt(3)+1);
                foe = foe - (hit);
                if(foe < 0){
                    System.out.print("You destroy " + hit +" of your attackers.\n" +
                            "There are 0 marauders left.\n\n");
                }
                else {
                    System.out.print("You destroy " + hit + " of your attackers.\n" +
                            "There are " + foe + " marauders left.\n\n");
                }

            }
            if(temp == 2){
                int heal = rand.nextInt(10);  // find a way to use crew
                if(heal <= 0){
                    System.out.print("The crew fails to make repairs in time.\n\n");
                }
                else{
                    System.out.print("You repair " + heal + "  of your hulls integrity.\n");
                    health = heal + health;
                    System.out.print("You now have "+ health+"\n\n");
                }
            }
            if(temp == 3){
                int run = turn + rand.nextInt(10);
                if(run >= 10){
                    System.out.print("You successfully escaped!\n");
                    return health;
                }
                System.out.print("The marauders are still on your tail!");
            }
            int damage = foe * (rand.nextInt(3)+1);
            if(damage > 0){
                health = health - damage;
                System.out.print("Your ship has taken " + damage + " damage and now has " + health + " hull integrity remaining!");}
            turn++;
        }



        return health;
    }

    public int[] mysteriousStranger(int weapons, int fuel){
        System.out.print("You cross paths with another ship whose mysterious captain reaches out to you with a shady proposition:\n" +
                "1. Exchange fuel for weapons\n" +
                "2. Exchange weapons for fuel\n" +
                "3. Leave\n" +
                "You aren't sure you fully trust the Captain to hold up a fair bargain and\n" +
                "you currently have " + weapons + " cannons and " + fuel + " fuel. Should you trust them?\n");

        int newWeapons = weapons;
        int newFuel = fuel;

        int weapExchange = rand.nextInt(2) + 1; //trade 1-3 weapons
        int fuelExchange = rand.nextInt(150) + 50; //trade
        int temp = scanner.nextInt();
        if (temp == 1){
            newWeapons += weapExchange;
            newFuel -= fuelExchange;
            if(newFuel < 0){
                newFuel = 0;
                System.out.print("The Captain gave you " + weapExchange + " cannons, BUT HE TOOK ALL YOUR FUEL!\n");
            }
            else{
                System.out.print("The Captain gave you " + weapExchange + " cannons, in exchange for " + fuelExchange + " fuel.\n");
            }
        }
        if (temp == 2){
            newWeapons -= weapExchange;
            newFuel += fuelExchange;
            if(newWeapons < 0){
                newWeapons = 0;
                System.out.print("The Captain gave you " + fuelExchange + " fuel, BUT HE TOOK ALL YOUR CANNONS!\n");
            }
            else{
                System.out.print("The Captain gave you " + fuelExchange + " fuel, in exchange for " + weapExchange + " cannons.\n");
            }
        }
        if (temp == 3){
            System.out.print("'Fine, I'll take my generosity elsewhere' The Captain says as their ship starts to fly away.\n");
        }
        else{
            System.out.print("'Seriously. Don't waste my time you drivel' The Captain proclaims as they fly off into the distance.\n" +
                    " (You should really follow the directions)\n");
        }

        int[] newVal = new int[2];
        newVal[0] = newWeapons;
        newVal[1] = newFuel;
        return newVal;
    }

    public int wormHole(int distance){
        System.out.print("You come across a swirling vortex pulling on the ship ever so slightly.\n " +
                "If you recall correctly, this seems to be a Worm Hole, and wondering portal that transports you to a random point in space.\n" +
                "You consider entering the Worm Hole in order to close the distance to the new world, but the possibility of being even farther\n" +
                "from your soon to be home is just as likely.\n" +
                "Do you enter the Worm Hole?:\n" +
                "1. Yes\n" +
                "2. No\n");
        int temp = scanner.nextInt();
        if (temp == 1){
            int distTraveled = rand.nextInt(401) - 100; // -100 to and including 300
            distance += distTraveled;
            System.out.print("When you exit the Worm Hole you see that you are now " + distance + " Light Years away.\n");
        }
        if (temp == 2){
            System.out.print("You chose to continue on your journey the natural way and left the Worm Hole behind.\n" +
                    "Was this the right choice? Who knows.\n");
        }
        else{
            int distTraveled = rand.nextInt(401) - 200; // -200 to and including 200
            distance += distTraveled;
            System.out.print("The Worm Hole sucks you in by force sending to a new destination.(Use the given options next time)\n" +
                    "On the other side you find yourself " + distance + " Light Years away from your future home.\n");
        }
        return distance;
    }

}
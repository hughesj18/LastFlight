import java.io.IOException;
import java.util.*;



public class LastFlight {
    static int difficulty;                      //modifier to change difficulty of encounters
    static int k = 0;                           //iteration variable
    static double credits = 100;                //player currency
    static int fuel = 500;                      //resource used to travel distance
    static int engines = 1;                     //player distance traveled modifier
    static int health = 50;                     //hull integrity of the ship
    static int weapons = 1;                     //player damage modifier
    static int distance;                        //distance to finish
    static int crew = 1000;                     //used to calculate points
    static int speed = 10;                      //variable for how much distance the ship moves per turn
    static int fcost = 0;                       //cost of moving at the chosen speed
    static int temp = 0;                        //honestly need to change and rename



    static String player;                       //player name


    //call encounters
    static Encounters flight = new Encounters();


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        //intro text

        System.out.print("Welcome to the Last Flight! \nDo you have what it takes to make it to salvation? \n\n");
        System.out.print("Instructions:\n" +
                "This game is played via responding to situations \n" +
                "that arise on your journey to a new world.\n" +
                "These can range from ship malfunctions to \n" +
                "encounters with alien merchants to even attacks \n" +
                "on your ship by hostile entities. \n" +
                "It is your job as Captain to decide what is best \n" +
                "for the remaining surviors of a doomed Earth\n");
        System.out.print("\n--Press Enter to continue--");
        System.in.read();
        System.out.print("\nThe world has ended.\n\nYou are on the last flight to a new habitable world.\nThe Journey will be long and hard, but though working together \nyou and the remaining survivors can make it to this new paradise");

        System.out.print("\nWhat difficulty would you like to play on:\n1.Easy\n2.Medium\n3.Hard\n4.Brutal\n");



        //Setting the difficulty

        while (difficulty != 1 && difficulty != 2 && difficulty != 3 && difficulty != 4) {
            System.out.print("\nPlease enter a value 1-4\n");
            difficulty = scanner.nextInt();
        }
        if (difficulty == 1)
            distance = 1000;
        if (difficulty == 2)
            distance = 3000;
        if (difficulty == 3)
            distance = 5000;
        if (difficulty == 4)
            distance = 10000;


        //get player name

        System.out.print("\n\nWhat is your name Captain?:\n");
        player = scanner.next();
        System.out.print("Welcome aboard Captain " + player + "!\n");

        System.out.print("\nYou helm the Marie Celeste, which is outfitted with :\n\n");
        System.out.print("    Warp engines:" + engines);
        System.out.print("       Phase cannons: " + weapons + "\n");
        System.out.print("    Hull integrity: " + health);
        System.out.print("   Crew members: " + crew + "\n");
        System.out.print("    credits: " + credits);
        System.out.print("       fuel:    " + fuel);

        System.out.print("\n--Press enter to continue--");
        System.in.read();

        System.out.print("\nYou lift of just in time as the home\n" +
                "you once knew is no more, but now is not\n" +
                "the time for rest there is a long journey ahead.\n");

        System.out.print("--And so your journey begins.--");

        while (distance > 0 && fuel > 0 && health > 0) {
            while (temp != 1 && temp != 2 && temp != 3) {
                System.out.print("\n"+distance+" light years left to go!\n\n");

                System.out.print("\nSelect a speed\n" +
                        "1.Slow    (1 fuel = 10 light years per engine)\n" +
                        "2.Average (10 fuel = 50 light years per engine)\n" +
                        "3.Fast    (100 fuel = 200 light years per engine)\n");
                temp = scanner.nextInt();
                if(temp == 1){
                    speed = 10;
                    fcost = 1;
                }
                if(temp == 2){
                    speed = 50;
                    fcost = 10;
                }
                if(temp == 3){
                    speed = 200;
                    fcost = 100;
                }
            }

            System.out.print("You traveled " + (speed*engines) + " light years\n");

            int jump = (int)(Math.random()*(16 +1)+0);
            if(jump <= 5){
                flight.allClear();
            }
            if(jump == 6){
                fuel = flight.fuelLeak(fuel, k);
            }
            if(jump <= 9 && jump >= 7){
                health = flight.marauders(health, weapons,engines ,k);
                credits = credits + k*50;
            }
            if(jump >= 10 && jump <= 12){
                int bazar = 100;
                System.out.print("Welcome to the Temporal Market!\n" +
                        "What would you like to buy traveler?\n");
                while(bazar != 0) {
                    System.out.print("[1] Fuel (1 credits = 1 fuel)\n" +
                            "[2] Cannon (Increases damage multiplier : 1000)\n" +
                            "[3] Engine (Increases how far you travel : 5000)\n" +
                            "[0] Exit\n");

                    bazar = scanner.nextInt();
                    if (bazar == 1){
                        System.out.print("How much fuel would you like to purchase?\n");
                        int pur = scanner.nextInt();
                        if(credits >= pur){
                            credits= credits - pur;
                            fuel = fuel + pur;
                            System.out.print("You purchased "+ pur +" fuel for " + pur +" credits.\n" +
                                    "You now have "+ credits +" credits.\n");
                        }
                        else{
                            System.out.print("You dont have the credits for that bum!\n");
                        }

                    }
                    if(bazar == 2){
                        System.out.print("How many cannons do you want to buy?\n");
                        int pur = scanner.nextInt();
                        if (credits >= pur*1000){
                            credits = credits - pur*1000;
                            weapons = weapons + pur;
                            System.out.print("You purchased "+ pur +" cannon(s) for "+ pur*1000 +" credits.\n" +
                                    "You now have "+ credits +" credits left.\n");
                        }
                        else{
                            System.out.print("You dont have the credits for that bum!\n");
                        }
                    }
                    if(bazar == 3){
                        System.out.print("How many engines would you like to buy?\n");
                        int pur = scanner.nextInt();
                        if(credits >= pur*5000){
                            credits = credits - pur*5000;
                            engines = engines + pur;
                            System.out.print("You purchased "+ pur +" engine(s) for "+ pur*5000 +" credits\n" +
                                    "You now have "+ credits +" credits left.\n");
                        }                        else{
                            System.out.print("You dont have the credits for that bum!\n");
                        }
                    }
                }
            }
            if(jump == 13){
               int[] temp = flight.mysteriousStranger(weapons, fuel);
               weapons = temp[0];
               fuel = temp[1];
            }
            if(jump == 14){
                distance = flight.wormHole(distance);
            }

            temp = 0;
            fuel = fuel - fcost;
            distance = distance-(speed*engines);
            k++;

        }
        //end conditions
        if(distance <= 0){
            System.out.print("Congratulations you have led "+ crew + " survivors" +
                    " to a new home!");}
        else if(health < 0){
            System.out.print("The ship was destroyed and you never made it to salvation\n" +
                    "                 Game over");
        }
        else if(fuel < 0){
            System.out.print("You have ran out of fuel and are derelict in space.\n" +
                    "your crew perish and your ship is never recovered.\n" +
                    "                 Game Over");

        }
    }
}


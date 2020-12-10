package Program_7;

import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("***NFL PLAYER TABLE***\n");
        Scanner scan = new Scanner(System.in);
        boolean loop = true;

        do {
            System.out.println("choose an option:");
            System.out.println("1). create custom tables");
            System.out.println("2). test average values on predefined tables");
            System.out.println("3). quit");
            int userCh = scan.nextInt();

            switch(userCh) {
                case 1:
                    phaseOne();
                    break;
                case 2:
                    phaseTwo();
                    break;
                default:
                    System.out.println("PROGRAM TERMINATED");
                    loop=false;
            }
        } while(loop);

    }

    private static void phaseOne() {
        boolean loop = true;
        Table table = new Table();

        do {
            int choice = menuProcess();

            switch(choice) {
                case 1:
                    insertProcess(table);
                    System.out.println();
                    break;
                case 2:
                    //delete not working...
                    break;
                case 3:
                    searchProcess(table);
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("The binary search tree is " + table.getHeight() + " nodes high.");
                    break;
                case 5:
                    System.out.println("The table has " + table.getSize() + " players in it.");
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("The average level of the binary search tree is "
                            + table.getAvgLevel());
                    break;
                case 7:
                    BSTstructureProcess(table);
                    break;
                case 8:
                    System.out.println();
                    table.toString();
                    System.out.println();
                    break;
                default:
                    System.out.println("ending program.\n");
                    loop = false;
            }

        } while(loop);
    }

    private static void phaseTwo() {
        System.out.println("\n*expected levels of retrieval, \nbased on cases of tables filled with random numbers*\n");
        System.out.println("SIZE 16:");
        System.out.println("\texpected level: " + findExpectedCase(4) + "\n\tworst case: " + findWorstCase(4) + "\n");
        System.out.println("SIZE 32:");
        System.out.println("\texpected level: " + findExpectedCase(5) + "\n\tworst case: " + findWorstCase(5) + "\n");
        System.out.println("SIZE 64:");
        System.out.println("\texpected level: " + findExpectedCase(6) + "\n\tworst case: " + findWorstCase(6) + "\n");
        System.out.println("SIZE 128:");
        System.out.println("\texpected level: " + findExpectedCase(7) + "\n\tworst case: " + findWorstCase(7) + "\n");
        System.out.println("SIZE 256:");
        System.out.println("\texpected level: " + findExpectedCase(8) + "\n\tworst case: " + findWorstCase(8) + "\n");
        System.out.println("SIZE 512:");
        System.out.println("\texpected level: " + findExpectedCase(9) + "\n\tworst case: " + findWorstCase(9) + "\n");
        System.out.println("SIZE 1024:");
        System.out.println("\texpected level: " + findExpectedCase(10) + "\n\tworst case: " + findWorstCase(10) + "\n");
        System.out.println("SIZE 2048:");
        System.out.println("\texpected level: " + findExpectedCase(11) + "\n\tworst case: " + findWorstCase(11) + "\n");
        System.out.println("SIZE 4096:");
        System.out.println("\texpected level: " + findExpectedCase(12) + "\n\tworst case: " + findWorstCase(12) + "\n");
        System.out.println("SIZE 8192:");
        System.out.println("\texpected level: " + findExpectedCase(13) + "\n\tworst case: " + findWorstCase(13) + "\n");
        System.out.println("SIZE 16384:");
        System.out.println("\texpected level: " + findExpectedCase(14) + "\n\tworst case: " + findWorstCase(14) + "\n");
        System.out.println("SIZE 32768:");
        System.out.println("\texpected level: " + findExpectedCase(15) + "\n\tworst case: " + findWorstCase(15) + "\n");

    }

    //returns the average level in instances of ten trees with size 2^N
    private static double findExpectedCase(int N) {
        Random rand = new Random();
        int sum = 0;

        for(int i = 0; i < 10; i++) {
            Table t = new Table();
            for(int e = 1; e <= Math.pow(2, N); e++) {
                KeyedNum random = new KeyedNum(rand.nextInt());
                t.insert(random);
            } sum += t.getAvgLevel();
        } return (double) sum/10;
    }

    //returns max height within ten instances of trees size 2^N
    private static int findWorstCase(int N) {
        Random rand = new Random();
        int max = 0;

        for(int i = 0; i < 10; i++) {
            Table t = new Table();
            for(int e = 1; e <= Math.pow(2, N); e++) {
                KeyedNum random = new KeyedNum(rand.nextInt());
                t.insert(random);
            }
            if(t.getHeight() > max)
                max = t.getHeight();
        } return max;
    }

    private static int menuProcess() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nchoose an option:");
        System.out.println("1). insert a player");
        System.out.println("2). delete a player (buggy)");
        System.out.println("3). search a player's info");
        System.out.println("4). get the height of the BST");
        System.out.println("5). get the number of catalogged players");
        System.out.println("6). get the average level of the BST");
        System.out.println("7). display the BST structure");
        System.out.println("8). display all data");
        System.out.println("9). quit program");
        return scan.nextInt();
    }

    private static NFLPlayer createPlayer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tteam name:");
        String team = scan.next();
        System.out.println("\tplayer name:");
        String name = scan.next();
        System.out.println("\tplayer number:");
        int num = scan.nextInt();
        System.out.println("\tsalary:");
        int salary = scan.nextInt();

        return new NFLPlayer(num, team, name, salary);
    }

    private static NFLPlayerKey createKey() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tteam name:");
        String team = scan.next();
        System.out.println("\tplayer number:");
        int num = scan.nextInt();

        return new NFLPlayerKey(num, team);
    }

    private static void insertProcess(Table t) {
        NFLPlayer playerInsert = createPlayer();
        t.insert(playerInsert);
    }

    private static void searchProcess(Table t) {
        NFLPlayerKey searchKey = createKey();
        Keyed result = t.search(searchKey);
        System.out.println("\nfound: " + result);
    }

    private static void BSTstructureProcess(Table t) {
        t.showTree();
        System.out.println();
    }

}

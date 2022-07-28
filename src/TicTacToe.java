import java.lang.module.Configuration;
import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] field = {{' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '}};
        printfield(field);

        Scanner scan = new Scanner(System.in); // in my program it works, but at tutorial it's not tested

        System.out.println("If CPU should start press 'C', else press any bottom");
        char firstTurn = scan.next().charAt(0);
        if (firstTurn == 'C') {
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            EnterSymbol(field, cpuPos, "cpu");

            printfield(field);
        }

        while(true){

            //Scanner scan = new Scanner(System.in); // scanner may be here, in tutorial it works here

            System.out.println("Enter your symbol between 1-9:");
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct position");
                playerPos = scan.nextInt();
            }

            EnterSymbol(field, playerPos, "player");

            System.out.println("Ur turn:");
            printfield(field);

            String result = checkWinner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            EnterSymbol(field, cpuPos, "cpu");

            System.out.println("CPU turn:");
            printfield(field);

            result = checkWinner();
            if (result.length()>0) {
                System.out.println(result);
                break;
            }

        }

    }

    public static void printfield(char[][] field) {
     for (char[] row : field) {
         for (char c : row) {
             System.out.print(c);
         }
         System.out.println();
     }
 }

    public static void EnterSymbol(char[][] field, int pos, String user) {

            char symbol = ' ';

            if (user.equals("player")){
                symbol = 'X';
                playerPositions.add(pos);
            } else if (user.equals("cpu")){
                symbol = 'O';
                cpuPositions.add(pos);
            }

         switch(pos){
             case 1:
                 field[0][0] = symbol;
                 break;
             case 2:
                 field[0][2] = symbol;
                 break;
             case 3:
                 field[0][4] = symbol;
                 break;
             case 4:
                 field[2][0] = symbol;
                 break;
             case 5:
                 field[2][2] = symbol;
                 break;
             case 6:
                 field[2][4] = symbol;
                 break;
             case 7:
                 field[4][0] = symbol;
                 break;
             case 8:
                 field[4][2] = symbol;
                 break;
             case 9:
                 field[4][4] = symbol;
                 break;
             default:
                 break;
         }
     }

    public static String checkWinner() {

      List topRow = Arrays.asList(1,2,3);
      List midRow = Arrays.asList(4,5,6);
      List botRow = Arrays.asList(7,8,9);
      List leftCol = Arrays.asList(1,4,7);
      List midCol = Arrays.asList(2,5,8);
      List rightCol = Arrays.asList(3,6,9);
      List cross1 = Arrays.asList(1,5,9);
      List cross2 = Arrays.asList(7,5,3);

      List<List> winning = new ArrayList<List>();
      winning.add(topRow);
      winning.add(midRow);
      winning.add(botRow);
      winning.add(leftCol);
      winning.add(midCol);
      winning.add(rightCol);
      winning.add(cross1);
      winning.add(cross2);

      for(List l: winning) {
         if (playerPositions.containsAll(l)) {
             return "Configuration you won!";
         } else if (cpuPositions.containsAll(l)) {
             return "CPU wins! Sorry :(";
         } else if (playerPositions.size() + cpuPositions.size() == 9) {
             return "Draw!";
         }

      }
        return "";
  }
}

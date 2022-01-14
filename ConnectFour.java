import java.util.Scanner;

public class ConnectFour
{

    static int turn;//Declared static so that it could be a global variable and be accessed by each separate method

    public static void main(String[] args)
    {
        char Player1chip = 'x';//Defined variables for the player chips which were defined by the instructions
        char Player2chip = 'o';
        turn = 0;//No one has made a turn yet and therefore the game is only just starting


        Scanner userInput = new Scanner(System.in);

        System.out.println("What would you like the height of the board to be? ");
        int height = userInput.nextInt();//Accepts input of height for the board
        System.out.println("What would you like the length of the board to be? ");
        int length = userInput.nextInt();//Accepts input of length for the board
        char[][] board = new char[height][length];
        initializeBoard(board);//Calls method to start a blank board with a specific height and length
        printBoard(board);//Prints the blank board
        int totalPlacements = length * height;//Total placements had to be tracked so if the total amount was placed, it would result in a draw
        System.out.println("Player 1: x");//Total placement is the area of the board
        System.out.println("Player 2: o");

        while(turn < totalPlacements)//As long as the amount of turns was less than the total placements it would be a draw if all spots were filled
        {//If all spots on board filled up, the statement would become false and the loop would end meaning the game is over
            int row = 0;
            System.out.println("Player 1: Which column would you like to choose? ");
            int choiceP1 = userInput.nextInt();
            insertChip(board,choiceP1,Player1chip);//Takes the choice they made about the column and uses it for placing the position of the chip in the board
            printBoard(board);
            ++turn;//Adds to turn after every piece is placed to mark the rotation between each individual player
            if(checkIfWinner(board,choiceP1,row,Player1chip) == true)//If checkIfWinner returns true, the game ends
            {
                System.out.println("Player 1 won the game!");
                System.exit(0);//If either player wins, the game will exit
            }
            System.out.println("Player 2: Which column would you like to choose? ");
            int choiceP2 = userInput.nextInt();
            insertChip(board,choiceP2,Player2chip);
            printBoard(board);//Prints the board
            ++turn;
            if(checkIfWinner(board,choiceP2,row,Player2chip) == true)
            {
                System.out.println("Player 2 won the game!");
                System.exit(0);
            }
        }

        System.out.println("Draw. Nobody wins.");//Result of the while loop becoming false
    }
    public static void printBoard(char[][] array)
    {
        for (int i = array.length-1; i >= 0; i--)//Had to print the board backwards as instructed by my TA Zachery
        {//Array is stored into memory backwards so you need to print it backwards to show it correctly
            for (int j = 0; j <= array[0].length-1; j++)//Nested for loop to go through each row of the board and print out each individual spot on the board
            {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
    public static void initializeBoard(char[][] array)
    {
        for (int i = 0; i <= array.length-1; i++)
        {
            array[i][0] = '-';//Initializes the board by going through each iteration of the 2d array and making it a '-'

            for (int j = 0; j <= array[0].length-1; j++)
            {
                array[i][j] = '-';
            }
        }
    }
    public static int insertChip(char[][] array, int col, char chipType)
    {
        int row;
        for (row = 0; row <= array.length-1; row++)//received idea from TA within slack, Zachery Utt
        {
            if (array[row][col] == '-')//Checks to see if there is an open space and if there is it places the chip of the player
            {
                array[row][col] = chipType;
                System.out.println();//Prints out the line with the changed value so new board is different and has piece
                return row;//row must be returned so that checkIfWinner does not need to look at the entire board
            }
        }
        System.out.println("Column " + col + " is already filled.");//If they try to fill a column that is already full
        turn--;//If the column is already filled, it won't count that as a turn as it did not place the player chip
        return -1;//Random value that cannot be true so that it will make the statement false, you cannot place a chip into an already filled space
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        int count = 0;
        for(int rowOffset = -3; rowOffset <= 3; rowOffset++) {
            try {
                if (array[row + rowOffset][col] == chipType) {//Did a try catch inside of a for loop to account for any edge cases
                    count++;//If the count reached 4 it meant there was 4 in a row and the player would win
                    if (count >= 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }

            } catch (Exception e) {

            }
        }
        count = 0;
        for(int columnOffset = -3; columnOffset <= 3; columnOffset++) {//Accounts for column and goes through each value to check if there is 4 in a row
            try {
                if (array[row][col + columnOffset] == chipType) {//Cannot be a diagonal win so I only had to check for horizontal and vertical wins
                    count++;
                    if (count >= 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }

            } catch (Exception e) {

            }
        }
        return false;//Returns false if it breaks out of both for loops, this will keep the game going
    }
}

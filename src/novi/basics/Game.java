package novi.basics;


import static novi.basics.Main.PLAYERINPUT;

public class Game {

    private Field[] board;
    private int maxRounds;

    private Player player1;
    private Player player2;

    private int drawCount;

    private Player activePlayer;

    private boolean gameWon = false;

    private int endGameChoice;

    private int round;


    public Game(Player player1, Player player2) {
        //board = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9'}; Dit kan niet meer door de constructor van Field();
        board = new Field[9];
        for (int fieldIndex = 0; fieldIndex < 9; fieldIndex++) {
            board[fieldIndex] = new Field(fieldIndex +1);
        }
        maxRounds = board.length;
        drawCount = 0;

        this.player1 = player1;
        this.player2= player2;

        activePlayer = this.player1;
    }

    public void play(){
        for (round = 0; round < maxRounds; round++) {
            if (!gameWon){
                printBoard();
                setField();
            }
            if (gameWon || round == (maxRounds-1)){
                if (!gameWon) {
                    drawCount++;
                }
                scoreBoard();
                endGameChoices();
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int fieldIndex = 0; fieldIndex < board.length; fieldIndex++) {
            System.out.print(board[fieldIndex].getToken() + " ");
            if(fieldIndex == 2 || fieldIndex == 5) { //In de array staan op deze posities de nummer '3' en '6'.
                System.out.println();
            }
        }
        System.out.println();
    }

    public void setField(){
        System.out.println(activePlayer.getName() + ", please choose a field");
        int chosenField = PLAYERINPUT.nextInt();
        int chosenIndex = chosenField - 1;
        if(chosenIndex < 9 && chosenIndex >= 0) {
            if(board[chosenIndex].isEmpty()) {
                board[chosenIndex].setToken(activePlayer.getToken());
                winCheck();
                if (!gameWon) {
                    changePlayer();
                }
                if (gameWon){
                    activePlayer.addScore();
                    changePlayer();
                }
            } //of al bezet is
            else {
                maxRounds++;
                System.out.println("this field is not available, choose another");
            }
        }// als het veld niet bestaat
        else {
            maxRounds++;
            System.out.println("the chosen field does not exist, try again");
        }
    }

    public void changePlayer(){
        if(activePlayer == player1) {
            activePlayer = player2;
        }// anders
        else {
            activePlayer = player1;
        }
    }

    public void winCheck() {
        if (board[0].getToken() == board[1].getToken() && board[0].getToken() == board[2].getToken()){
            gameWon = true;
        }
        if (board[3].getToken() == board[4].getToken() && board[3].getToken() == board[5].getToken()){
            gameWon = true;
        }
        if (board[6].getToken() == board[7].getToken() && board[6].getToken() == board[8].getToken()){
            gameWon = true;
        }
        if (board[6].getToken() == board[4].getToken() && board[6].getToken() == board[2].getToken()){
            gameWon = true;
        }
        if (board[0].getToken() == board[4].getToken() && board[0].getToken() == board[8].getToken()){
            gameWon = true;
        }
        if (board[0].getToken() == board[3].getToken() && board[0].getToken() == board[6].getToken()){
            gameWon = true;
        }
        if (board[1].getToken() == board[4].getToken() && board[1].getToken() == board[7].getToken()){
            gameWon = true;
        }
        if (board[2].getToken() == board[5].getToken() && board[2].getToken() == board[8].getToken()){
            gameWon = true;
        }
    }

    public void endGameChoices() {
        System.out.println("The game has ended. Please select one of the following options: " +
                "\n'1': Start again." + "\n'2': Change players." + "\n'3': Quit game.");
            endGameChoice = PLAYERINPUT.nextInt();
            if (endGameChoice >= 1 && endGameChoice <= 3) {
                switch (endGameChoice) {
                    case 1:
                        board = new Field[9];
                        for (int fieldIndex = 0; fieldIndex < 9; fieldIndex++) {
                            board[fieldIndex] = new Field(fieldIndex + 1);
                        }
                        maxRounds = (board.length + 1);
                        round = 0;
                        gameWon = false;
                        break;
                    case 2:
                        System.out.println("What is the name of player one?");
                        String player1Name = PLAYERINPUT.next();
                        player1 = new Player(player1Name, 'x');
                        System.out.println("What is the name of player two?");
                        String player2Name = PLAYERINPUT.next();
                        player2 = new Player(player2Name, 'o');

                        board = new Field[9];
                        for (int fieldIndex = 0; fieldIndex < 9; fieldIndex++) {
                            board[fieldIndex] = new Field(fieldIndex + 1);
                        }
                        maxRounds = (board.length + 1);
                        round = 0;
                        gameWon = false;
                        activePlayer = player1;
                        break;
                    default:
                        round = maxRounds;
                        break;
                }
            } else {
                System.out.println("Option not available, please try again.");
            }
    }

    public void scoreBoard(){
        System.out.println();
        System.out.println("Player One wins: " + player1.getScore());
        System.out.println("Player Two wins: " + player2.getScore());
        System.out.println("Amount of draws: " + drawCount);
        System.out.println();
    }
}


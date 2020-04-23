package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class CustomGame {
    ArrayList playerNames = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public CustomGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public void addPlayer(String playerName) {
        playerNames.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        displayAddedPlayer(playerName);
    }

    private void displayAddedPlayer(String playerName) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerNames.size());
    }

    public int howManyPlayers() {
        return playerNames.size();
    }

    public void roll(int dieFace) {
        displayCurrentPlayerName();
        displayRollDieFace(dieFace);

        if (inPenaltyBox[currentPlayer]) {
            if (isOdd(dieFace)) {
                exitFromPenaltyBox();
            } else {
                staysInPenaltyBox();
                return;
            }
        }

        moveCurrentPlayer(dieFace);
        displayCurrentPlayerLocation();

        displayCurrentCategory();
        askQuestion();
    }

    private void staysInPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
        displayWhenCurrentPlayerStaysInPenaltyBox();
    }

    private void exitFromPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
        displayWhenCurrentPlayerOutOfPenaltyBox();
    }

    private boolean isOdd(int dieFace) {
        return dieFace % 2 != 0;
    }

    private void displayWhenCurrentPlayerStaysInPenaltyBox() {
        System.out.println(playerNames.get(currentPlayer) + " is not getting out of the penalty box");
    }

    private void displayWhenCurrentPlayerOutOfPenaltyBox() {
        System.out.println(playerNames.get(currentPlayer) + " is getting out of the penalty box");
    }

    private void displayCurrentCategory() {
        System.out.println("The category is " + currentCategory());
    }

    private void displayCurrentPlayerLocation() {
        System.out.println(playerNames.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
    }

    private void moveCurrentPlayer(int dieFace) {
        places[currentPlayer] = places[currentPlayer] + dieFace;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
    }

    private void displayRollDieFace(int dieFace) {
        System.out.println("They have rolled a " + dieFace);
    }

    private void displayCurrentPlayerName() {
        System.out.println(playerNames.get(currentPlayer) + " is the current player");
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0) return "Pop";
        if (places[currentPlayer] == 4) return "Pop";
        if (places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1) return "Science";
        if (places[currentPlayer] == 5) return "Science";
        if (places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2) return "Sports";
        if (places[currentPlayer] == 6) return "Sports";
        if (places[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(playerNames.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == playerNames.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == playerNames.size()) currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(playerNames.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == playerNames.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(playerNames.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == playerNames.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}

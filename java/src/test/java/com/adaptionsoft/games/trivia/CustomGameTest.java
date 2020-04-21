package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.util.RememberAllWrittenTextPrintStream;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomGameTest {

    @Test
    public void shouldPrintCorrectAnswerMessage(){

        //Given
        CustomGame customGame = new CustomGame();
        customGame.add("Chet");
        RememberAllWrittenTextPrintStream ps =new RememberAllWrittenTextPrintStream(System.out);
        System.setOut(ps);
        //When
         customGame.wasCorrectlyAnswered();
        //Them
        assertThat(ps.getAllWrittenText()).isEqualTo("Answer was correct!!!!\nChet now has 1 Gold Coins.\n");
    }
}

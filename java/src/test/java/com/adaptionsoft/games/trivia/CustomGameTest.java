package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.util.RememberAllWrittenTextPrintStream;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PrintStream.class)
public class CustomGameTest {

    @Test
    @Ignore
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

    @Test
    @Ignore
    public void shouldPrintCorrectAnswerMessageUsingPowerMock(){

        //Given
        CustomGame customGame = new CustomGame();
        customGame.add("Chet");

        PrintStream spy = PowerMockito.spy(System.out);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(spy).println(argumentCaptor.capture());
        System.setOut(spy);


        //When
        customGame.wasCorrectlyAnswered();
        //Them
//        Mockito.verify(spy,Mockito.times(2)).println(Mockito.anyString());
        assertThat(argumentCaptor.getAllValues().get(0)).isEqualTo("Answer was correct!!!!");
        assertThat(argumentCaptor.getAllValues().get(1)).isEqualTo("Chet now has 1 Gold Coins.");
        assertThat(argumentCaptor.getAllValues().size()).isEqualTo(2);
    }

}

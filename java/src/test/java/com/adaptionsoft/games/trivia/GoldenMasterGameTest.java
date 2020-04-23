
package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.util.RememberAllWrittenTextPrintStream;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GoldenMasterGameTest {

	private static boolean notAWinner;
	private static boolean notACustomWinner;

	@Test
	public void testGoldenMasterGameRunner() {
		//Given
		RememberAllWrittenTextPrintStream ps1 = new RememberAllWrittenTextPrintStream(System.out);
		RememberAllWrittenTextPrintStream ps2 = new RememberAllWrittenTextPrintStream(System.out);

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		CustomGame customGame = new CustomGame();
		customGame.addPlayer("Chet");
		customGame.addPlayer("Pat");
		customGame.addPlayer("Sue");

		//when
		int number = 1;
		boolean isWrongAnswer = false;

		do {
			System.setOut(ps1);
			aGame.roll(number);
			System.setOut(ps2);
			customGame.roll(number);
			if (isWrongAnswer) {
				System.setOut(ps1);
				notAWinner = aGame.wrongAnswer();
				System.setOut(ps2);
				notACustomWinner = customGame.wrongAnswer();
			} else {
				System.setOut(ps1);
				notAWinner = aGame.wasCorrectlyAnswered();
				System.setOut(ps2);
				notACustomWinner = customGame.wasCorrectlyAnswered();
			}
			number += 1;

			if(number == 7){
				isWrongAnswer = !isWrongAnswer;
				number = 1;
			}



			assertThat(notAWinner).isEqualTo(notACustomWinner);
			assertThat(ps1.getAllWrittenText()).isEqualTo(ps2.getAllWrittenText());


		} while (notAWinner);


		//then
	}

}


package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.CustomGame;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class GoldenMasterGameTest {

	private static boolean notAWinner;

	@Test
	public void testGoldenMasterGameRunner() {
		//Given
		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		CustomGame customGame = new CustomGame();
		customGame.add("Chet");
		customGame.add("Pat");
		customGame.add("Sue");

		//when
		int number = 1;
		boolean isWrongAnswer = false;

		do {

			aGame.roll(number);

			if (isWrongAnswer) {
				notAWinner = aGame.wrongAnswer();
				isWrongAnswer = false;
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
				isWrongAnswer = true;
			}
			number += 1;

			if(number == 7){
				number = 1;
			}

			assertThat(notAWinner).isEqualTo("Rock Question 1");


		} while (notAWinner);


		//then
	}

}

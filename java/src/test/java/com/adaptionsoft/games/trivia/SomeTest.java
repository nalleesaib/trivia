package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeTest {

	// player roll1-6

	@Test
	public void testCreateRockQuestionIsEqualToRockQuestion1() {
		//Given
		Game aGame = new Game();

		//when

		//then
		assertThat(aGame.createRockQuestion(1)).isEqualTo("Rock Question 1");
	}

	@Test
	public void testWhenGameHasNoPayer() {
		//Given
		Game aGame = new Game();

		//when


		//then
		assertThat(aGame.howManyPlayers()).isEqualTo(0);
	}

	@Test
	public void testWhenAPlayerIsAddedAddMethodShouldReturnTrue() {
		//Given
		Game aGame = new Game();

		//when
		String playerName= "Khalil";

		//then
		assertThat(aGame.add(playerName)).isEqualTo(true);
		assertThat(aGame.howManyPlayers()).isEqualTo(1);
	}



	@Test
	public void testPlayerIsEqualTo3() {
		//Given
		Game aGame = new Game();

		//when
		aGame.add("Khalil");
		aGame.add("Williams");
		aGame.add("Andre");

		//then
		assertThat(aGame.howManyPlayers()).isEqualTo(3);
	}
}

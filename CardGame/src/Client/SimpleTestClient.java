package Client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallBackGUI;
import view.GameEngineCallbackImpl;

public class SimpleTestClient {

	public static void main(String args[]) {
		final GameEngine gameEngine = new GameEngineImpl();

		// call method in Validator.jar to test *structural* correctness
		// just passing this does not mean it actually works .. you need to test
		// yourself!
		// pass false if you want to disable logging .. (i.e. once it passes)

		// create two test players
//	      Player[] players = new Player[] { new SimplePlayer("2", "The Shark", 1000), new SimplePlayer(
//	         "1", "The Loser", 500) };

		// add logging callback
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		gameEngine.addGameEngineCallback(new GameEngineCallBackGUI(gameEngine));

//	      for (Player player : players)
//	      {
//	         gameEngine.addPlayer(player);
//	         gameEngine.placeBet(player, 100);
//	         gameEngine.dealPlayer(player, 100);
//	      }
//	      
		// all players have played so now house deals
		// GameEngineCallBack.houseResult() is called to log all players (after results
		// are calculated)
		// gameEngine.dealHouse(10);

	}

}

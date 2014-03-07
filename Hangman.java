import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		//////////////////////////////////////////////ARGUMENT CHECKS///////////////////////////////////////////////////////////////
		
		String player1 ="";
		String player2 ="";
		String player3 ="";
		
        /**
         * If the person enters only one or two arguments they will be told 
         * that the number of arguments they entered are incorrect, and will
         * have the chance to enter the arguments that are specified. Also,
         * if they enter an argument that is not available, they will be notified,
         * and asked to enter argumets that make sense.
         */
        if(args.length == 0 || args.length == 1){
			System.out.println("You entered the incorrect number of arguments");
			System.exit(1);
		}
		if(args.length == 2){
			player1 = args[0];
			player2 = args[1];
			
			//checking correct args
			if(!player1.equalsIgnoreCase("computer") && !player1.equalsIgnoreCase("human") && !player1.equalsIgnoreCase("naive") && !player1.equalsIgnoreCase("random") && !player1.equalsIgnoreCase("cutthroat")){
				System.out.println("You entered incorrect arguments");
			}
			if(!player2.equalsIgnoreCase("computer") && !player2.equalsIgnoreCase("human") && !player2.equalsIgnoreCase("naive") && !player2.equalsIgnoreCase("random") && !player2.equalsIgnoreCase("cutthroat")){
				System.out.println("You entered incorrect arguments");
			}
		}
		if(args.length == 3){
			player1 = args[0];
			player2 = args[1];
			player3 = args[2];
			
			//checking correct args
			if(!player1.equalsIgnoreCase("-L") && !player1.equalsIgnoreCase("computer") && !player1.equalsIgnoreCase("human") && !player1.equalsIgnoreCase("naive") && !player1.equalsIgnoreCase("random") && !player1.equalsIgnoreCase("cutthroat")){
				System.out.println("You entered incorrect arguments");
			}
			if(!player2.equalsIgnoreCase("-L") && !player2.equalsIgnoreCase("computer") && !player2.equalsIgnoreCase("human") && !player2.equalsIgnoreCase("naive") && !player2.equalsIgnoreCase("random") && !player2.equalsIgnoreCase("cutthroat")){
				System.out.println("You entered incorrect arguments");
			}
			if(!player3.equalsIgnoreCase("-L") && !player3.equalsIgnoreCase("computer") && !player3.equalsIgnoreCase("human") && !player3.equalsIgnoreCase("naive") && !player3.equalsIgnoreCase("random") && !player3.equalsIgnoreCase("cutthroat")){
				System.out.println("You entered incorrect arguments");
			}
			
			/**
             * Here we account for "-L", and if a player is using it. We go through
             * the players to check to see if they have a "-L" in them, and if 
             * they do the try-catch block will be run, and an output error
             * exception message will appear.If not the program continues as 
             * it normally would.
             */
            if(player1.equalsIgnoreCase("-L")){
				FileOutputStream f = null;
				try {
					f = new FileOutputStream("logged-gameplay.output");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintStream out = new PrintStream(f, true);
				System.setOut(out);
				
				player1 = args[1];
				player2 = args[2];
			}
			if(player2.equalsIgnoreCase("-L")){
				FileOutputStream f = null;
				try {
					f = new FileOutputStream("logged-gameplay.output");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintStream out = new PrintStream(f, true);
				System.setOut(out);
				
				player1 = args[0];
				player2 = args[2];
			}
			if(player3.equalsIgnoreCase("-L")){
				FileOutputStream f = null;
				try {
					f = new FileOutputStream("logged-gameplay.output");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintStream out = new PrintStream(f, true);
				System.setOut(out);
				
			}
		}
		if(args.length > 3){
			System.out.println("You entered the incorrect number of arguments");
			System.exit(1);
		}
		
		
		
		
		////////////////////////////////////////////////////////////BEGINNING///////////////////////////////////////////////////////////////////////////////
		
		
		
		if(player1.equalsIgnoreCase("computer") && player2.equalsIgnoreCase("human")){
			System.out.println("Hangman!");
			int guessCount = 0;
			Scanner keyboard = new Scanner(System.in);
			char charGuess = ' ';
			String inputCheck = "";
			
			
			/**
             * We start with the human player. The game starts, and we implemented 
             * the methods to allow a random word to be chosen, and a number of 
             * spaces are put in place. An output statement asks for the user's
             * guess, and the game begins. This is done at the beginning of every
             * combo of arguments.
             */
			HumanPlayer plyr1 = new HumanPlayer();
			plyr1.setNumGuesses(guessCount);
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			System.out.println(comp.getWord());
			//char word[] = new char[comp.getWord().length()];
			HangmanBoard board = new HangmanBoard(comp.getWord());
			
			
			//PUT CURRENT BOARD HERE
			
			String guessed = "";
			while(guessCount < 6){
				
				
				int i = 0;
				while(i == 0){
					
					/**
                     * If the person tries to guess more than one charcater at a 
                     * time the program will let them know that entering more than
                     * one letter is not an option.
                     */
                    System.out.println("Make Your Guess");
					inputCheck = keyboard.next();
						
                        //Checks user input
                        if(inputCheck.length() > 1){
							System.out.println("You Can Only Enter One Character At The Time");
						}
						else{
							charGuess = inputCheck.charAt(0);
							
						}
						
						//Checks letters already guessed
                        for(int j = 0; j < guessed.length(); j++){
							if(charGuess == guessed.charAt(j)){
								System.out.println("You have already guessed that letter!");
							}
						
						}
						guessed = guessed + charGuess;
						i = 1;
					}
				
				//Checks if guess was correct, or if they entered a letter they
                //had already guessed.
                if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(plyr1.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					plyr1.setNumGuesses(guessCount);
					board.printBoard(plyr1.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				
				//If the person guesses the word correctly after 6 guesses they win
                //and the game quits. If they don't guess the word they get a sad
                //message and the game ends.
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}
			
			
			
		}
		
		//Same as above, but this is for the combination of the human and the
        //computer.
        if(player1.equalsIgnoreCase("human") && player2.equalsIgnoreCase("computer")){
			System.out.println("Hangman!");
			int guessCount = 0;
			Scanner keyboard = new Scanner(System.in);
			char charGuess = ' ';
			String inputCheck = "";
			
			
			
			HumanPlayer plyr1 = new HumanPlayer();
			plyr1.setNumGuesses(guessCount);
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			System.out.println(comp.getWord());
			HangmanBoard board = new HangmanBoard(comp.getWord());
			
			
			//PUT CURRENT BOARD HERE
			
			String guessed = "";
			while(guessCount < 6){
				
				
				int i = 0;
				while(i == 0){
					
					System.out.println("Make Your Guess");
					inputCheck = keyboard.next();
						if(inputCheck.length() > 1){
							System.out.println("You Can Only Enter One Character At The Time");
						}
						else{
							charGuess = inputCheck.charAt(0);
							
						}
						
						for(int j = 0; j < guessed.length(); j++){
							if(charGuess == guessed.charAt(j)){
								System.out.println("You have already guessed that letter!");
							}
						
						}
						guessed = guessed + charGuess;
						i = 1;
					}
				
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(plyr1.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					plyr1.setNumGuesses(guessCount);
					board.printBoard(plyr1.getNumGuesses());
					System.out.println("Already Guessed" + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}
			
			
			
		}
		
		
		
		//NAIVE PLAYER
		if(player1.equalsIgnoreCase("computer") && player2.equalsIgnoreCase("naive")){
			System.out.println("Hangman!");
			char charGuess = ' ';
			int guessCount = 0;
			
			
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			HangmanBoard board = new HangmanBoard(comp.getWord());
			
			NaiveComputerPlayer naive = new NaiveComputerPlayer();
			String guessed = "";
			while(guessCount < 6){
				charGuess = naive.makeGuess();
				guessed += charGuess;
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(naive.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					naive.setNumGuesses(guessCount);
					board.printBoard(naive.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}
			
		}
			
		if(player1.equalsIgnoreCase("naive") && player2.equalsIgnoreCase("computer")){
			System.out.println("Hangman!");
			char charGuess = ' ';
			int guessCount = 0;
			
			
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			HangmanBoard board = new HangmanBoard(comp.getWord());
			
			NaiveComputerPlayer naive = new NaiveComputerPlayer();
			String guessed = "";
			while(guessCount < 6){
				charGuess = naive.makeGuess();
				guessed = guessed + charGuess;
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(naive.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					naive.setNumGuesses(guessCount);
					board.printBoard(naive.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}		
			
		}	
			
		
		if(player1.equalsIgnoreCase("computer") && player2.equalsIgnoreCase("random")){
			System.out.println("Hangman!");
			char charGuess = ' ';
			int guessCount = 0;
				
				
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			HangmanBoard board = new HangmanBoard(comp.getWord());
				
			RandomComputerPlayer random = new RandomComputerPlayer();
			
			String guessed = "";
			while(guessCount < 6){
				charGuess = random.makeGuess();
				guessed += charGuess;
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(random.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					random.setNumGuesses(guessCount);
					board.printBoard(random.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}		
		}
			
		
		//Same as above, but this is for the combination of the random guesser and
        //the computer.
		if(player1.equalsIgnoreCase("random") && player2.equalsIgnoreCase("computer")){
			System.out.println("Hangman!");
			char charGuess = ' ';
			int guessCount = 0;
				
				
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			HangmanBoard board = new HangmanBoard(comp.getWord());
				
			RandomComputerPlayer random = new RandomComputerPlayer();
			
			String guessed = "";
			while(guessCount < 6){
				charGuess = random.makeGuess();
				guessed += charGuess;

				
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(random.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					random.setNumGuesses(guessCount);
					board.printBoard(random.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}		
		}
			
		
		//Same as above, but this is for the combination of the cutthroat player
        // and the computer.
        if(player1.equalsIgnoreCase("cutthroat") && player2.equalsIgnoreCase("computer")){
			System.out.println("Hangman!");
			char charGuess = ' ';
			int guessCount = 0;
				
				
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			HangmanBoard board = new HangmanBoard(comp.getWord());
			
			CutThroatComputerPlayer cut = new CutThroatComputerPlayer();
			String guessed = "";	
			while(guessCount < 6){

				charGuess = cut.makeGuess(board.getBoard(), guessed);
				guessed += charGuess;
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(cut.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					cut.setNumGuesses(guessCount);
					board.printBoard(cut.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}
			
				
			
		}
			
		if(player1.equalsIgnoreCase("computer") && player2.equalsIgnoreCase("cutthroat")){
			System.out.println("Hangman!");
			char charGuess = ' ';
			int guessCount = 0;
				
				
			ComputerPlayer comp = new ComputerPlayer();
			comp.setWord();
			HangmanBoard board = new HangmanBoard(comp.getWord());
			
			CutThroatComputerPlayer cut = new CutThroatComputerPlayer();
		

			String guessed = "";
			while(guessCount < 6){
				
				charGuess = cut.makeGuess(board.getBoard(), guessed);
				guessed += charGuess;
				if(board.checkGuess(charGuess)){
					board.setCurrent(charGuess);
					System.out.println("Your Guess Was Correct!!!");
					board.printBoard(cut.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				else{
					System.out.println("Your Guess Was Incorrect!!!");
					guessCount++;
					cut.setNumGuesses(guessCount);
					board.printBoard(cut.getNumGuesses());
					System.out.println("Already Guessed " + guessed);
					//PUT CURRENT BOARD HERE
				}
				
				if(board.getWord().equalsIgnoreCase(board.getBoard()) && guessCount < 6){
					System.out.println("Game Over! You guessed the word!");
					guessCount = 9;
				}
				if(guessCount < 9 && guessCount > 5){
					System.out.println("Game Over! You died :-( The word was " + board.getWord());
				}
			}
			
				
			
		}	
			
	}

}



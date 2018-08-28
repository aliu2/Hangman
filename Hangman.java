import java.util.*;
import java.io.*;

public class Hangman
{
	//Method to create string of correct guesses
	public static String generateString(String word, List<Character> correctGuesses)
	{
		String newWord = "";
		for (int i = 0; i < word.length(); i++)
		{
			//If a letter has been guessed correctly then display that letter
			if (correctGuesses.contains(word.charAt(i)))
			{
				newWord += word.substring(i,i+1);
			}

			else if (word.substring(i,i+1).equals(" "))
			{
				newWord += " ";
			}

			//If a letter hasn't been guessed then put an underscore there
			else
			{
				newWord += "_";
			}
		}
		return newWord;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		List<Character> correctGuesses = new ArrayList<Character>();
		List<Character> guesses = new ArrayList<Character>();
		System.out.println("Enter a word to be guessed (lowercase letters only):");
		String word = in.nextLine();
		int lives = 10;

		//Hides word to be guessed from rest of players 
		//as this game runs in cmd
		for (int i = 0; i < 40; i++)
		{
			System.out.println("|");
		}
		System.out.println("Start guessing!");
		System.out.println(generateString(word, correctGuesses));

		//Loop keeps going until all letters have been guessed
		while (generateString(word, correctGuesses).indexOf('_') >= 0)
		{
			char nextGuess = in.next().charAt(0);

			//Catches duplicate guesses
			if (guesses.contains(nextGuess))
			{
				System.out.printf("You've already guessed %c!\n", nextGuess);
				System.out.println(generateString(word, correctGuesses));
			}
      
			//Checks to see if a new guess is in the word
			//and adds it to correctGuesses and guesses if it is
			else if (word.indexOf(nextGuess) >= 0)
			{
				correctGuesses.add(nextGuess);
				guesses.add(nextGuess);
				System.out.println(generateString(word, correctGuesses));
			}
      
			//Adds incorrect guesses to guesses and decrements a life
			else
			{
				System.out.println(nextGuess + " is not in the word.");
				guesses.add(nextGuess);
				lives--;
				System.out.printf("You have %d lives left.\n", lives);
				System.out.println(generateString(word, correctGuesses));
			}

			//If lives are all used, then breaks
			if (lives == 0)
			{
				System.out.println("You lose!");
				break;
			}
		}
		
		//Checks if the whole word has been guessed
		//and prints congratulations if it has
		if (generateString(word, correctGuesses).indexOf('_') == -1)
		{
			System.out.println("Congratulations!");
		}
	}
}

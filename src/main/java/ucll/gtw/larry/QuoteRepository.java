package ucll.gtw.larry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteRepository {

	private List<String> quotes = new ArrayList<String>();

	public QuoteRepository() {
		super();
		quotes.add("Great is the art of beginning, but greater is the art of ending.");
		quotes.add("Love is like pi: natural, irrational, and very important.");
		quotes.add("If you're not confused, you're not paying attention.");
		quotes.add("Pick the flower when it is ready to be picked.");
		quotes.add("Only your real friends tell you when your face is dirty.");
		quotes.add("I don't think much of a man who is not wiser today than he was yesterday.");
		quotes.add("Yes we can!");
		quotes.add("I Have a Dream");
		quotes.add("My work is a game, a very serious game.");
		quotes.add("When you say, \'I wrote a program that crashed Windows,\' people just stare at you blankly and say, \'Hey, I got those with the system, for free.\'");
	}

	public String getRandomQuote() {
		Random random = new Random();
		int position = random.nextInt(quotes.size());
		String quote = quotes.get(position);
		return quote;
	}

}

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public ArrayList<Room> rooms;
	public Room cur;
	public Player p;

	public Game() {
		rooms = new ArrayList<>();
		rooms.add(new Bedroom());	
		rooms.add(new Bathroom());
		rooms.add(new Kitchen());
		rooms.add(new Basement());
		rooms.add(new LivingRoom());
		rooms.add(new Outside());
		cur = rooms.get(0);
	}

	/**
	 * Main engine of the game. Constantly asks for input until the game is over, and can restart as necessary
	 */
	public void play() {
		Scanner in = new Scanner(System.in);
		int i = 0;
		Parser parse = new Parser();
		System.out.println("Welcome to the text adventure game!");
		System.out.println("To begin, enter a name for your character!");
		String name = in.nextLine();
		Player p = new Player(name);
		System.out.println("You wake up in a house. The goal of the game is to save 3 children and defeat the monster. Good Luck "+ name + "!!");
		System.out.println("You are in the bedroom.");
		System.out.println();
		System.out.println("For a list of valid commands, type help");
		p.printStats();

		while(!cur.isGameOver() && !p.isGameOver() && i != 666) {
			System.out.printf("%d ======\n", i);
			i++;
			System.out.println("> ");
			cur = parse.parse(in, cur, p, rooms);
		}
		if (i == 666) {
			System.out.println("You have summoned Satan and begun the apocalypse. You go directly to Hell"); //JASOM AL-ADSANI
		}
		while (true) {
			System.out.println("Would you like to play again? Yes or No");
			String answer = in.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				Game g = new Game();
				g.play();
			} else if (answer.equalsIgnoreCase("no")){
				return;
			}
		}
	}
}

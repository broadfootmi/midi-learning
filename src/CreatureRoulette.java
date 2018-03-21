import java.util.Random;
import java.util.ArrayList;

public class CreatureRoulette {

	private Random rng;

	public CreatureRoulette () {
		
		rng = new Random();

	}

	public Creature chooseCreature ( Population pop ) {

		ArrayList< Creature > creatures = pop.getCreatures();
		Creature chosen = creatures.get(0);
		
		int totalFitness = 0;
		for( Creature c : creatures ) {
			totalFitness += c.getFitness();
		}

		int chosenFitness = rng.nextInt() % totalFitness;

		int fitnessCount = 0;
		for( Creature c : creatures ) {
			
			fitnessCount += c.getFitness();
			
			if ( fitnessCount >= chosenFitness ) {
				chosen = c;
				break;
			}	
		}

		return chosen;

	}

}


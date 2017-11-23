import javax.swing.*;

public class CreatureInfoPanel extends JPanel { 

	Creature creature;

	JLabel name;
	//image for image
	//textlabel for notes
	//panel for fitness
	//	textlabel FIT
	//	textlabel 36

	CreatureInfoPanel( Creature creature ) {
		
		this.creature = creature;

		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		name = new JLabel( creature.getName() ); 

		add( this.name );

	}
	
	//paintComponent() {

	//	name.repaint();
		//image.set("crea");
		//image.repaint();
		//text.setText("FIT")
		//text2.setText(c.getFitness());
	//}
	
	//image.paintComponent() {

		//setColor( c.sequenceAsColor() );

}


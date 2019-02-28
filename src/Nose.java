import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Nose implements Command
{
	@Override
	public void execute(Finch finch)
	{
		/* Duration in seconds */
		int duration = 3;
		
		/* Color */
		int r = 255;
		int g = 0;
		int b = 0;
		
		/* Set LED */
		finch.setLED(r, g, b, duration * 1000);
	}
}

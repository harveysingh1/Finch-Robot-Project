import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Forward implements Command
{
	private int seconds = 0;
	
	public Forward(int secs)
	{
		seconds = secs;
	}

	@Override
	public void execute(Finch finch)
	{
		int velocity = 100;
		finch.setWheelVelocities(velocity, velocity, seconds * 1000);
	}
}

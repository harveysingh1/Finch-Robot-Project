import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Backward implements Command
{
	private int seconds = 0;
	
	public Backward(int secs)
	{
		seconds = secs;
	}
	
	@Override
	public void execute(Finch finch)
	{
		int velocity = 100;
		finch.setWheelVelocities(-velocity, -velocity, seconds * 1000);
	}
}

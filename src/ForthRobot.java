import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class ForthRobot
{
	/* Finch */
	private Finch finch = null;
	
	/* Maximum number of commands on the stack at any time */
	private static final int MAX_COMMANDS = 5;
	
	/* Stack of commands */
	private Stack<Command> cmds;
	
	/* Counts (entered) */
	private int fwdCount = 0;
	private int bckCount = 0;
	private int nosCount = 0;
	
	/* Deleted */
	private int delCount = 0;
	
	/**
	 * Create a Forth robot and initialize it
	 */
	public ForthRobot()
	{
		finch = new Finch();
		cmds = new Stack<Command>();
	}
	
	/**
	 * Parse command string and return corresponding command object
	 * Return null if string is erroneous
	 */
	public Command parse(String s)
	{	
		if(s == null || s.equals(""))
		{
			return null;
		}
		
		StringTokenizer tok = new StringTokenizer(s, " ");
		String cmdString = tok.nextToken().toLowerCase();
		
		if(cmdString == null)
		{
			return null;
		}
		
		Command cmd = null;
		if(cmdString.equals("fwd") || cmdString.equals("bck"))
		{
			String secondString = null;
			int seconds = 0;
			
			try
			{
				secondString = tok.nextToken();
				seconds = Integer.parseInt(secondString);
			}
			catch(NoSuchElementException e)
			{
				return null;
			}
			catch(NumberFormatException e)
			{
				return null;
			}
			
			if(cmdString.equals("fwd"))
			{
				cmd = new Forward(seconds);
			}
			else
			{
				cmd = new Backward(seconds);
			}
		}
		else if(cmdString.equals("nos"))
		{
			cmd = new Nose();
		}
		
		return cmd;
	}
	
	/**
	 * Execute the next command, the one on the top of the stack.
	 */
	public void execute()
	{
		/* Make sure the stack is not empty */
		if(! cmds.empty())
		{
			/* Pop the stack to reduce size by 1 */
			Command cmd = cmds.pop();
			
			/* Execute the command */
			cmd.execute(finch);
		}
		
	}
	
	/**
	 * Add a new command to the top of the stack
	 */
	public void add(Command cmd)
	{
		/* Make sure there is room for a new command */
		
		int size = cmds.size();
		if(size < MAX_COMMANDS)
		{
			/* Add the command to the top of the stack */
			cmds.push(cmd);
			
			/* Update counts */
			if(cmd instanceof Forward)
			{
				fwdCount += 1;
			}
			else if(cmd instanceof Backward)
			{
				bckCount += 1;
			}
			else if(cmd instanceof Nose)
			{
				nosCount += 1;
			}
		}
	}
	
	/**
	 * Discard the command on the top of the stack
	 */
	public void delete()
	{
		/* Make sure there is a command to delete */
		if(! cmds.empty())
		{
			cmds.pop();
			
			/* Update counts */
			delCount += 1;
		}
	}
	
	/**
	 * Clean up
	 */
	public void quit()
	{
		if(finch != null)
		{
			finch.quit();
		}
	}

	/* Getters */
	public int getFwdCount()
	{
		return fwdCount;
	}

	public int getBckCount()
	{
		return bckCount;
	}

	public int getNosCount()
	{
		return nosCount;
	}

	public int getDelCount()
	{
		return delCount;
	}
}

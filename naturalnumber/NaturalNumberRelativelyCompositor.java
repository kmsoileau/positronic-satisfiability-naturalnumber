package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberRelativelyCompositor extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -4756992399919407651L;

	public NaturalNumberRelativelyCompositor(
			INaturalNumber M, 
			INaturalNumber N,
			INaturalNumber CF) throws Exception
	{
		INaturalNumber Two=new NaturalNumber();
		INaturalNumber A=new NaturalNumber();
		INaturalNumber B=new NaturalNumber();
		INaturalNumber P=new NaturalNumber();
			
		IProblem p=new Conjunction(
				new NaturalNumberFixer(Two,2),
	    		new NaturalNumberMultiplier(CF,A,M),
	    		new NaturalNumberMultiplier(CF,B,N),
	    		new NaturalNumberAdder(P,Two,CF));
	
	    this.setClauses(p.getClauses());
	  }
}

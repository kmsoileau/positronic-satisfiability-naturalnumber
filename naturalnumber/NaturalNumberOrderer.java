package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberOrderer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = 4539757428746135087L;
	private static int nNOCount;
	
	public NaturalNumberOrderer(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		//X<=Y
		//There exists Z such that Y=X+Z
		INaturalNumber C=new NaturalNumber("NaturalNumberOrderer$"+ nNOCount++);
		IProblem p1=new NaturalNumberAdder(X,new NaturalNumber("NaturalNumberOrderer$"+ nNOCount++),Y,C);
		//Constrain overflow bit
		IProblem p2=new BitFixer(C.getBooleanVariable(NaturalNumber.getLength()-1),false);
		this.setClauses(new Conjunction(p1,p2).getClauses());
	}
	
	public NaturalNumberOrderer(INaturalNumber X, long y) throws Exception
	{
		INaturalNumber dummy=new NaturalNumber("NaturalNumberOrderer$"+ nNOCount++);
		IProblem p1=new NaturalNumberFixer(dummy,y);
		IProblem p2=new NaturalNumberOrderer(X,dummy);
		this.setClauses(new Conjunction(p1,p2).getClauses());
	}
	
	public NaturalNumberOrderer(long x, INaturalNumber Y) throws Exception
	{
		INaturalNumber dummy=new NaturalNumber("NaturalNumberOrderer$"+ nNOCount++);
		IProblem p1=new NaturalNumberFixer(dummy,x);
		IProblem p2=new NaturalNumberOrderer(dummy,Y);
		this.setClauses(new Conjunction(p1,p2).getClauses());
	}
}

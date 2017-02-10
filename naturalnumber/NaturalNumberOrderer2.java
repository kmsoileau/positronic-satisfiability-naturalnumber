package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.BitEqualizer;
import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberOrderer2 extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -6694284316559827918L;
	private static int depthCount=0;
	
	public NaturalNumberOrderer2(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		depthCount++;
		IProblem p0011=null;
		IProblem p01=null;
		IProblem p10=null;
		
		if(depthCount==NaturalNumber.getLength())
		{
			p0011=new Conjunction(
					new BitEqualizer(X.getBooleanVariable(NaturalNumber.getLength()-1),
							Y.getBooleanVariable(NaturalNumber.getLength()-1)));
			p01=new Conjunction(
					new BitFixer(X.getBooleanVariable(NaturalNumber.getLength()-1),
							false),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength()-1),
							true));
			p10=new Conjunction(
					new BitFixer(X.getBooleanVariable(NaturalNumber.getLength()-1),
							true),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength()-1),
							false),
							MetaProblem.unsolvableProblem());
		}
		else
		{
			INaturalNumber Xr=new NaturalNumber();
			INaturalNumber Yr=new NaturalNumber();
			IProblem leftX=new NaturalNumberLeftShifter(X,Xr);
			IProblem leftY=new NaturalNumberLeftShifter(Y,Yr);
			IProblem recurse=new Conjunction(new NaturalNumberOrderer2(Xr,Yr),
					leftX,leftY);
			p0011=new Conjunction(
					new BitEqualizer(X.getBooleanVariable(NaturalNumber.getLength()-1),
							Y.getBooleanVariable(NaturalNumber.getLength()-1)),
					recurse);
			p01=new Conjunction(
					new BitFixer(X.getBooleanVariable(NaturalNumber.getLength()-1),
							false),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength()-1),
							true));
			p10=new Conjunction(
					new BitFixer(X.getBooleanVariable(NaturalNumber.getLength()-1),
							true),
					new BitFixer(Y.getBooleanVariable(NaturalNumber.getLength()-1),
							false),
							MetaProblem.unsolvableProblem());
		}
		this.setClauses(new Disjunction(p0011,p01,p10).getClauses());
	}
}

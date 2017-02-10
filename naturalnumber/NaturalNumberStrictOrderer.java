package positronic.satisfiability.naturalnumber;

import positronic.mathematica.satisfiability.Problem;
import positronic.satisfiability.elements.BitEqualizer;
import positronic.satisfiability.elements.BooleanLiteral;
import positronic.satisfiability.elements.Clause;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IBooleanLiteral;
import positronic.satisfiability.elements.IClause;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberStrictOrderer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = 1078331127802815825L;
	private static int nNOCount;

	public static IProblem orderer(INaturalNumber X, long y) throws Exception
	{
		INaturalNumber dummy=new NaturalNumber("NaturalNumberStrictOrderer$"+ nNOCount++);
		IProblem p1=new NaturalNumberFixer(dummy,y);
		IProblem p2=new NaturalNumberStrictOrderer(X,dummy);
		IProblem ret=Problem.newProblem();
		ret.setClauses(new Conjunction(p1,p2).getClauses());
		return ret;
	}

	public static IProblem orderer(long x, INaturalNumber Y) throws Exception
	{
		INaturalNumber dummy=new NaturalNumber("NaturalNumberStrictOrderer$"+ nNOCount++);
		IProblem p1=new NaturalNumberFixer(dummy,x);
		IProblem p2=new NaturalNumberStrictOrderer(dummy,Y);
		IProblem ret=Problem.newProblem();
		ret.setClauses(new Conjunction(p1,p2).getClauses());
		return ret;
	}

	public NaturalNumberStrictOrderer(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		this.setClauses(recursivep(X.size(), X, Y).getClauses());
	}

	public static IProblem recursivep(int n, INaturalNumber X, INaturalNumber Y) throws Exception
	{
		IProblem ret=null;
		if(n<1)
			ret=null;
		if(n==1)
		{
			IBooleanLiteral blx = BooleanLiteral.getBooleanLiteral(
					X.getBooleanVariable(0), true);
			IBooleanLiteral bly = BooleanLiteral.getBooleanLiteral(
					Y.getBooleanVariable(0), false);
			IClause cl1 = new Clause();
			cl1.add(blx);
			IClause cl2 = new Clause();
			cl2.add(bly);
			ret=Problem.newProblem();
			ret.add(cl1);
			ret.add(cl2);
		}
		if(n>1)
		{
			IBooleanLiteral blx = BooleanLiteral.getBooleanLiteral(
					X.getBooleanVariable(n-1), true);
			IBooleanLiteral bly = BooleanLiteral.getBooleanLiteral(
					Y.getBooleanVariable(n-1), false);
			IClause cl1 = new Clause();
			cl1.add(blx);
			IClause cl2 = new Clause();
			cl2.add(bly);
			IProblem first=Problem.newProblem();
			first.add(cl1);
			first.add(cl2);

			BitEqualizer second = 
					new BitEqualizer(
							X.getBooleanVariable(
									n-1),
									Y.getBooleanVariable(n-1)
							);
			ret = new Disjunction(first,
					new Conjunction(
							second, 
							recursivep(n-1,X,Y)
							)
					);
		}
		return ret;
	}
}

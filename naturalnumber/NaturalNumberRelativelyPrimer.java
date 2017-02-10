package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

/**
 * @todo test and demo this class
 *
 */
public class NaturalNumberRelativelyPrimer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -678441182597233147L;

	public NaturalNumberRelativelyPrimer(INaturalNumber M, INaturalNumber N) throws Exception
	{
		INaturalNumber One=new NaturalNumber();
		INaturalNumber A=new NaturalNumber();
		INaturalNumber B=new NaturalNumber();
		INaturalNumber P=new NaturalNumber();
		INaturalNumber Q=new NaturalNumber();

		IProblem p=new Conjunction(
				new NaturalNumberFixer(One,1)
				,new NaturalNumberMultiplier(M,A,P)
				,new NaturalNumberMultiplier(N,B,Q)
				,new Disjunction(new NaturalNumberAdder(Q,One,P),new NaturalNumberAdder(P,One,Q)));

		this.setClauses(p.getClauses());
	}
}
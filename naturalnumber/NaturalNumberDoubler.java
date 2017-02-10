package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2004</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

import positronic.satisfiability.elements.BitEqualizer;
import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberDoubler extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -3940960055724199667L;

	public NaturalNumberDoubler(INaturalNumber X, INaturalNumber Z) throws Exception
	{
		IProblem p=new BitFixer(Z.getBooleanVariable(0),false);
		for(int i=0;i<NaturalNumber.getLength()-1;i++)
		{
			p=new Conjunction(p,new BitEqualizer(Z.getBooleanVariable(i+1),X.getBooleanVariable(i)));
		}
		p=new Conjunction(p,new BitFixer(X.getBooleanVariable(NaturalNumber.getLength()-1),false));
		this.setClauses(p.getClauses());
	}
}
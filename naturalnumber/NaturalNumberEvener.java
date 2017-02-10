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
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

/**
 * @todo test and demo this class
 *
 */
public class NaturalNumberEvener extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 3224751661986678000L;

	public NaturalNumberEvener(INaturalNumber Z) throws Exception
  {
		INaturalNumber Two=new NaturalNumber();
		INaturalNumber K=new NaturalNumber();
		
		IProblem p=new Conjunction(
    		new NaturalNumberFixer(Two,2),
    		new NaturalNumberMultiplier(Two,K,Z));

    this.setClauses(p.getClauses());
  }
}
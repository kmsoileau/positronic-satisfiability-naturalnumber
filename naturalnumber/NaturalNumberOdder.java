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
public class NaturalNumberOdder extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = -5625804576872975747L;

	public NaturalNumberOdder(INaturalNumber Z) throws Exception
  {
  	INaturalNumber One=new NaturalNumber();
		INaturalNumber Two=new NaturalNumber();
		INaturalNumber K=new NaturalNumber();
		INaturalNumber L=new NaturalNumber();
		
		IProblem p=new Conjunction(
				new NaturalNumberFixer(One,1),
    		new NaturalNumberFixer(Two,2),
    		new NaturalNumberMultiplier(Two,K,L),
    		new NaturalNumberIncrementer(L,Z));

    this.setClauses(p.getClauses());
  }
}
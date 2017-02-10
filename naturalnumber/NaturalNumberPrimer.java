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
import positronic.satisfiability.elements.ProblemDenier;

/**
 * @todo test and demo this class
 *
 */
public class NaturalNumberPrimer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 3224751661986678000L;

	public NaturalNumberPrimer(INaturalNumber Z) throws Exception
  {
		INaturalNumber Two=new NaturalNumber();
		IProblem p1=new NaturalNumberFixer(Two,2);
		IProblem p2=new ProblemDenier(new NaturalNumberCompositor(Z));
		IProblem p3=new NaturalNumberOrderer(Two,Z);
   
    IProblem p=new Conjunction(p1,p2,p3);

    this.setClauses(p.getClauses());
  }
}
package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
 import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberFromBitConverter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberFromBitConverter(INaturalNumber b, IBooleanVariable bv) throws Exception
  {
    IProblem convert=new Disjunction(
    new Conjunction(new NaturalNumberFixer(b,0L),new BitFixer(bv,false)),
    new Conjunction(new NaturalNumberFixer(b,1L),new BitFixer(bv,true))
    );

    this.setClauses(convert.getClauses());
  }
}
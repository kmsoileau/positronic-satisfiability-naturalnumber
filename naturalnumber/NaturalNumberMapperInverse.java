package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
/**
 * <p>Title: NaturalNumberMappingInverse</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberMapperInverse extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberMapperInverse(NaturalNumberMapper f, INaturalNumber X, INaturalNumber Y) throws Exception
  {
    IProblem prob=new Conjunction(new MetaProblem[]
    {
      new NaturalNumberEqualizer(Y,f.getDomainVariable()),
      f,
      new NaturalNumberEqualizer(f.getRangeVariable(),X)
    });
    this.setClauses(prob.getClauses());
  }
}
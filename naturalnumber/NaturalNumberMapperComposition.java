package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
/**
 * <p>Title: NaturalNumberMappingComposition</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberMapperComposition extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberMapperComposition(NaturalNumberMapper first, NaturalNumberMapper second, INaturalNumber X, INaturalNumber Y) throws Exception
  {
    IProblem prob=new Conjunction(new MetaProblem[]
    {
      new NaturalNumberEqualizer(X,first.getDomainVariable()),
      first,
      new NaturalNumberEqualizer(first.getRangeVariable(),second.getDomainVariable()),
      second,
      new NaturalNumberEqualizer(second.getRangeVariable(),Y),
    });
    this.setClauses(prob.getClauses());
  }
}
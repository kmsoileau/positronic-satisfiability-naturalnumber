package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import positronic.satisfiability.elements.BitEqualizer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberEqualizer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberEqualizer(INaturalNumber X, INaturalNumber Y) throws Exception
  {
    BitEqualizer[] thba=new BitEqualizer[NaturalNumber.getLength()];
    for(int i=0;i<NaturalNumber.getLength();i++)
      thba[i]=new BitEqualizer(X.getBooleanVariable(i),Y.getBooleanVariable(i));
    IProblem p1=new Conjunction(thba);
    this.setClauses(p1.getClauses());
  }
}
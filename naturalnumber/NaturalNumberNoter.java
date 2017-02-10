package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import positronic.satisfiability.elements.BitNoter;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberNoter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberNoter(INaturalNumber X, INaturalNumber Y) throws Exception
  {
    BitNoter[] bnnb=new BitNoter[NaturalNumber.getLength()];
    for(int i=0;i<bnnb.length;i++)
      bnnb[i]=new BitNoter(X.getBooleanVariable(i), Y.getBooleanVariable(i));
    IProblem p=new Conjunction(bnnb);
    this.setClauses(p.getClauses());
  }
}
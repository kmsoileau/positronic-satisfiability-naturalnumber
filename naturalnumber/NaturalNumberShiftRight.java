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
import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberShiftRight extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberShiftRight(INaturalNumber X, INaturalNumber Z) throws Exception
  {
    int span=NaturalNumber.getLength();
    BitEqualizer[] be=new BitEqualizer[span-1];
    for(int i=0;i<span-1;i++)
      be[i]=new BitEqualizer(Z.getBooleanVariable(i),X.getBooleanVariable(i+1));
    BitFixer beset=new BitFixer(Z.getBooleanVariable(span-1),false);
    IProblem p=new Conjunction(new Conjunction(be),beset);

    this.setClauses(p.getClauses());
  }
}
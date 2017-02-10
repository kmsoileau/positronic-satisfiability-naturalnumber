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

public class NaturalNumberCompositor extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 154021679263116366L;

	public NaturalNumberCompositor(INaturalNumber Z) throws Exception
  {
    INaturalNumber A=new NaturalNumber();
    INaturalNumber B=new NaturalNumber();
    INaturalNumber X=new NaturalNumber();
    INaturalNumber Y=new NaturalNumber();
    INaturalNumber Two=new NaturalNumber();

    NaturalNumberFixer nnf2=new NaturalNumberFixer(Two,2);
    NaturalNumberAdder nnax=new NaturalNumberAdder(A,Two,X);
    NaturalNumberAdder nnby=new NaturalNumberAdder(B,Two,Y);
    NaturalNumberMultiplier nnm=new NaturalNumberMultiplier(X,Y,Z);

    IProblem p=new Conjunction(new IProblem[]{nnf2,nnax,nnby,nnm});

    this.setClauses(p.getClauses());
  }
}
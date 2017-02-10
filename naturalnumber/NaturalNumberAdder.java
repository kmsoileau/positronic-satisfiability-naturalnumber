package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IClause;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.elements.ThreeBitAdder;
import positronic.satisfiability.elements.TwoBitAdder;
 /**
 * An extension of the Problem class which implements an adder of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z and INaturalNumber C to the appropriate constructor. The
 * NaturalNumberAdder object produced is a Problem, and one may manipulate it
 * using any of the methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p><tt>Problem p = new NaturalNumberAdder(X,Y,Z,C);</tt></p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p><tt>Z == X + Y</tt></p>
 *
 * and the carry bits will be found in C.
 *
 * If the carry bits are of no interest, one may instead write
 *
 * <p><tt>Problem p = new NaturalNumberAdder(X,Y,Z);</tt></p>
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.2, 05/03/08
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see MetaProblem
 */

public class NaturalNumberAdder extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = -595205667365932960L;

	public NaturalNumberAdder(INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
  {
    this(X,Y,Z,new NaturalNumber());
  }

  public NaturalNumberAdder(INaturalNumber X, INaturalNumber Y, INaturalNumber Z, INaturalNumber C) throws Exception
  {
    IProblem p=new TwoBitAdder(Y.getBooleanVariable(0),X.getBooleanVariable(0),Z.getBooleanVariable(0),C.getBooleanVariable(0));
    for(int i=1;i<NaturalNumber.getLength();i++)
      p=new Conjunction(p,new ThreeBitAdder(C.getBooleanVariable(i-1),Y.getBooleanVariable(i),X.getBooleanVariable(i),Z.getBooleanVariable(i),C.getBooleanVariable(i)));
    p=new Conjunction(p,new BitFixer(C.getBooleanVariable(NaturalNumber.getLength()-1),false));
    this.setClauses(p.getClauses());
  }
}
package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IClause;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

 /**
 * An extension of the Problem class which implements an divider of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z to the appropriate constructor. The NaturalNumberDivider 
 * object produced is a Problem, and one may manipulate it using any of the 
 * methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p><tt>Problem p = new NaturalNumberDivider(X,Y,Z);</tt></p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p><tt>X == Z * Y</tt></p>.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 06/02/08
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see MetaProblem
 */

public class NaturalNumberQuotienter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = -3831009110091726051L;

	public NaturalNumberQuotienter(INaturalNumber Dividend, INaturalNumber Divisor, INaturalNumber Quotient, INaturalNumber Remainder) throws Exception
  {
  	//Product=Divisor*Quotient
		//Dividend=Product+Remainder
		//RemainderPlusOne=Remainder++
  	//RemainderPlusOne<=Divisor
  	INaturalNumber Product=new NaturalNumber(NaturalNumber.getLength());
  	INaturalNumber RemainderPlusOne=new NaturalNumber(NaturalNumber.getLength());
  	INaturalNumber Difference=new NaturalNumber(NaturalNumber.getLength());
  	INaturalNumber One=new NaturalNumber(NaturalNumber.getLength());
  	IProblem problem=null;
  	problem=new Conjunction(problem,new NaturalNumberMultiplier(Divisor,Quotient,Product));
  	problem=new Conjunction(problem,new NaturalNumberAdder(Product,Remainder,Dividend));
  	problem=new Conjunction(problem,new NaturalNumberFixer(One,1L));
  	problem=new Conjunction(problem,new NaturalNumberAdder(Remainder,One,RemainderPlusOne));
  	problem=new Conjunction(problem,new NaturalNumberAdder(RemainderPlusOne,Difference,Divisor));
  	
  	this.setClauses(problem.getClauses());
  }
}
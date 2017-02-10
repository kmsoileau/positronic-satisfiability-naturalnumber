package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IClause;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

/**
 * An extension of the Problem class which constrains an INaturalNumber to be 
 * positive.
 *
 * In one way to use this class, one passes INaturalNumber X to the 
 * appropriate constructor. The NaturalNumberPositiver object produced is a 
 * Problem, and one may manipulate it using any of the methods provided by 
 * the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p><tt>Problem p = new NaturalNumberPositiver(X);</tt></p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p><tt>X > 0</tt></p>.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 10/08/29
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see MetaProblem
 */

public class NaturalNumberPositiver extends MetaProblem implements IProblem 
{
	private static final long serialVersionUID = 5324036170365126821L;

	public NaturalNumberPositiver(INaturalNumber X) throws Exception 
	{
		this(X,new NaturalNumber());
	}
	
	public NaturalNumberPositiver(INaturalNumber X,INaturalNumber OneLess) throws Exception 
	{
		INaturalNumber One=new NaturalNumber(1);
		IProblem add1=new NaturalNumberAdder(OneLess,One,X);
		IProblem problem=new Conjunction(new NaturalNumberFixer(One),add1);
		this.setClauses(problem.getClauses());
	}
}

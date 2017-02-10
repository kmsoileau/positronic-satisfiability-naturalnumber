package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Clause;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IClause;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.elements.TwoBitAdder;

public class NaturalNumberIncrementer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = -2609644289688919937L;

	public NaturalNumberIncrementer(INaturalNumber X, INaturalNumber Y, INaturalNumber C) throws Exception
  {
  	IBooleanVariable bx=X.getBooleanVariable(0);
  	IBooleanVariable by=Y.getBooleanVariable(0);
  	IBooleanVariable bc=C.getBooleanVariable(0);
  	
  	this.setClauses(new IClause[]
  	{
  			Clause.newClause().or(bx).or(by).or(bc),
  			Clause.newClause().or(bx).or(by).orNot(bc),
  			Clause.newClause().or(bx).orNot(by).orNot(bc),
  			Clause.newClause().orNot(bx).or(by).or(bc),
  			Clause.newClause().orNot(bx).orNot(by).or(bc),
  			Clause.newClause().orNot(bx).orNot(by).orNot(bc)
  	});
  	IProblem[] tba=new TwoBitAdder[NaturalNumber.getLength()-1];
  	for(int i=0;i<tba.length;i++)
  		tba[i]=new TwoBitAdder(
  			C.getBooleanVariable(i),
  			X.getBooleanVariable(i+1),
  			Y.getBooleanVariable(i+1),
  			C.getBooleanVariable(i+1));
  	IProblem p=new Conjunction(this,new Conjunction(tba));
  	
    this.setClauses(p.getClauses());
  }
  
  public NaturalNumberIncrementer(INaturalNumber X, INaturalNumber Y) throws Exception
  {
  	INaturalNumber C=new NaturalNumber();
  	
  	IBooleanVariable bx=X.getBooleanVariable(0);
  	IBooleanVariable by=Y.getBooleanVariable(0);
  	IBooleanVariable bc=C.getBooleanVariable(0);
  	
  	this.setClauses(new IClause[]
  	{
  			Clause.newClause().or(bx).or(by).or(bc),
  			Clause.newClause().or(bx).or(by).orNot(bc),
  			Clause.newClause().or(bx).orNot(by).orNot(bc),
  			Clause.newClause().orNot(bx).or(by).or(bc),
  			Clause.newClause().orNot(bx).orNot(by).or(bc),
  			Clause.newClause().orNot(bx).orNot(by).orNot(bc)
  	});
  	IProblem[] tba=new TwoBitAdder[NaturalNumber.getLength()-1];
  	for(int i=0;i<tba.length;i++)
  		tba[i]=new TwoBitAdder(
  			C.getBooleanVariable(i),
  			X.getBooleanVariable(i+1),
  			Y.getBooleanVariable(i+1),
  			C.getBooleanVariable(i+1));
  	IProblem p=new Conjunction(this,new Conjunction(tba));
  	
    this.setClauses(p.getClauses());
  }
}
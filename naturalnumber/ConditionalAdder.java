package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.ConditionalAdderException;
import positronic.satisfiability.naturalnumberlist.INaturalNumberList;

public class ConditionalAdder extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = 766604207120847889L;

	public ConditionalAdder(INaturalNumber[] numbers, IBooleanVariable[] membership, INaturalNumber conditionalSum) throws Exception
	{
		if(numbers.length==0 || membership.length==0)
			throw(new ConditionalAdderException("numbers or bits array of zero length was passed to constructor."));
		if(numbers.length!=membership.length)
			throw(new ConditionalAdderException("numbers or bits array of different lengths were passed to constructor."));
		if(conditionalSum==null)
			throw(new ConditionalAdderException("A null conditionalSum variable was passed to constructor."));
		if(conditionalSum.size()==0)
			throw(new ConditionalAdderException("A conditionalSum variable of size zero was passed to constructor."));
		
		INaturalNumber[] subAnswer=new INaturalNumber[numbers.length];
		INaturalNumber[] subTotal=new INaturalNumber[numbers.length];
		subTotal[0]=new NaturalNumber();
		subAnswer[0]=new NaturalNumber();
		IProblem problem=new NaturalNumberBitMultiply(membership[0],numbers[0],subAnswer[0]);
		problem=new Conjunction(problem,
				new NaturalNumberEqualizer(subTotal[0],subAnswer[0]));
		for(int i=1;i<numbers.length;i++)
		{
			subAnswer[i]=new NaturalNumber();
			subTotal[i]=new NaturalNumber();
			problem=new Conjunction(problem,
					new NaturalNumberBitMultiply(membership[i],numbers[i],subAnswer[i]),
					new NaturalNumberAdder(subTotal[i-1],subAnswer[i],subTotal[i]));
		}
		problem=new Conjunction(problem,new NaturalNumberEqualizer(subTotal[numbers.length-1],conditionalSum));
		this.setClauses(problem.getClauses());
	}

	public ConditionalAdder(INaturalNumberList numbersArray,
			IBitString membership, INaturalNumber conditionalSum) throws Exception 
	{
		this(numbersArray.getNaturalNumberArray(),membership.getBVArray(),conditionalSum);
	}
	
	public ConditionalAdder(INaturalNumberList numbersArray,
			IBooleanVariable[] membership, INaturalNumber conditionalSum) throws Exception 
	{
		this(numbersArray.getNaturalNumberArray(),membership,conditionalSum);
	}
}
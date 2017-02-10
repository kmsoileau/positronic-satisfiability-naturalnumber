package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: NaturalNumberMultiplier</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class NaturalNumberMultiplier extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -652128847964417007L;
	private static int nnmCount=-1;

	public NaturalNumberMultiplier(INaturalNumber X, INaturalNumber Y, INaturalNumber Z) throws Exception
	{
		nnmCount++;
		if(NaturalNumber.getLength()==1)
		{
			NaturalNumberBitMultiply p=new NaturalNumberBitMultiply(X.getBooleanVariable(0),Y,Z);
			this.setClauses(p.getClauses());
		}
		if(NaturalNumber.getLength()==2)
		{
			INaturalNumber[] B=new INaturalNumber[2];
			INaturalNumber[] D=new INaturalNumber[1];
			B[1]=new NaturalNumber("NNM$"+nnmCount+"$"+"B$1$");
			D[0]=new NaturalNumber("NNM$"+nnmCount+"$"+"D$0$");
			IProblem p1=new NaturalNumberBitMultiply(X.getBooleanVariable(1),Y,B[1]);
			IProblem p2=new NaturalNumberDoubler(B[1],D[0]);
			B[0]=new NaturalNumber("NNM$"+nnmCount+"$"+"B$0$");
			IProblem p3=new NaturalNumberBitMultiply(X.getBooleanVariable(0),Y,B[0]);
			IProblem p4=new NaturalNumberAdder(B[0],D[0],Z);
			this.setClauses(new Conjunction(p1,p2,p3,p4).getClauses());
		}
		if(NaturalNumber.getLength()>2)
		{
			INaturalNumber[] B=new INaturalNumber[NaturalNumber.getLength()];
			INaturalNumber[] D=new INaturalNumber[NaturalNumber.getLength()-1];
			INaturalNumber[] S=new INaturalNumber[NaturalNumber.getLength()-2];

			B[NaturalNumber.getLength()-1]=new NaturalNumber("NNM$"+nnmCount+"$"+"B$"+(NaturalNumber.getLength()-1)+"$");
			IProblem p=null;
			p=new Conjunction(p,new NaturalNumberBitMultiply(X.getBooleanVariable(NaturalNumber.getLength()-1),Y,B[NaturalNumber.getLength()-1]));
			D[NaturalNumber.getLength()-2]=new NaturalNumber("NNM$"+nnmCount+"$"+"D$"+(NaturalNumber.getLength()-2)+"$");
			p=new Conjunction(p,
					new NaturalNumberDoubler(B[NaturalNumber.getLength()-1],D[NaturalNumber.getLength()-2]));
			B[NaturalNumber.getLength()-2]=new NaturalNumber("NNM$"+nnmCount+"$"+"B$"+(NaturalNumber.getLength()-2)+"$");
			p=new Conjunction(p,
					new NaturalNumberBitMultiply(X.getBooleanVariable(NaturalNumber.getLength()-2),Y,B[NaturalNumber.getLength()-2]));
			S[NaturalNumber.getLength()-3]=new NaturalNumber("NNM$"+nnmCount+"$"+"S$"+(NaturalNumber.getLength()-3)+"$");
			p=new Conjunction(p,
					new NaturalNumberAdder(B[NaturalNumber.getLength()-2],D[NaturalNumber.getLength()-2],S[NaturalNumber.getLength()-3]));
			for(int i=NaturalNumber.getLength()-3;i>0;i--)
			{
				D[i]=new NaturalNumber("NNM$"+nnmCount+"$"+"D$"+i+"$");
				p=new Conjunction(p,
						new NaturalNumberDoubler(S[i],D[i]));
				B[i]=new NaturalNumber("NNM$"+nnmCount+"$"+"B$"+i+"$");
				p=new Conjunction(p,
						new NaturalNumberBitMultiply(X.getBooleanVariable(i),Y,B[i]));
				S[i-1]=new NaturalNumber("NNM$"+nnmCount+"$"+"S$"+(i-1)+"$");
				p=new Conjunction(p,
						new NaturalNumberAdder(B[i],D[i],S[i-1]));
			}
			D[0]=new NaturalNumber("NNM$"+nnmCount+"$"+"D$0$");
			p=new Conjunction(p,
					new NaturalNumberDoubler(S[0],D[0]));
			B[0]=new NaturalNumber("NNM$"+nnmCount+"$"+"B$0$");
			p=new Conjunction(p,
					new NaturalNumberBitMultiply(X.getBooleanVariable(0),Y,B[0]));
			p=new Conjunction(p,
					new NaturalNumberAdder(B[0],D[0],Z));
			this.setClauses(p.getClauses());
		}
	}
}
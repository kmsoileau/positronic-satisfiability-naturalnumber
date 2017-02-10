/**
 * <p>Title: NaturalNumberFixer</p>
 * <p>Description: An IProblem which constrains an INaturalNumber to a
 * particular value.</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.NaturalNumberFixerException;

public class NaturalNumberFixer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  
  public NaturalNumberFixer(INaturalNumber b, boolean[] bitArray) throws Exception
  {
  	if(b==null)
  		throw new NaturalNumberFixerException("A null INaturalNumber was passed to a constructor.");
  	if(bitArray==null)
  		throw new NaturalNumberFixerException("A null bitArray was passed to a constructor.");
  	NaturalNumberBitFixer[] bnnbf
      =new NaturalNumberBitFixer[NaturalNumber.getLength()];
    int a=bitArray.length;
    if(a!=bnnbf.length)
      throw(new NaturalNumberFixerException(
        "There is a size mismatch between the INaturalNumber and the parameter (boolean[] or long) passed to a constructor."));
    for(int i=0;i<a;i++)
      bnnbf[i]=new NaturalNumberBitFixer(b,i,bitArray[i]);
    for(int i=a;i<NaturalNumber.getLength();i++)
      bnnbf[i]=new NaturalNumberBitFixer(b,i,false);
    IProblem p=new Conjunction(bnnbf);
    this.setClauses(p.getClauses());
  }

  public NaturalNumberFixer(INaturalNumber n) throws Exception
  {
  	if(n==null)
  		throw new NaturalNumberFixerException("A null INaturalNumber was passed to a constructor.");
    IProblem problem=new NaturalNumberBitFixer(n);
    this.setClauses(problem.getClauses());
  }

  public NaturalNumberFixer(INaturalNumber b, long n) throws Exception
  {
    this(b,(
      (positronic.math.INumber)new positronic.math.Number(
        new positronic.math.Number(n),NaturalNumber.getLength())).getBitArray());
  }

  public NaturalNumberFixer(INaturalNumber b, String bitString) throws Exception
  {
  	if(b==null)
  		throw new NaturalNumberFixerException("A null INaturalNumber was passed to a constructor.");
  	if(bitString==null)
  		throw new NaturalNumberFixerException("A null bitString was passed to a constructor.");
  	NaturalNumberBitFixer[] bnnbf=new NaturalNumberBitFixer[bitString.length()];
    for(int i=0;i<bnnbf.length;i++)
      if(bitString.charAt(bnnbf.length-1-i)=='1')
        bnnbf[i]=new NaturalNumberBitFixer(b,i,true);
      else
        bnnbf[i]=new NaturalNumberBitFixer(b,i,false);
    IProblem p=new Conjunction(bnnbf);
    this.setClauses(p.getClauses());
  }
}
package positronic.satisfiability.naturalnumber;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import positronic.satisfiability.bitstring.BitString;

public class NaturalNumberDemo
{
  public static void main(String[] args) throws Exception
  {
    positronic.math.Number n=new positronic.math.Number(56L);
    NaturalNumber NaturalNumber1 = new NaturalNumber("kerry",new BitString("noname",n.getBitArray()));
    System.out.println(NaturalNumber1);
  }
}
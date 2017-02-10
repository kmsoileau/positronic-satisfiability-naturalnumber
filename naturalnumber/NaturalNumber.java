/*
 * NaturalNumber.java	1.0 05/05/04
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
/**
 * A BitString representing the binary form of a natural number (i.e., a
 * positive integer).
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/05/04
 * @see BitString
 * @see BitStringException
 * @see INaturalNumber
 * @see positronic.util.Number
 */

package positronic.satisfiability.naturalnumber;

import positronic.satisfiability.bitstring.BitString;
import positronic.satisfiability.bitstring.IBitString;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.exceptions.NaturalNumberException;

public class NaturalNumber extends BitString implements INaturalNumber
{
	private static long nNCount;
	private static int bits=INaturalNumber.DEFAULTLENGTH;
	private static boolean hasBeenSet=false;
	private static String prefix=INaturalNumber.DEFAULTPREFIX;

	public static int getLength() 
	{
		if(!hasBeenSet)
		{
			bits=INaturalNumber.DEFAULTLENGTH;
			hasBeenSet=true;
		}
		return bits;	  
	}

	public static void setLength(int size) throws NaturalNumberException 
	{
		if(!hasBeenSet)
		{
			bits=size;
			hasBeenSet=true;
		}
		else
			throw(new NaturalNumberException("Attempted to change a previously set bit length for the class NaturalNumber."));
	}

	public static void setPrefix(String string) 
	{
		prefix=string;
	}

	public NaturalNumber() throws Exception
	{
		this(prefix+ nNCount++);
	}

	public NaturalNumber(IBitString data) throws Exception
	{
		this(prefix+ nNCount++,data);
	}

	public NaturalNumber(long n) throws Exception
	{
		this(prefix+ nNCount++,n);
	}

	public NaturalNumber(String name) throws Exception
	{
		super(name,new boolean[NaturalNumber.getLength()]);
	}

	private NaturalNumber(String name, boolean[] data) throws Exception
	{
		this(name,new BitString(name,data));
	}

	public NaturalNumber(String name,IBitString data) throws Exception
	{
		super(name,data.getBVArray(NaturalNumber.getLength()));
	}

	public NaturalNumber(String name, long n) throws Exception
	{
		this(name,new positronic.math.Number(new positronic.math.Number(n),
				NaturalNumber.getLength()).getBitArray());
	}

	@Override
	public boolean equals(Object o)
	{
		if(o==null)
			return false; //this is never equal to null.
			if(!(o instanceof NaturalNumber))
				return false;
			else
				return (this.getName().compareTo(((NaturalNumber)o).getName()))==0;
	}

	@Override
	public String getName()
	{
		return super.getName();
	}

	@Override
	public void setName(String s) throws Exception
	{
		super.setName(s);
	}

	public IBooleanVariable[] toBVArray() throws Exception
	{
		return super.getBVArray();
	}

	public String toString()
	{
		long total=0L;
		for(int i=getLength()-1;i>=0;i--)
		{
			IBooleanVariable ib = null;
			try
			{
				ib = this.getBooleanVariable(i);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			if(ib.getValue())
				total=2*total+1;
			else
				total=2*total;
		}
		return ""+total;
	}
}
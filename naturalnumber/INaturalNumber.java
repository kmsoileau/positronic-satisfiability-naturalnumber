package positronic.satisfiability.naturalnumber;

/*
 * INaturalNumber.java	1.1 09/05/21
 *
 * Copyright 2009 Positronic Software.
 *
 *
 */

import positronic.satisfiability.bitstring.IBitString;

public interface INaturalNumber extends IBitString
{
	final int DEFAULTLENGTH=9;
	final String DEFAULTPREFIX = "nn$";
}
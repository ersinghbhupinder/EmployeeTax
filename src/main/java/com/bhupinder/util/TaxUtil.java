package com.bhupinder.util;

/**
 * This is utility class for  all tax related logic
 * @author bhupinder
 *
 */
public class TaxUtil {
	
	//Ideally this should be part of database table ie lookup table 
	private static final double TAXRATE= 20;
	
	/**
	 * This method calculate tax based on given tax rate
	 * @param salary
	 * @return double
	 */
	public static double calculateTaxes(double salary) {
		double totalTax=salary*(TAXRATE/100);
		return totalTax;
	}
}

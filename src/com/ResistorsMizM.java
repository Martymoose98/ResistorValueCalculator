package com;

import javax.swing.JOptionPane;

/**
 * 
 * @author Martin
 * @version 1.0
 * 
 *          This program calculates resistor values based on the resistor entered. 
 *          If the resistor is malformed it notifies the user that the values 
 *          cannot be calculated and exits. Otherwise displays the calculation
 * 
 */
public class ResistorsMizM
{
	/**
	 * Helper enumeration
	 */
	enum ResistorValue
	{
		BLACK(0), BROWN(1), RED(2), ORANGE(3), YELLOW(4), GREEN(5), BLUE(6), VIOLET(7), GREY(8), WHITE(9);

		public int value;

		/**
		 * Constructor
		 * 
		 * @param value
		 *            the value to initialize a new ResistorValue
		 */
		ResistorValue(int value)
		{
			this.value = value;
		}
	}

	/**
	 * 
	 * Calculates the resistor values based on this formula: (first * radix + second) * 10^third
	 * 
	 * @param first
	 *            the first color band value
	 * @param second
	 *            the second color band value
	 * @param multiplier
	 *            the third color band value
	 * @return the resistor value
	 */
	static long calculate(ResistorValue first, ResistorValue second, ResistorValue multiplier)
	{
		return (long) ((first.value * 10 + second.value) * Math.pow(10, multiplier.value));
	}

	/**
	 * Creates a input style message box dialog
	 * 
	 * @see createMessageBox if no user input is desired
	 * @see createOptionMessageBox if the user input is a "yes no" type or similar
	 * 
	 * @param text
	 *            a message
	 * @param caption
	 *            a caption; title
	 * @param selectionValues
	 *            values to be selected can be null
	 * @param defaultSelection
	 *            default value to be selected can be null
	 * @param type
	 *            type of message box (info, warning, question, etc...)
	 * @param <T>
	 *            template for selections
	 * 
	 * @return the JOptionPane.showInputDialog return value casted to string
	 */
	public static <T> String createInputMessageBox(String text, String caption, T[] selectionValues, T defaultSelection, int type)
	{
		return (String) JOptionPane.showInputDialog(null, text, caption, type, null, selectionValues, defaultSelection);
	}

	/**
	 * Creates a message box
	 * 
	 * @see createInputMessageBox if you want to obtain user input from the user
	 * @see createOptionMessageBox if the user input is a "yes no" type or similar
	 * 
	 * @param text
	 *            a message
	 * @param caption
	 *            a caption (the title)
	 * @param type
	 *            type of message box (info, warning, question, etc...)
	 */
	public static void createMessageBox(String text, String caption, int type)
	{
		JOptionPane.showMessageDialog(null, text, caption, type);
	}

	/**
	 * Creates an option message box
	 * 
	 * @see createInputMessageBox if you want to obtain user input from the user
	 * @see createMessageBox if no user input is desired
	 * 
	 * @param text
	 *            a message
	 * @param caption
	 *            a caption (the title)
	 * @param type
	 *            type of message box (info, warning, question, etc...)
	 * 
	 * @return the users choice
	 */
	public static int createOptionMessageBox(String text, String caption, int type)
	{
		return JOptionPane.showConfirmDialog(null, text, caption, type);
	}

	/**
	 * Parses the resistor from a string and converts it to a more usable value. If the conversion isn't possible it notifies the user and returns nullptr
	 * 
	 * @param resistor
	 *            the resistor value in string form
	 * 
	 * @return the resistor value in ResistorValue (integer) array form
	 */
	public static ResistorValue[] parseResistor(String resistor)
	{
		String[] values = resistor.toUpperCase().split("-");
		ResistorValue[] resistorValues = new ResistorValue[3];

		if (values.length > 3)
		{
			createMessageBox("The resistor entered is not supported!", "Resistor Calculator", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		for (int i = 0; i < values.length; ++i)
		{
			if (values[i].equals("BLACK"))
				resistorValues[i] = ResistorValue.BLACK;
			else if (values[i].equals("BROWN"))
				resistorValues[i] = ResistorValue.BROWN;
			else if (values[i].equals("RED"))
				resistorValues[i] = ResistorValue.RED;
			else if (values[i].equals("ORANGE"))
				resistorValues[i] = ResistorValue.ORANGE;
			else if (values[i].equals("YELLOW"))
				resistorValues[i] = ResistorValue.YELLOW;
			else if (values[i].equals("GREEN"))
				resistorValues[i] = ResistorValue.GREEN;
			else if (values[i].equals("BLUE"))
				resistorValues[i] = ResistorValue.BLUE;
			else if (values[i].equals("VIOLET"))
				resistorValues[i] = ResistorValue.VIOLET;
			else if (values[i].equals("GREY"))
				resistorValues[i] = ResistorValue.GREY;
			else if (values[i].equals("WHITE"))
				resistorValues[i] = ResistorValue.WHITE;
		}

		return resistorValues;
	}

	/**
	 * Main Function (Entry Point)
	 * 
	 * @param args
	 *            command line arguments if any
	 */
	public static void main(String[] args)
	{
		String input = createInputMessageBox("This program calculates resistor values based on the resistor entered.\nEx. RED-BLUE-BROWN\nEnter a resistor.", "Resistor Calculator", null, null, JOptionPane.INFORMATION_MESSAGE);
		ResistorValue[] values = parseResistor(input);

		if (values != null)
			createMessageBox("The value of the resistor \"" + input + "\" is: " + calculate(values[0], values[1], values[2]) + " ohms.", "Resistor Calculator", JOptionPane.INFORMATION_MESSAGE);
	}

}

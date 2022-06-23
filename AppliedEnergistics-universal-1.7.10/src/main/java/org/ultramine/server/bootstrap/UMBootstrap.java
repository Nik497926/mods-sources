package org.ultramine.server.bootstrap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jline.console.ConsoleReader;

import java.io.IOException;

public class UMBootstrap
{
	private static boolean isJLine;
	private static boolean isColoredConsole;
	private static boolean isAnsiColors;
	private static String charset;

	@SideOnly(Side.SERVER)
	private static ConsoleReader reader;

	public static boolean isJLine()
	{
		return isJLine;
	}

	public static boolean isColoredTerminal()
	{
		return isColoredConsole;
	}

	public static boolean isAnsiColors()
	{
		return isAnsiColors;
	}

	public static String getTerminalCharset()
	{
		return charset;
	}

	@SideOnly(Side.SERVER)
	public static ConsoleReader getJLineReader()
	{
		return reader;
	}

	@SideOnly(Side.SERVER)
	public static void handleFirstLine(String[] args)
	{
		setSysPropertyIfNotPresent("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector"); //always async logging

		setupTerminal(args);

		showGreeting();
	}

	@SideOnly(Side.SERVER)
	private static void setupTerminal(String[] args)
	{
		// -Dorg.ultramine.terminal=
		// default - strip all minecraft control codes
		// raw - leaves the original minecraft codes with \u00a7 + [0-9k-or]
		// ansi - replaces minecraft codes with ANSI control sequences
		// jline - same as 'ansi' + JLine ConsoleReader
		String terminal = System.getProperty("org.ultramine.terminal", contains(args, "nojline") ? "default" : "jline").toLowerCase();
		isJLine = terminal.equals("jline") && System.console() != null;
		if(isJLine)
		{
			setupJLine();
		}
		else
		{
			setSysPropertyIfNotPresent("log4j.skipJansi", "true");
			isAnsiColors = terminal.equals("ansi");
			isColoredConsole = isAnsiColors || terminal.equals("raw");
		}
		charset = System.getProperty("org.ultramine.terminal.charset", charset);
	}

	@SideOnly(Side.SERVER)
	private static void setupJLine()
	{
		try
		{
			reader = new ConsoleReader(System.in, System.out);
			reader.setExpandEvents(false); // Avoid parsing exceptions for uncommonly used event designators
			isAnsiColors = isColoredConsole = reader.getTerminal().isAnsiSupported();
			charset = reader.getTerminal().getOutputEncoding();
		}
		catch (Throwable e)
		{
			isAnsiColors = isColoredConsole = isJLine = false;
			System.err.println("Failed to setup Jline. Add flag '-Dorg.ultramine.terminal=default' to disable");
			e.printStackTrace();
			try
			{
				// Try again with jline disabled for Windows users without C++ 2008 Redistributable
				System.setProperty("jline.terminal", "jline.UnsupportedTerminal");
				System.setProperty("user.language", "en");
				reader = new ConsoleReader(System.in, System.out);
				reader.setExpandEvents(false);
			}
			catch (IOException ignored) {}
		}
	}

	@SideOnly(Side.SERVER)
	private static void showGreeting() {
		System.out.println(
			"\u001b[96m" +
			"              ,,                     ,,    ,,    ,,                                                \n" +
			"  .g8\"\"8q.   *MM                     db  `7MM    db                                                \n" +
			".dP'    `YM.  MM                           MM                                                      \n" +
			"dM'      `MM  MM,dMMb.  `7M'   `MF'`7MM    MM  `7MM   ,pW\"Wq.  `7MMpMMMb.     `7Mb,od8 `7MM  `7MM  \n" +
			"MM        MM  MM    `Mb   VA   ,V    MM    MM    MM  6W'   `Wb   MM    MM       MM' \"'   MM    MM  \n" +
			"MM.      ,MP  MM     M8    VA ,V     MM    MM    MM  8M     M8   MM    MM       MM       MM    MM  \n" +
			"`Mb.    ,dP'  MM.   ,M9     VVV      MM    MM    MM  YA.   ,A9   MM    MM  ,,   MM       MM    MM  \n" +
			"  `\"bmmd\"'    P^YbmdP'       W     .JMML..JMML..JMML. `Ybmd9'  .JMML  JMML.db .JMML.     `Mbod\"YML. \n" +
			"\u001b[m"
		);
	}

	@SideOnly(Side.SERVER)
	private static boolean contains(String[] args, String search)
	{
		for(String arg : args)
			if(arg.contains(search))
				return true;
		return false;
	}

	@SideOnly(Side.SERVER)
	private static void setSysPropertyIfNotPresent(String key, String val)
	{
		if(System.getProperty(key, null) == null)
			System.setProperty(key, val);
	}
}

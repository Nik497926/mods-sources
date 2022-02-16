package net.divinerpg.utils.events;

import net.divinerpg.libs.Reference;

public class UpdateChecker
{
	public static boolean isUpdateAvailable()
	{
		/* TODO gamerforEA code replace, old code:
		try
		{
			if (!getCurrentVersion().contains(Reference.MOD_VERSION))
				return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		} */
		return false;
	}

	public static boolean isOnline()
	{
		/* TODO gamerforEA code replace, old code:
		try
		{
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements())
			{
				NetworkInterface interf = interfaces.nextElement();
				if (interf.isUp() && !interf.isLoopback())
				{
					List<InterfaceAddress> adrs = interf.getInterfaceAddresses();
					for (InterfaceAddress adr : adrs)
					{
						InetAddress inadr = adr.getAddress();
						if (inadr instanceof Inet4Address)
						{
							LogHelper.debug("Internet connection found");
							return true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			LogHelper.warn("Something is probably wrong with your network configuration. DivineRPG can continue loading but joining worlds will be slightly slower!");
			try
			{
				URL url = new URL("https://github.com");
				HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
				Object data = httpConnection.getContent();
			}
			catch (Exception ex)
			{
				LogHelper.debug("Internet connection not found");
				return false;
			}
			LogHelper.debug("Internet connection found");
			return true;
		}
		LogHelper.debug("Internet connection not found");
		return false; */
		return true;
		// TODO gamerforEA code end
	}

	public static String getCurrentVersion()
	{
		/* TODO gamerforEA code replace, old code:
		BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/DivineRPG/DivineRPG/master/Version.txt").openStream()));
		String curVersion = versionFile.readLine();
		versionFile.close();
		return curVersion; */
		return Reference.MOD_VERSION;
		// TODO gamerforEA code end
	}
}

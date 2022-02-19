package thaumcraft.codechicken.core.launch;

import com.gamerforea.thaumcraft.coremod.ThaumCraftSideTransformer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.asm.transformers.SideTransformer;
import cpw.mods.fml.common.versioning.ComparableVersion;
import cpw.mods.fml.relauncher.*;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.LaunchClassLoader;
import sun.misc.URLClassPath;
import sun.net.util.URLUtil;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// TODO gamerforEA code start
@IFMLLoadingPlugin.SortingIndex(1001)
// TODO gamerforEA code end
public class DepLoader implements IFMLLoadingPlugin, IFMLCallHook
{
	private static ByteBuffer downloadBuffer = ByteBuffer.allocateDirect(8388608);
	private static final String owner = "CB\'s DepLoader";
	private static DepLoader.DepLoadInst inst;

	// TODO gamerforEA code start
	private static final boolean FIX_THAUMOMETER_VALIDATION = true;
	private static boolean isObfuscated = false;

	public static boolean isObfuscated()
	{
		return isObfuscated;
	}

	private static void reorderTransformers(LaunchClassLoader classLoader)
	{
		if (!FIX_THAUMOMETER_VALIDATION || classLoader == null)
			return;

		try
		{
			List<IClassTransformer> transformers = ReflectionHelper.getPrivateValue(LaunchClassLoader.class, classLoader, "transformers");
			int transformerIndex = -1;
			int sideTransformerIndex = -1;

			for (int i = 0; (transformerIndex == -1 || sideTransformerIndex == -1) && i < transformers.size(); i++)
			{
				IClassTransformer transformer = transformers.get(i);
				if (transformer instanceof ThaumCraftSideTransformer)
					transformerIndex = i;
				else if (transformer instanceof SideTransformer)
					sideTransformerIndex = i;
			}

			if (transformerIndex != -1 && sideTransformerIndex != -1 && transformerIndex > sideTransformerIndex)
			{
				IClassTransformer transformer = transformers.remove(transformerIndex);
				transformers.add(sideTransformerIndex, transformer);
			}
		}
		catch (Throwable throwable)
		{
			throwable.printStackTrace();
		}
	}
	// TODO gamerforEA code end

	public static void load()
	{
		if (inst == null)
		{
			inst = new DepLoader.DepLoadInst();
			inst.load();
		}
	}

	@Override
	public String[] getASMTransformerClass()
	{
		// TODO gamerforEA code replace, old code:
		// return null;
		if (FIX_THAUMOMETER_VALIDATION)
			return new String[] { "com.gamerforea.thaumcraft.coremod.ThaumCraftSideTransformer", "com.gamerforea.thaumcraft.coremod.ThaumCraftClassTransformer" };
		return new String[] { "com.gamerforea.thaumcraft.coremod.ThaumCraftClassTransformer" };
		// TODO gamerforEA code end
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return this.getClass().getName();
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		// TODO gamerforEA code start
		isObfuscated = (Boolean) data.get("runtimeDeobfuscationEnabled");

		if (FIX_THAUMOMETER_VALIDATION)
		{
			Object classLoader = data.get("classLoader");
			if (classLoader instanceof LaunchClassLoader)
				reorderTransformers((LaunchClassLoader) classLoader);
		}
		// TODO gamerforEA code end
	}

	@Override
	public Void call()
	{
		load();
		return null;
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}

	public static class DepLoadInst
	{
		private File modsDir;
		private File v_modsDir;
		private DepLoader.IDownloadDisplay downloadMonitor;
		private JDialog popupWindow;
		private Map<String, DepLoader.Dependency> depMap = new HashMap<>();
		private HashSet<String> depSet = new HashSet<>();

		public DepLoadInst()
		{
			String mcVer = (String) FMLInjectionData.data()[4];
			File mcDir = (File) FMLInjectionData.data()[6];
			this.modsDir = new File(mcDir, "mods");
			this.v_modsDir = new File(mcDir, "mods/" + mcVer);
			if (!this.v_modsDir.exists())
				this.v_modsDir.mkdirs();

		}

		private void addClasspath(String name)
		{
			try
			{
				((LaunchClassLoader) DepLoader.class.getClassLoader()).addURL(new File(this.v_modsDir, name).toURI().toURL());
			}
			catch (MalformedURLException var3)
			{
				throw new RuntimeException(var3);
			}
		}

		private void deleteMod(File mod)
		{
			if (!mod.delete())
			{
				try
				{
					ClassLoader cl = DepLoader.class.getClassLoader();
					URL url = mod.toURI().toURL();
					Field f_ucp = URLClassLoader.class.getDeclaredField("ucp");
					Field f_loaders = URLClassPath.class.getDeclaredField("loaders");
					Field f_lmap = URLClassPath.class.getDeclaredField("lmap");
					f_ucp.setAccessible(true);
					f_loaders.setAccessible(true);
					f_lmap.setAccessible(true);
					URLClassPath ucp = (URLClassPath) f_ucp.get(cl);
					Closeable loader = (Closeable) ((Map) f_lmap.get(ucp)).remove(URLUtil.urlNoFragString(url));
					if (loader != null)
					{
						loader.close();
						((List) f_loaders.get(ucp)).remove(loader);
					}
				}
				catch (Exception var9)
				{
					var9.printStackTrace();
				}

				if (!mod.delete())
				{
					mod.deleteOnExit();
					String msg = "CB\'s DepLoader was unable to delete file " + mod.getPath() + " the game will now try to delete it on exit. If this dialog appears again, delete it manually.";
					System.err.println(msg);
					if (!GraphicsEnvironment.isHeadless())
						JOptionPane.showMessageDialog(null, msg, "An update error has occured", JOptionPane.ERROR_MESSAGE);

					System.exit(1);
				}

			}
		}

		private void download(DepLoader.Dependency dep)
		{
			this.popupWindow = (JDialog) this.downloadMonitor.makeDialog();
			File libFile = new File(this.v_modsDir, dep.file.filename);

			try
			{
				URL libDownload = new URL(dep.url + '/' + dep.file.filename);
				this.downloadMonitor.updateProgressString("Downloading file %s", libDownload.toString());
				System.out.format("Downloading file %s\n", libDownload.toString());
				URLConnection connection = libDownload.openConnection();
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestProperty("User-Agent", "CB\'s DepLoader Downloader");
				int sizeGuess = connection.getContentLength();
				this.download(connection.getInputStream(), sizeGuess, libFile);
				this.downloadMonitor.updateProgressString("Download complete");
				System.out.println("Download complete");
				this.scanDepInfo(libFile);
			}
			catch (Exception var6)
			{
				libFile.delete();
				if (this.downloadMonitor.shouldStopIt())
				{
					System.err.println("You have stopped the downloading operation before it could complete");
					System.exit(1);
				}
				else
				{
					this.downloadMonitor.showErrorDialog(dep.file.filename, dep.url + '/' + dep.file.filename);
					throw new RuntimeException("A download error occured", var6);
				}
			}
		}

		private void download(InputStream is, int sizeGuess, File target) throws Exception
		{
			if (sizeGuess > DepLoader.downloadBuffer.capacity())
				throw new Exception(String.format("The file %s is too large to be downloaded by CB\'s DepLoader - the download is invalid", target.getName()));
			else
			{
				DepLoader.downloadBuffer.clear();
				int fullLength = 0;
				this.downloadMonitor.resetProgress(sizeGuess);

				try
				{
					this.downloadMonitor.setPokeThread(Thread.currentThread());
					byte[] smallBuffer = new byte[1024];

					int bytesRead;
					while ((bytesRead = is.read(smallBuffer)) >= 0)
					{
						DepLoader.downloadBuffer.put(smallBuffer, 0, bytesRead);
						fullLength += bytesRead;
						if (this.downloadMonitor.shouldStopIt())
							break;

						this.downloadMonitor.updateProgress(fullLength);
					}

					is.close();
					this.downloadMonitor.setPokeThread(null);
					DepLoader.downloadBuffer.limit(fullLength);
					DepLoader.downloadBuffer.position(0);
				}
				catch (InterruptedIOException var8)
				{
					Thread.interrupted();
					throw new Exception("Stop");
				}

				if (!target.exists())
					target.createNewFile();

				DepLoader.downloadBuffer.position(0);
				FileOutputStream fos = new FileOutputStream(target);
				fos.getChannel().write(DepLoader.downloadBuffer);
				fos.close();
			}
		}

		private String checkExisting(DepLoader.Dependency dep)
		{
			for (File f : this.modsDir.listFiles())
			{
				DepLoader.VersionedFile vfile = new DepLoader.VersionedFile(f.getName(), dep.file.pattern);
				if (vfile.matches() && vfile.name.equals(dep.file.name) && !f.renameTo(new File(this.v_modsDir, f.getName())))
					this.deleteMod(f);
			}

			for (File f : this.v_modsDir.listFiles())
			{
				DepLoader.VersionedFile vfile = new DepLoader.VersionedFile(f.getName(), dep.file.pattern);
				if (vfile.matches() && vfile.name.equals(dep.file.name))
				{
					int cmp = vfile.version.compareTo(dep.file.version);
					if (cmp < 0)
					{
						System.out.println("Deleted old version " + f.getName());
						this.deleteMod(f);
						return null;
					}

					if (cmp > 0)
					{
						System.err.println("Warning: version of " + dep.file.name + ", " + vfile.version + " is newer than request " + dep.file.version);
						return f.getName();
					}

					return f.getName();
				}
			}

			return null;
		}

		public void load()
		{
			this.scanDepInfos();
			if (!this.depMap.isEmpty())
			{
				this.loadDeps();
				this.activateDeps();
			}
		}

		private void activateDeps()
		{
			for (DepLoader.Dependency dep : this.depMap.values())
			{
				if (dep.coreLib)
					this.addClasspath(dep.existing);
			}

		}

		private void loadDeps()
		{
			this.downloadMonitor = FMLLaunchHandler.side().isClient() ? new Downloader() : new DummyDownloader();

			try
			{
				while (!this.depSet.isEmpty())
				{
					Iterator<String> it = this.depSet.iterator();
					DepLoader.Dependency dep = this.depMap.get(it.next());
					it.remove();
					this.load(dep);
				}
			}
			finally
			{
				if (this.popupWindow != null)
				{
					this.popupWindow.setVisible(false);
					this.popupWindow.dispose();
				}

			}

		}

		private void load(DepLoader.Dependency dep)
		{
			dep.existing = this.checkExisting(dep);
			if (dep.existing == null && dep.file.name.equalsIgnoreCase("baubles"))
			{
				this.download(dep);
				dep.existing = dep.file.filename;
			}

		}

		private List<File> modFiles()
		{
			List<File> list = new LinkedList<>();
			list.addAll(Arrays.asList(this.modsDir.listFiles()));
			list.addAll(Arrays.asList(this.v_modsDir.listFiles()));
			return list;
		}

		private void scanDepInfosWeb()
		{
			try
			{
				InputStream input = new URL("https://dl.dropboxusercontent.com/u/47135879/dependancies.info").openStream();
				if (input != null)
					this.loadJSon(input);
			}
			catch (Exception var2)
			{
				System.err.println("Failed to load dependencies.info from https://dl.dropboxusercontent.com/u/47135879/dependancies.info as JSON");
				var2.printStackTrace();
			}

		}

		private void scanDepInfos()
		{
			for (File file : this.modFiles())
			{
				if (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))
					this.scanDepInfo(file);
			}

		}

		private void scanDepInfo(File file)
		{
			try
			{
				ZipFile zip = new ZipFile(file);
				ZipEntry e = zip.getEntry("dependancies.info");
				if (e == null)
					e = zip.getEntry("dependencies.info");

				if (e != null)
					this.loadJSon(zip.getInputStream(e));

				zip.close();
			}
			catch (Exception var4)
			{
				System.err.println("Failed to load dependencies.info from " + file.getName() + " as JSON");
				var4.printStackTrace();
			}

		}

		private void loadJSon(InputStream input) throws IOException
		{
			InputStreamReader reader = new InputStreamReader(input);
			JsonElement root = new JsonParser().parse(reader);
			if (root.isJsonArray())
				this.loadJSonArr(root);
			else
				this.loadJson(root.getAsJsonObject());

			reader.close();
		}

		private void loadJSonArr(JsonElement root) throws IOException
		{
			for (JsonElement node : root.getAsJsonArray())
			{
				this.loadJson(node.getAsJsonObject());
			}

		}

		private void loadJson(JsonObject node) throws IOException
		{
			boolean obfuscated = ((LaunchClassLoader) DepLoader.class.getClassLoader()).getClassBytes("net.minecraft.world.World") == null;
			String testClass = node.get("class").getAsString();
			if (DepLoader.class.getResource("/" + testClass.replace('.', '/') + ".class") == null)
			{
				String repo = node.get("repo").getAsString();
				String filename = node.get("file").getAsString();
				if (!obfuscated && node.has("dev"))
					filename = node.get("dev").getAsString();

				boolean coreLib = node.has("coreLib") && node.get("coreLib").getAsBoolean();
				Pattern pattern = null;

				try
				{
					if (node.has("pattern"))
						pattern = Pattern.compile(node.get("pattern").getAsString());
				}
				catch (PatternSyntaxException var9)
				{
					System.err.println("Invalid filename pattern: " + node.get("pattern"));
					var9.printStackTrace();
				}

				if (pattern == null)
					pattern = Pattern.compile("(\\w+).*?([\\d.]+)[-\\w]*\\.[^\\d]+");

				DepLoader.VersionedFile file = new DepLoader.VersionedFile(filename, pattern);
				if (!file.matches())
					throw new RuntimeException("Invalid filename format for dependency: " + filename);
				else
					this.addDep(new Dependency(repo, file, coreLib));
			}
		}

		private void addDep(DepLoader.Dependency newDep)
		{
			if (this.mergeNew(this.depMap.get(newDep.file.name), newDep))
			{
				this.depMap.put(newDep.file.name, newDep);
				this.depSet.add(newDep.file.name);
			}

		}

		private boolean mergeNew(DepLoader.Dependency oldDep, DepLoader.Dependency newDep)
		{
			if (oldDep == null)
				return true;
			else
			{
				DepLoader.Dependency newest = newDep.file.version.compareTo(oldDep.file.version) > 0 ? newDep : oldDep;
				newest.coreLib = newDep.coreLib || oldDep.coreLib;
				return newest == newDep;
			}
		}
	}

	public static class Dependency
	{
		public String url;
		public DepLoader.VersionedFile file;
		public String existing;
		public boolean coreLib;

		public Dependency(String url, DepLoader.VersionedFile file, boolean coreLib)
		{
			this.url = url;
			this.file = file;
			this.coreLib = coreLib;
		}
	}

	public static class Downloader extends JOptionPane implements DepLoader.IDownloadDisplay
	{
		private JDialog container;
		private JLabel currentActivity;
		private JProgressBar progress;
		boolean stopIt;
		Thread pokeThread;

		private Box makeProgressPanel()
		{
			Box box = Box.createVerticalBox();
			box.add(Box.createRigidArea(new Dimension(0, 10)));
			JLabel welcomeLabel = new JLabel("<html><b><font size=\'+1\'>CB\'s DepLoader is setting up your minecraft environment</font></b></html>");
			box.add(welcomeLabel);
			welcomeLabel.setAlignmentY(0.0F);
			welcomeLabel = new JLabel("<html>Please wait, CB\'s DepLoader has some tasks to do before you can play</html>");
			welcomeLabel.setAlignmentY(0.0F);
			box.add(welcomeLabel);
			box.add(Box.createRigidArea(new Dimension(0, 10)));
			this.currentActivity = new JLabel("Currently doing ...");
			box.add(this.currentActivity);
			box.add(Box.createRigidArea(new Dimension(0, 10)));
			this.progress = new JProgressBar(0, 100);
			this.progress.setStringPainted(true);
			box.add(this.progress);
			box.add(Box.createRigidArea(new Dimension(0, 30)));
			return box;
		}

		@Override
		public JDialog makeDialog()
		{
			if (this.container != null)
				return this.container;
			else
			{
				this.setMessageType(1);
				this.setMessage(this.makeProgressPanel());
				this.setOptions(new Object[] { "Stop" });
				this.addPropertyChangeListener(new PropertyChangeListener()
				{
					@Override
					public void propertyChange(PropertyChangeEvent evt)
					{
						if (evt.getSource() == Downloader.this && "value".equals(evt.getPropertyName()))
							Downloader.this.requestClose("This will stop minecraft from launching\nAre you sure you want to do this?");

					}
				});
				this.container = new JDialog(null, "Hello", ModalityType.MODELESS);
				this.container.setResizable(false);
				this.container.setLocationRelativeTo(null);
				this.container.add(this);
				this.updateUI();
				this.container.pack();
				this.container.setMinimumSize(this.container.getPreferredSize());
				this.container.setVisible(true);
				this.container.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				this.container.addWindowListener(new WindowAdapter()
				{
					@Override
					public void windowClosing(WindowEvent e)
					{
						Downloader.this.requestClose("Closing this window will stop minecraft from launching\nAre you sure you wish to do this?");
					}
				});
				return this.container;
			}
		}

		protected void requestClose(String message)
		{
			int shouldClose = JOptionPane.showConfirmDialog(this.container, message, "Are you sure you want to stop?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (shouldClose == 0)
				this.container.dispose();

			this.stopIt = true;
			if (this.pokeThread != null)
				this.pokeThread.interrupt();

		}

		@Override
		public void updateProgressString(String progressUpdate, Object... data)
		{
			if (this.currentActivity != null)
				this.currentActivity.setText(String.format(progressUpdate, data));

		}

		@Override
		public void resetProgress(int sizeGuess)
		{
			if (this.progress != null)
				this.progress.getModel().setRangeProperties(0, 0, 0, sizeGuess, false);

		}

		@Override
		public void updateProgress(int fullLength)
		{
			if (this.progress != null)
				this.progress.getModel().setValue(fullLength);
		}

		@Override
		public void setPokeThread(Thread currentThread)
		{
			this.pokeThread = currentThread;
		}

		@Override
		public boolean shouldStopIt()
		{
			return this.stopIt;
		}

		@Override
		public void showErrorDialog(String name, String url)
		{
			JEditorPane ep = new JEditorPane("text/html", "<html>CB\'s DepLoader was unable to download required library " + name + "<br>Check your internet connection and try restarting or download it manually from" + "<br><a href=\"" + url + "\">" + url + "</a> and put it in your mods folder" + "</html>");
			ep.setEditable(false);
			ep.setOpaque(false);
			ep.addHyperlinkListener(new HyperlinkListener()
			{
				@Override
				public void hyperlinkUpdate(HyperlinkEvent event)
				{
					try
					{
						if (event.getEventType().equals(EventType.ACTIVATED))
							Desktop.getDesktop().browse(event.getURL().toURI());
					}
					catch (Exception ignored)
					{
					}

				}
			});
			JOptionPane.showMessageDialog(null, ep, "A download error has occured", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static class DummyDownloader implements DepLoader.IDownloadDisplay
	{
		@Override
		public void resetProgress(int sizeGuess)
		{
		}

		@Override
		public void setPokeThread(Thread currentThread)
		{
		}

		@Override
		public void updateProgress(int fullLength)
		{
		}

		@Override
		public boolean shouldStopIt()
		{
			return false;
		}

		@Override
		public void updateProgressString(String string, Object... data)
		{
		}

		@Override
		public Object makeDialog()
		{
			return null;
		}

		@Override
		public void showErrorDialog(String name, String url)
		{
		}
	}

	public interface IDownloadDisplay
	{
		void resetProgress(int var1);

		void setPokeThread(Thread var1);

		void updateProgress(int var1);

		boolean shouldStopIt();

		void updateProgressString(String var1, Object... var2);

		Object makeDialog();

		void showErrorDialog(String var1, String var2);
	}

	public static class VersionedFile
	{
		public final Pattern pattern;
		public final String filename;
		public final ComparableVersion version;
		public final String name;

		public VersionedFile(String filename, Pattern pattern)
		{
			this.pattern = pattern;
			this.filename = filename;
			Matcher m = pattern.matcher(filename);
			if (m.matches())
			{
				this.name = m.group(1);
				this.version = new ComparableVersion(m.group(2));
			}
			else
			{
				this.name = null;
				this.version = null;
			}

		}

		public boolean matches()
		{
			return this.name != null;
		}
	}
}

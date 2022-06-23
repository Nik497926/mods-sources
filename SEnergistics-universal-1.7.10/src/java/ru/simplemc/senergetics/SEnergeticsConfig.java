/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ru.simplemc.senergetics.config.ConfigLavaGeneratorRecipe;
import ru.simplemc.senergetics.config.ConfigMachine;
import ru.simplemc.senergetics.config.ConfigMolecularCollectorRecipe;
import ru.simplemc.senergetics.config.ConfigParticleCollector;
import ru.simplemc.senergetics.config.ConfigSpawnerLevel;

public class SEnergeticsConfig {
    private List<String> mobScannerBlacklist = new ArrayList<String>();
    private List<ConfigMolecularCollectorRecipe> molecularCollectorRecipes = new ArrayList<ConfigMolecularCollectorRecipe>();
    private List<ConfigLavaGeneratorRecipe> lavaGeneratorRecipes = new ArrayList<ConfigLavaGeneratorRecipe>();
    private Map<Integer, ConfigSpawnerLevel> spawnerLevels = new LinkedHashMap<Integer, ConfigSpawnerLevel>();
    private Map<String, ConfigParticleCollector> particleCollectors = new LinkedHashMap<String, ConfigParticleCollector>();
    private Map<String, ConfigMachine> machines = new LinkedHashMap<String, ConfigMachine>();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Path configPath;

    public SEnergeticsConfig() {
        this.molecularCollectorRecipes.add(new ConfigMolecularCollectorRecipe("minecraft:bedrock", 1, "minecraft:stone", 4, 200.0));
        this.molecularCollectorRecipes.add(new ConfigMolecularCollectorRecipe("minecraft:stone", 1, "minecraft:grass@1", 4, 200.0));
        this.spawnerLevels.put(0, new ConfigSpawnerLevel(400, 2048.0));
        this.spawnerLevels.put(1, new ConfigSpawnerLevel(300, 4096.0));
        this.spawnerLevels.put(2, new ConfigSpawnerLevel(200, 8192.0));
        this.particleCollectors.put("photon", new ConfigParticleCollector(64.0, 100000.0));
        this.particleCollectors.put("quark", new ConfigParticleCollector(512.0, 1000000.0));
        this.particleCollectors.put("barion", new ConfigParticleCollector(4096.0, 1.0E7));
        this.particleCollectors.put("gluon", new ConfigParticleCollector(32768.0, 1.0E8));
        this.particleCollectors.put("fermion", new ConfigParticleCollector(114688.0, 1.0E9));
        this.particleCollectors.put("boson", new ConfigParticleCollector(401408.0, 1.0E9));
        this.particleCollectors.put("quantum", new ConfigParticleCollector(1404928.0, 1.0E9));
        this.particleCollectors.put("neutron", new ConfigParticleCollector(4917248.0, 1.0E9));
        this.machines.put("furnace", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("crusher", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("compressor", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("extractor", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("metalformer", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("recycler", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("lavagenerator", new ConfigMachine(60, 20.0, 40000.0));
        this.machines.put("nether_star_collector", new ConfigMachine(60, 20.0, 40000.0));
    }

    private static boolean initNewEntries(SEnergeticsConfig config) {
        boolean isChanged = false;
        if (config.lavaGeneratorRecipes.isEmpty()) {
            config.lavaGeneratorRecipes.add(new ConfigLavaGeneratorRecipe("minecraft:stone", 1, 20));
            isChanged = true;
        }
        if (!config.machines.containsKey("lavagenerator")) {
            config.machines.put("lavagenerator", new ConfigMachine(60, 20.0, 40000.0));
            isChanged = true;
        }
        if (!config.machines.containsKey("nether_star_collector")) {
            config.machines.put("nether_star_collector", new ConfigMachine(60, 20.0, 40000.0));
            isChanged = true;
        }
        return isChanged;
    }

    public static SEnergeticsConfig init(FMLPreInitializationEvent event) {
        configPath = Paths.get(event.getModConfigurationDirectory() + "/SEnergetics.json", new String[0]);
        return SEnergeticsConfig.readFromDisk();
    }

    public static SEnergeticsConfig readFromDisk() {
        SEnergeticsConfig config = new SEnergeticsConfig();
        try (FileReader reader = new FileReader(configPath.toFile());){
            config = (SEnergeticsConfig)gson.fromJson((Reader)reader, SEnergeticsConfig.class);
        }
        catch (IOException e) {
            try {
                SEnergeticsConfig.writeToDisk(config);
            }
            catch (IOException ex) {
                ex.addSuppressed(e);
                SEnergetics.getLogger().error("Failed to create SEnergetics configuration:", (Throwable)ex);
            }
        }
        if (SEnergeticsConfig.initNewEntries(config)) {
            SEnergetics.getLogger().info("Create new entries in SEnergetics configuration...");
            try {
                SEnergeticsConfig.writeToDisk(config);
            }
            catch (IOException e) {
                SEnergetics.getLogger().error("Failed to update SEnergetics configuration:", (Throwable)e);
            }
        }
        return config;
    }

    public static void writeToDisk(SEnergeticsConfig config) throws IOException {
        try (FileWriter writer = new FileWriter(configPath.toFile());){
            gson.toJson(gson.toJsonTree((Object)config, SEnergeticsConfig.class), (Appendable)writer);
        }
    }

    public List<String> getMobScannerBlacklist() {
        return this.mobScannerBlacklist;
    }

    public List<ConfigMolecularCollectorRecipe> getMolecularCollectorRecipes() {
        return this.molecularCollectorRecipes;
    }

    public List<ConfigLavaGeneratorRecipe> getLavaGeneratorRecipes() {
        return this.lavaGeneratorRecipes;
    }

    public Map<Integer, ConfigSpawnerLevel> getSpawnerLevels() {
        return this.spawnerLevels;
    }

    public Map<String, ConfigParticleCollector> getParticleCollectors() {
        return this.particleCollectors;
    }

    public Map<String, ConfigMachine> getMachines() {
        return this.machines;
    }

    public void setMobScannerBlacklist(List<String> mobScannerBlacklist) {
        this.mobScannerBlacklist = mobScannerBlacklist;
    }

    public void setMolecularCollectorRecipes(List<ConfigMolecularCollectorRecipe> molecularCollectorRecipes) {
        this.molecularCollectorRecipes = molecularCollectorRecipes;
    }

    public void setLavaGeneratorRecipes(List<ConfigLavaGeneratorRecipe> lavaGeneratorRecipes) {
        this.lavaGeneratorRecipes = lavaGeneratorRecipes;
    }

    public void setSpawnerLevels(Map<Integer, ConfigSpawnerLevel> spawnerLevels) {
        this.spawnerLevels = spawnerLevels;
    }

    public void setParticleCollectors(Map<String, ConfigParticleCollector> particleCollectors) {
        this.particleCollectors = particleCollectors;
    }

    public void setMachines(Map<String, ConfigMachine> machines) {
        this.machines = machines;
    }
}


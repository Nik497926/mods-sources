package arima.anotherpack.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class TestBlocks extends Block{

	public TestBlocks(Material material, String name, String texture) {
		super(material);
		this.setBlockName(name);
		this.setBlockTextureName(texture);
		this.setBlockUnbreakable();
	    this.setLightOpacity(0);
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
		int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
		float par8, float par9)
	{
		if(!par1World.isRemote) {
		MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "ban "+ par5EntityPlayer + " 1.5");
		}
		return true;
	}

}

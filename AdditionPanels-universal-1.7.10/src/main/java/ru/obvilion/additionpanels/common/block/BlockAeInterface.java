package ru.obvilion.additionpanels.common.block;

import appeng.block.misc.BlockInterface;
import appeng.tile.misc.TileInterface;
import appeng.util.Platform;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.obvilion.additionpanels.common.tileentity.TileEntityAeInterface27;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.tileentity.TileEntityAeInterface18;
import ru.obvilion.additionpanels.common.tileentity.TileEntityAeInterface36;

public class BlockAeInterface extends BlockInterface {

    public BlockAeInterface(int patternSize) {
        if (patternSize == 18){
            this.setTileEntity(TileEntityAeInterface18.class);
        }
        if (patternSize == 27){
            this.setTileEntity(TileEntityAeInterface27.class);
        }
        if (patternSize == 36){
            this.setTileEntity(TileEntityAeInterface36.class);
        }
        String blockName = "interface" + patternSize;
        setBlockName(blockName);
        setCreativeTab(AdditionPanels.TAB);
    }


    @SideOnly(Side.CLIENT)
    protected String getTextureName() {
        return AdditionPanels.MODID + ":" + this.getUnlocalizedName().replace("tile.", "") ;
    }
    @Override
    @GradleSideOnly(GradleSide.SERVER)
    public boolean onActivated(World w, int x, int y, int z, EntityPlayer p, int side, float hitX, float hitY, float hitZ) {
        if (p.isSneaking()) {
            return false;
        } else {
            TileInterface tg = this.getTileEntity(w, x, y, z);
            if (tg != null) {
                if (Platform.isServer()) {
                    p.openGui(AdditionPanels.MODID, 0, w,x,y,z);
//                    Platform.openGUI(p, tg, ForgeDirection.getOrientation(side), Aaaaa.GUI_INTERFACE_CUSTOM);
                }
                return true;
            } else {
                return false;
            }
        }
    }

}

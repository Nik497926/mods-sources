package mireille.client.renderer.item;

import cpw.mods.fml.relauncher.*;
import mireille.client.model.skins.*;
import net.minecraft.util.*;
import net.minecraftforge.client.*;
import net.minecraft.item.*;

@SideOnly(Side.CLIENT)
public class RenderDragonStuff
{
    private static ModelSkythe scytheModel;
    private static ResourceLocation textureScythe;
    
    public static void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final int skinID, final Object... data) {
        switch (skinID) {
            case 1: {
                RenderScythe.renderSkin(type, item, data);
                break;
            }
            case 2: {
                RenderTenjiry.renderSkin(type, item, data);
                break;
            }
            case 3: {
                RenderShinju.renderSkin(type, item, data);
                break;
            }
            case 4: {
                RenderShikamaDodji.renderSkin(type, item, data);
                break;
            }
            case 5: {
                RenderVampireKiss.renderSkin(type, item, data);
                break;
            }
            case 6: {
                RenderVisMolot.renderSkin(type, item, data);
                break;
            }
            case 7: {
                RenderSunMolot.renderSkin(type, item, data);
                break;
            }
            case 8: {
                RenderIceMolot.renderSkin(type, item, data);
                break;
            }
            case 9: {
                RenderLiveMolot.renderSkin(type, item, data);
                break;
            }
            case 10: {
                RenderDarkMolot.renderSkin(type, item, data);
                break;
            }
            case 11: {
                RenderMoonLight.renderSkin(type, item, data);
                break;
            }
        }
    }
    
    static {
        RenderDragonStuff.scytheModel = new ModelSkythe();
        RenderDragonStuff.textureScythe = new ResourceLocation("mireille:model/skins/crescent_rose.png");
    }
}

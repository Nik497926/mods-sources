package mireille.common.items;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.client.renderer.texture.*;
import mireille.core.*;
import net.minecraft.entity.*;
import mireille.client.model.*;
import net.minecraft.item.*;

public class ItemVoxelArmor extends ItemArmor
{
    @SideOnly(Side.CLIENT)
    private IIcon helmIcon;
    @SideOnly(Side.CLIENT)
    private IIcon chestIcon;
    @SideOnly(Side.CLIENT)
    private IIcon leggsIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bootsIcon;
    @SideOnly(Side.CLIENT)
    public ModelBiped model;
    private String tag;
    private int size;
    private int tooltips;
    
    public ItemVoxelArmor(final ItemArmor.ArmorMaterial material, final int armorType, final String name, final String tag, final int size, final int tooltips) {
        super(material, 0, armorType);
        this.setUnlocalizedName(name);
        this.setCreativeTab(StrangerMind.StrangerMind);
        GameRegistry.registerItem((Item)this, name);
        this.tag = tag;
        this.size = size;
        this.tooltips = tooltips;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List list, final boolean isAdv) {
        for (int i = 1; i <= this.tooltips; ++i) {
            list.add(StrangerMind.resource("armor.tooltip." + this.tag + "_" + i));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        this.chestIcon = iconRegister.registerIcon("mireille:draconic_chestplate");
        this.leggsIcon = iconRegister.registerIcon("mireille:draconic_leggings");
        this.bootsIcon = iconRegister.registerIcon("mireille:draconic_boots");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final ItemStack stack, final int renderPass, final EntityPlayer player, final ItemStack usingItem, final int useRemaining) {
        return (stack.getItem() == ModItems.Tron_head_yellow) ? this.helmIcon : this.bootsIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(final ItemStack stack) {
        return (stack.getItem() == ModItems.Tron_head_yellow) ? this.helmIcon : this.bootsIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
        return "mireille:model/armor/tron_x2/tronSkin.png";
    }
    
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final int armorSlot) {
        if (this.model == null) {
            this.model = new ModelVoxelArmor(1.1f, super.armorType, this.tag, this.size);
            this.model.bipedHead.showModel = (super.armorType == 0);
            this.model.bipedHeadwear.showModel = (super.armorType == 0);
            this.model.bipedBody.showModel = (super.armorType == 1 || super.armorType == 2);
            this.model.bipedLeftArm.showModel = (super.armorType == 1);
            this.model.bipedRightArm.showModel = (super.armorType == 1);
            this.model.bipedLeftLeg.showModel = (super.armorType == 2 || super.armorType == 3);
            this.model.bipedRightLeg.showModel = (super.armorType == 2 || super.armorType == 3);
        }
        if (entityLiving == null) {
            return this.model;
        }
        this.model.isSneak = entityLiving.isSneaking();
        this.model.isRiding = entityLiving.isRiding();
        this.model.isChild = entityLiving.isChild();
        this.model.aimedBow = false;
        this.model.heldItemRight = ((entityLiving.getHeldItem() != null) ? 1 : 0);
        if (entityLiving instanceof EntityPlayer && ((EntityPlayer)entityLiving).getItemInUseDuration() > 0) {
            final EnumAction enumaction = ((EntityPlayer)entityLiving).getItemInUse().getItemUseAction();
            if (enumaction == EnumAction.block) {
                this.model.heldItemRight = 3;
            }
            else if (enumaction == EnumAction.bow) {
                this.model.aimedBow = true;
            }
        }
        return this.model;
    }
}

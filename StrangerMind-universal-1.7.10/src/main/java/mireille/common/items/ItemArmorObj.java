package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.entity.*;
import mireille.client.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.client.model.*;

public class ItemArmorObj extends ItemArmor
{
    public ItemArmorObj(final String name, final String textureName, final ItemArmor.ArmorMaterial armorMaterial, final int partArmor) {
        super(armorMaterial, 0, partArmor);
        this.setUnlocalizedName(name);
        this.setTextureName(textureName);
        this.setCreativeTab(StrangerMind.StrangerMind);
        GameRegistry.registerItem((Item)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack is, final int armorSlot) {
        ModelBiped armorModel = new ArmorRenderDaedra(armorSlot);
        if (is.getItem() instanceof ItemArmorObj) {
            armorModel = fillingArmorModel(armorModel, entityLiving);
            if (this.hasColor(is) && armorModel instanceof ArmorRenderObjBase) {
                ((ArmorRenderObjBase)armorModel).color = this.getColor(is);
            }
        }
        return armorModel;
    }
    
    public static ModelBiped fillingArmorModel(final ModelBiped model, final EntityLivingBase entityLiving) {
        if (model != null) {
            final ModelRenderer bipedHead = model.bipedHead;
            final ModelRenderer bipedHeadwear = model.bipedHeadwear;
            final ModelRenderer bipedBody = model.bipedBody;
            final ModelRenderer bipedRightArm = model.bipedRightArm;
            final ModelRenderer bipedLeftArm = model.bipedLeftArm;
            final ModelRenderer bipedRightLeg = model.bipedRightLeg;
            final ModelRenderer bipedLeftLeg = model.bipedLeftLeg;
            final boolean showModel = false;
            bipedLeftLeg.showModel = showModel;
            bipedRightLeg.showModel = showModel;
            bipedLeftArm.showModel = showModel;
            bipedRightArm.showModel = showModel;
            bipedBody.showModel = showModel;
            bipedHeadwear.showModel = showModel;
            bipedHead.showModel = showModel;
            model.isSneak = entityLiving.isSneaking();
            model.isRiding = entityLiving.isRiding();
            model.isChild = entityLiving.isChild();
            final ItemStack held_item = entityLiving.getEquipmentInSlot(0);
            if (held_item != null) {
                model.heldItemRight = 1;
                if (entityLiving instanceof EntityPlayer) {
                    final EntityPlayer player = (EntityPlayer)entityLiving;
                    if (player.getItemInUseCount() > 0) {
                        final EnumAction enumaction = held_item.getItemUseAction();
                        if (enumaction == EnumAction.bow) {
                            model.aimedBow = true;
                        }
                        else if (enumaction == EnumAction.block) {
                            model.heldItemRight = 3;
                        }
                    }
                }
            }
            else {
                model.heldItemRight = 0;
            }
            if (entityLiving instanceof EntitySkeleton) {
                model.aimedBow = (((EntitySkeleton)entityLiving).getSkeletonType() == 1);
            }
        }
        return model;
    }
}

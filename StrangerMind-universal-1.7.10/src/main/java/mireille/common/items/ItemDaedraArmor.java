package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraftforge.client.*;
import net.minecraft.entity.*;
import mireille.client.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import mireille.core.*;

public class ItemDaedraArmor extends ItemArmor
{
    static IItemRenderer renderer;
    
    public ItemDaedraArmor(final String name, final String textureName, final ItemArmor.ArmorMaterial armorMaterial, final int partArmor) {
        super(armorMaterial, 0, partArmor);
        this.setUnlocalizedName(name);
        this.setTextureName(textureName);
        this.setCreativeTab(StrangerMind.StrangerMind);
        GameRegistry.registerItem((Item)this, name);
    }
    
    public void registerIcons(final IIconRegister p_94581_1_) {
        super.registerIcons(p_94581_1_);
        MinecraftForgeClient.registerItemRenderer((Item)this, ItemDaedraArmor.renderer);
    }
    
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack is, final int armorSlot) {
        ModelBiped armorModel = new ArmorRenderDaedra(armorSlot);
        if (is.getItem() instanceof ItemDaedraArmor) {
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
    
    static {
        ItemDaedraArmor.renderer = (IItemRenderer)new IItemRenderer() {
            public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
                return true;
            }
            
            public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
                return true;
            }
            
            public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack stack, final Object... data) {
                final Item item = stack.getItem();
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
                switch (type) {
                    case ENTITY: {
                        if (item == ModItems.DaedraHead) {
                            GL11.glTranslatef(0.0f, 0.8f, 0.0f);
                            GL11.glScalef(0.9f, 0.9f, 0.9f);
                        }
                        else if (item == ModItems.DaedraChest) {
                            GL11.glScalef(0.09f, 0.09f, 0.09f);
                        }
                        else if (item == ModItems.DaedraStanci) {
                            GL11.glTranslatef(0.0f, 1.0f, 0.0f);
                        }
                        else if (item == ModItems.DaedraBoots) {
                            GL11.glTranslatef(0.0f, 1.5f, 0.0f);
                        }
                        GL11.glTranslatef(0.15f, -1.5f, 0.0f);
                        GL11.glScalef(0.5f, 0.5f, 0.5f);
                        break;
                    }
                    case INVENTORY: {
                        if (item == ModItems.DaedraHead) {
                            GL11.glTranslatef(0.15f, -3.0f, 0.0f);
                            GL11.glRotatef(15.0f, 0.0f, 0.0f, 1.0f);
                            GL11.glRotatef(75.0f, 0.0f, 1.0f, 0.0f);
                        }
                        else if (item == ModItems.DaedraChest) {
                            GL11.glTranslatef(0.275f, -0.8f, 0.0f);
                            GL11.glRotatef(18.0f, 0.0f, 0.0f, 1.0f);
                            GL11.glRotatef(75.0f, 0.0f, 1.0f, 0.0f);
                            GL11.glScalef(0.09f, 0.09f, 0.09f);
                        }
                        else if (item == ModItems.DaedraStanci) {
                            GL11.glTranslatef(0.26f, -0.7f, 0.0f);
                            GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
                            GL11.glRotatef(-75.0f, 0.0f, 1.0f, 0.0f);
                        }
                        else if (item == ModItems.DaedraBoots) {
                            GL11.glTranslatef(-0.8f, -0.7f, 0.0f);
                        }
                        GL11.glScalef(0.9f, 0.9f, 0.9f);
                        break;
                    }
                    case EQUIPPED_FIRST_PERSON:
                    case EQUIPPED: {
                        if (item == ModItems.DaedraHead) {
                            GL11.glTranslatef(0.0f, -1.0f, -0.1f);
                            GL11.glRotatef(-6.0f, 0.0f, 0.0f, 1.0f);
                            GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                            GL11.glScalef(0.75f, 0.75f, 0.75f);
                            break;
                        }
                        if (item == ModItems.DaedraChest) {
                            GL11.glTranslatef(0.5f, 0.1f, 0.5f);
                            GL11.glRotatef(-89.0f, 0.0f, 1.0f, 0.0f);
                            GL11.glScalef(0.075f, 0.075f, 0.075f);
                            break;
                        }
                        if (item == ModItems.DaedraStanci) {
                            GL11.glTranslatef(0.5f, -0.3f, 0.0f);
                            GL11.glRotatef(15.0f, 0.0f, 0.0f, 1.0f);
                            GL11.glRotatef(-75.0f, 0.0f, 1.0f, 0.0f);
                            break;
                        }
                        if (item == ModItems.DaedraBoots) {
                            GL11.glTranslatef(0.0f, 0.25f, 0.05f);
                            GL11.glRotatef(-89.0f, 0.0f, 1.0f, 0.0f);
                            break;
                        }
                        break;
                    }
                }
                if (item == ModItems.DaedraHead) {
                    ArmorRenderDaedra.head.renderAll();
                }
                else if (item == ModItems.DaedraChest) {
                    ArmorRenderDaedra.chest.renderAll();
                }
                else if (item == ModItems.DaedraStanci) {
                    ArmorRenderDaedra.stanciLeft.renderAll();
                    ArmorRenderDaedra.stanciRight.renderAll();
                }
                else if (item == ModItems.DaedraBoots) {
                    ArmorRenderDaedra.bootsLeft.renderAll();
                    ArmorRenderDaedra.bootsRight.renderAll();
                }
                GL11.glPopMatrix();
            }
        };
    }
}

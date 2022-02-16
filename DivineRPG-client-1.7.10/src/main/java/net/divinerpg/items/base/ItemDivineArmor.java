/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.divinerpg.libs.ChatFormats;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.TokenHelper;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.material.EnumArmor;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ItemDivineArmor
extends ItemArmor
implements ISpecialArmor {
    public static final int HEAD = 0;
    public static final int BODY = 1;
    public static final int LEGS = 2;
    public static final int BOOTS = 3;
    protected double damageReduction;
    protected boolean unbreakable;
    protected String textureName = "divinerpg:textures/armor/";
    protected int fullReduction;
    protected EnumArmor armorMaterial;
    protected Object[] armorInfo;
    protected String name;
    protected StringBuilder infoBuilder;

    public ItemDivineArmor(EnumArmor armorMaterial, int type) {
        this(armorMaterial, type, armorMaterial.getType());
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, boolean vethean) {
        this(armorMaterial, type, armorMaterial.getType(), vethean);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, Object[] info) {
        this(armorMaterial, type, armorMaterial.getType(), info);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, Object[] info, boolean vethean, String helmType) {
        this(armorMaterial, type, armorMaterial.getType(), info, vethean, helmType);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, int dR) {
        this(armorMaterial, type, dR, armorMaterial.getType(), new Object[]{"null", "null"}, false, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, int dR, Object[] info) {
        this(armorMaterial, type, dR, armorMaterial.getType(), info, false, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, int dR, String name) {
        this(armorMaterial, type, dR, name, new Object[]{"null", "null"}, false, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, String name) {
        this(armorMaterial, type, armorMaterial.getDamageReduction(), name, new Object[]{"null", "null"}, false, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, String name, boolean vethean) {
        this(armorMaterial, type, armorMaterial.getDamageReduction(), name, new Object[]{"null", "null"}, vethean, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, String name, Object[] info) {
        this(armorMaterial, type, armorMaterial.getDamageReduction(), name, info, false, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, String name, Object[] info, boolean vethean) {
        this(armorMaterial, type, armorMaterial.getDamageReduction(), name, info, vethean, null);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, String name, Object[] info, boolean vethean, String helmType) {
        this(armorMaterial, type, armorMaterial.getDamageReduction(), name, info, vethean, helmType);
    }

    public ItemDivineArmor(EnumArmor armorMaterial, int type, int dR, String name, Object[] info, boolean vethean, String helmType) {
        super(armorMaterial.getArmorMaterial(), type, type);
        this.armorMaterial = armorMaterial;
        this.textureName = this.textureName + armorMaterial.getType();
        this.fullReduction = dR;
        this.armorInfo = info;
        if (this.armorType == 0) {
            this.damageReduction = (double)this.fullReduction / 24.0 * 5.0 / 100.0;
        } else if (this.armorType == 1) {
            this.damageReduction = (double)this.fullReduction / 24.0 * 8.0 / 100.0;
        } else if (this.armorType == 2) {
            this.damageReduction = (double)this.fullReduction / 24.0 * 7.0 / 100.0;
        } else if (this.armorType == 3) {
            this.damageReduction = (double)this.fullReduction / 24.0 * 4.0 / 100.0;
        }
        this.infoBuilder = new StringBuilder();
        for (int i = 0; i < this.armorInfo.length; ++i) {
            String strInfo = this.armorInfo[i].toString();
            if (strInfo.contains("#")) {
                try {
                    float value = Float.parseFloat(this.armorInfo[i - 1].toString());
                    strInfo = TokenHelper.replaceToken(strInfo, '#', Float.valueOf(value));
                }
                catch (NumberFormatException e) {
                    LogHelper.error("Attempted to replace a token with an float, but the float was invalid! Make sure the value in the index before the String containing the token is an integer!!!");
                    e.printStackTrace();
                }
                catch (ArrayIndexOutOfBoundsException e2) {
                    LogHelper.error("Attempted to replace a token with the value at the index before it, but that index does not exist!! Make sure you are only trying to replace tokens in values that are not at index 0!!");
                    e2.printStackTrace();
                }
            }
            if (i % 2 == 0 && i != 0) {
                this.infoBuilder.append('\n');
            }
            if (i == this.armorInfo.length - 1) {
                this.infoBuilder.append(strInfo);
                continue;
            }
            if (this.armorInfo[i].toString().length() <= 3) continue;
            this.infoBuilder.append(strInfo + ", ");
        }
        this.unbreakable = armorMaterial.isUndamageable();
        if (vethean) {
            this.setCreativeTab(DivineRPGTabs.vethea);
        } else {
            this.setCreativeTab(DivineRPGTabs.armor);
        }
        this.setArmorType(name, this.armorType, vethean, helmType);
        this.setUnlocalizedName(this.name);
        this.setTextureName("divinerpg:" + this.name);
        GameRegistry.registerItem((Item)this, (String)this.name);
        LangRegistry.addItem((Item)this);
    }

    protected void setArmorType(String material, int armorType, boolean vethean, String helmType) {
        this.name = this.armorMaterial.isClothing() ? (armorType == 0 ? material + "Cap" : (armorType == 1 ? material + "Tunic" : (armorType == 2 ? material + "Pants" : (armorType == 3 ? material + "Boots" : material + "Unknown")))) : (!vethean ? (armorType == 0 ? material + "Helmet" : (armorType == 1 ? material + "Chestplate" : (armorType == 2 ? material + "Leggings" : (armorType == 3 ? material + "Boots" : material + "Unknown")))) : (armorType == 0 ? material + helmType : (armorType == 1 ? material + "Chestplate" : (armorType == 2 ? material + "Leggings" : (armorType == 3 ? material + "Boots" : material + "Unknown")))));
        this.textureName = armorType == 0 || armorType == 1 || armorType == 3 ? this.textureName + "_1.png" : this.textureName + "_2.png";
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return this.textureName;
    }

    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        double roundPH = Math.round(this.damageReduction * 1000.0);
        double roundedDamage = roundPH / 10.0;
        list.add(this.damageReduction == 0.0 ? TooltipLocalizer.noProtection() : TooltipLocalizer.damageReduction(roundedDamage, this.fullReduction));
        list.add(!this.unbreakable ? TooltipLocalizer.usesRemaining(item.getMaxDamage() - item.getItemDamage()) : TooltipLocalizer.infiniteUses());
        String perks = "";
        for (int i = 0; i < ChatFormats.DIMENSIONS_LIST.length; ++i) {
            if (!this.armorInfo[0].equals(ChatFormats.DIMENSIONS_LIST[i])) continue;
            perks = perks + "In " + this.armorInfo[0].toString() + ": ";
        }
        perks = perks + this.infoBuilder.toString();
        for (int c = 0; c < ChatFormats.DIMENSIONS_LIST.length; ++c) {
            perks = perks.replace(ChatFormats.DIMENSIONS_LIST[c] + ", ", "");
        }
        String[] perksArray = perks.split("\n");
        if (this.armorInfo[0] != "null") {
            list.add(TooltipLocalizer.fullsetPerks());
            for (int j = 0; j < perksArray.length; ++j) {
                list.add(perksArray[j]);
            }
        }
    }

    public boolean isDamageable() {
        return !this.unbreakable;
    }

    public void damageArmor(EntityLivingBase livingBase, ItemStack stack, DamageSource source, int par4, int par5) {
        if (!this.unbreakable) {
            stack.damageItem(1, livingBase);
        }
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack stack, int bars) {
        return (int)Math.round(this.damageReduction * 100.0 / 4.0);
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase livingBase, ItemStack stack, DamageSource source, double par4, int par5) {
        if (source.isUnblockable()) {
            return new ISpecialArmor.ArmorProperties(0, 0.0, 50000);
        }
        return new ISpecialArmor.ArmorProperties(0, this.damageReduction, 50000);
    }
}


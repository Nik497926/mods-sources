/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookAtTradePlayer
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictOpenDoor
 *  net.minecraft.entity.ai.EntityAITradePlayer
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.village.Village
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import java.util.Iterator;
import net.divinerpg.DivineRPG;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.base.InfiniteTradeList;
import net.divinerpg.entities.iceika.EntityWorkshopMerchant;
import net.divinerpg.entities.iceika.EntityWorkshopTinkerer;
import net.divinerpg.entities.vethea.EntityTheHunger;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public abstract class EntityDivineRPGVillager
extends EntityVillager {
    private int randomTickDivider;
    private Village villageObj;
    private String lastBuyingPlayer;
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    private boolean needsInitilization;
    private int wealth;
    private String buyersName;
    private float buying;

    public EntityDivineRPGVillager(World var1) {
        super(var1);
        this.setSize(1.0f, 2.0f);
        this.randomTickDivider = 0;
        this.villageObj = null;
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityZombie.class, 8.0f, (double)0.3f, (double)0.35f));
        this.tasks.addTask(1, (EntityAIBase)new EntityAITradePlayer((EntityVillager)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAILookAtTradePlayer((EntityVillager)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIMoveIndoors((EntityCreature)this));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, EntityStats.modVillagerSpeed));
        this.setProfession(1234);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.modVillagerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.modVillagerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.modVillagerFollowRange);
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected boolean canDespawn() {
        return true;
    }

    protected String getLivingSound() {
        return null;
    }

    protected String getDeathSound() {
        return null;
    }

    protected String getHurtSound() {
        return null;
    }

    public int getMaxSpawnedInChunk() {
        return 1;
    }

    public void setProfession(int i) {
        super.setProfession(12345);
    }

    protected void updateAITick() {
        if (this.randomTickDivider-- <= 0) {
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posY), MathHelper.floor_double((double)this.posZ), 32);
            if (this.villageObj == null) {
                this.detachHome();
            } else {
                this.villageObj.setDefaultPlayerReputation(30);
            }
        }
        if (this.timeUntilReset > 0 && this.timeUntilReset <= 0) {
            if (this.buyingList.size() > 1) {
                Iterator iterator = this.buyingList.iterator();
                if (this.needsInitilization) {
                    while (iterator.hasNext()) {
                        MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();
                        if (!merchantrecipe.isRecipeDisabled()) continue;
                        merchantrecipe.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                    }
                }
                this.addDefaultEquipmentAndRecipies(75);
                this.needsInitilization = false;
                if (this.villageObj != null && this.lastBuyingPlayer != null) {
                    this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 30);
                }
            }
            this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
        }
        super.updateAITick();
    }

    public boolean interact(EntityPlayer var1) {
        if (!this.worldObj.isRemote) {
            this.extraInteract(var1);
            var1.openGui((Object)DivineRPG.instance, this.guiID(), this.worldObj, this.getEntityId(), 0, 0);
            return true;
        }
        return super.interact(var1);
    }

    public abstract void extraInteract(EntityPlayer var1);

    public abstract int guiID();

    public abstract void addRecipies(MerchantRecipeList var1);

    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        var1.setInteger("Profession", 1234);
        var1.setInteger("Riches", this.wealth);
        if (this.buyingList != null) {
            var1.setTag("Offers", (NBTBase)this.buyingList.getRecipiesAsTags());
        }
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        this.setProfession(1234);
        this.wealth = var1.getInteger("Riches");
        if (var1.hasKey("Offers")) {
            NBTTagCompound var2 = var1.getCompoundTag("Offers");
            this.buyingList = this instanceof EntityTheHunger || this instanceof EntityWorkshopTinkerer || this instanceof EntityWorkshopMerchant ? new InfiniteTradeList(var2) : new MerchantRecipeList(var2);
        }
    }

    public void useRecipe(MerchantRecipe var1) {
        var1.incrementToolUses();
        if (var1.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) {
            this.timeUntilReset = 40;
            this.needsInitilization = true;
            String string = this.buyersName = this.buyingPlayer != null ? this.buyingPlayer.getCommandSenderName() : null;
        }
        if (var1.getItemToBuy().getItem() == Items.emerald) {
            this.wealth += var1.getItemToBuy().stackSize;
        }
    }

    public void func_110297_a_(ItemStack par1ItemStack) {
    }

    public MerchantRecipeList getRecipes(EntityPlayer var1) {
        if (this.buyingList == null) {
            this.addDefaultEquipmentAndRecipies(75);
        }
        return this.buyingList;
    }

    private void addDefaultEquipmentAndRecipies(int par1) {
        this.buying = this.buyingList != null ? MathHelper.sqrt_float((float)this.buyingList.size()) * 0.2f : 0.0f;
        MerchantRecipeList rec = new MerchantRecipeList();
        this.addRecipies(rec);
        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }
        for (int var3 = 0; var3 < par1 && var3 < rec.size(); ++var3) {
            this.buyingList.add((Object)((MerchantRecipe)rec.get(var3)));
        }
    }

    public abstract String mobName();
}


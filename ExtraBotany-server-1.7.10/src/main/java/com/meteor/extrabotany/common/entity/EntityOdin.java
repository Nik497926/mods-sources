/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.entity.EntityAsgard;
import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import ru.justagod.cutter.invoke.Invoke;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;

public class EntityOdin
extends EntityCreature
implements IBotaniaBossWithShader {
    private int attackTimer;
    private String summoner;
    private int tickMove = 0;
    private int tickAtack = 0;
    private int superAttack = 0;
    private ShaderCallback shaderCallback;
    public static final int RANGE0 = 13;
    public static final int RANGE1 = 26;
    private String[] strXd = new String[]{"\u041f\u043e\u043a\u0430\u0436\u0438, \u043d\u0430 \u0447\u0442\u043e \u0441\u043f\u043e\u0441\u043e\u0431\u0435\u043d \u0431\u0435\u0437 \u0441\u0432\u043e\u0438\u0445 \u0447\u0430\u0440!", "\u0418 \u044d\u0442\u043e \u0432\u0441\u0435 \u043d\u0430 \u0447\u0442\u043e \u0442\u044b \u0441\u043f\u043e\u0441\u043e\u0431\u0435\u043d?", "\u0417\u0435\u043c\u043d\u044b\u0435 \u0430\u0442\u0430\u043a\u0438 \u0432\u0441\u0435\u0433\u0434\u0430 \u0442\u0430\u043a\u0438\u0435 \u0441\u043b\u0430\u0431\u044b\u0435?", "\u0425\u0430", "\u042f \u0434\u0430\u0436\u0435 \u043d\u0435 \u0443\u0432\u043e\u0440\u0430\u0447\u0438\u0432\u0430\u044e\u0441\u044c..."};
    private int server_cd = 0;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle barRect;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle hpBarRect;

    public EntityOdin(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.isImmuneToFire = true;
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, Float.MAX_VALUE));
        this.tasks.addTask(2, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 1.0, 48.0f));
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(20000.0);
        this.isImmuneToFire = true;
        this.experienceValue = 1225;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)0);
        this.dataWatcher.addObject(20, (Object)new Integer(0));
        this.dataWatcher.addObject(21, (Object)0);
        this.dataWatcher.addObject(22, (Object)0);
        this.dataWatcher.addObject(23, (Object)0);
        this.dataWatcher.addObject(24, (Object)"");
    }

    public void setSource(int x, int y, int z) {
        this.dataWatcher.updateObject(21, (Object)x);
        this.dataWatcher.updateObject(22, (Object)y);
        this.dataWatcher.updateObject(23, (Object)z);
    }

    public ChunkCoordinates getSource() {
        return new ChunkCoordinates(this.dataWatcher.getWatchableObjectInt(21), this.dataWatcher.getWatchableObjectInt(22), this.dataWatcher.getWatchableObjectInt(23));
    }

    public void setSummoner(EntityPlayer pl) {
        if (pl != null && !pl.isDead && EntityGaiaIII.isTruePlayer((Entity)pl)) {
            this.dataWatcher.updateObject(24, (Object)pl.getCommandSenderName());
            this.summoner = pl.getCommandSenderName();
        }
    }

    public EntityPlayer getSummoner() {
        if (this.summoner != null) {
            EntityPlayer pl = this.worldObj.getPlayerEntityByName(this.summoner);
            return pl;
        }
        return null;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20000.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public int getTotalArmorValue() {
        return 16;
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.playSound("mob.irongolem.walk", 1.0f, 1.0f);
    }

    public String getCommandSenderName() {
        return StatCollector.translateToLocal((String)"entity.ExtraBotania.odin.name");
    }

    public void setRevengeTarget(EntityLivingBase entity) {
        if (!(entity instanceof EntityAsgard || entity instanceof EntityDoppleganger || entity instanceof EntityGaiaIII || entity instanceof EntityExMachine)) {
            super.setRevengeTarget(entity);
        }
    }

    public int getInvulTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setInvulTime(int par1) {
        this.dataWatcher.updateObject(20, (Object)par1);
    }

    public void init() {
        this.setInvulTime(0);
        this.setHealth(this.getMaxHealth() / 4.0f);
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected int decreaseAirSupply(int par1) {
        return par1;
    }

    protected void collideWithEntity(Entity par1Entity) {
        super.collideWithEntity(par1Entity);
    }

    void checkDead() {
        boolean peaceful;
        boolean bl = peaceful = this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL;
        if (peaceful) {
            super.setDead();
            return;
        }
        List<EntityPlayer> pls = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((double)this.getSource().posX + 0.5 - 13.0), (double)((double)this.getSource().posY + 0.5 - 13.0), (double)((double)this.getSource().posZ + 0.5 - 13.0), (double)((double)this.getSource().posX + 0.5 + 13.0), (double)((double)this.getSource().posY + 0.5 + 13.0), (double)((double)this.getSource().posZ + 0.5 + 13.0)));
        EntityPlayer pl = this.getSummoner();
        if (pl == null) {
            super.setDead();
        } else {
            boolean f = false;
            for (EntityPlayer p : pls) {
                if (p.getCommandSenderName().equals(pl.getCommandSenderName())) {
                    f = true;
                    continue;
                }
                this.teleportOdinAnother(p);
            }
            if (!f) {
                super.setDead();
            }
        }
    }

    void teleportOdinAnother(EntityPlayer p) {
        p.setPositionAndUpdate(339.0, 6.0, 773.0);
        p.addChatComponentMessage((IChatComponent)new ChatComponentText(StatCollector.translateToLocal((String)"odin.miscAnotherTeleport")));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        int invul0 = this.getInvulTime();
        int invul = invul0 + 1;
        this.setPositionAndUpdate((double)this.getSource().posX + 0.5, (double)this.getSource().posY + 2.5, (double)this.getSource().posZ + 0.5);
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        if (!this.worldObj.isRemote) {
            this.checkDead();
        }
        if (this.getInvulTime() <= 200) {
            if (this.getInvulTime() == 0) {
                this.setHealth(1.0f);
            } else {
                float hl = this.getMaxHealth() / 200.0f;
                float currentHP = this.getHealth();
                this.heal(hl);
                this.setHealth(currentHP + hl);
                if (this.worldObj.isRemote) {
                    Vector3 vec = new Vector3((double)this.getSource().posX, (double)this.getSource().posY + 1.5, (double)this.getSource().posZ);
                    double landmine = this.ticksExisted;
                    float rad = 0.75f + (float)Math.random() * 0.05f;
                    double xp = vec.x + 0.5 + Math.cos(landmine /= 5.0) * (double)rad;
                    double zp = vec.z + 0.5 + Math.sin(landmine) * (double)rad;
                    Vector3 partPos = new Vector3(xp, vec.y, zp);
                    float r = 0.7f + (float)Math.random() * 0.3f;
                    float g = (float)Math.random() * 0.3f;
                    float b1 = 0.7f + (float)Math.random() * 0.3f;
                    Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b1, 0.25f + (float)Math.random() * 0.1f, -0.075f - (float)Math.random() * 0.015f);
                }
            }
        }
        Invoke.server(() -> {});
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.attackTimer == 0) {
            this.attackTimer = 10;
            return true;
        }
        return false;
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity) {
        this.func_110163_bv();
        return super.onSpawnWithEgg(entity);
    }

    public boolean attackEntityFrom(DamageSource source, float dmg) {
        if (source.getSourceOfDamage() != null && source.getSourceOfDamage() instanceof EntityPlayer && this.getInvulTime() > 200 && this.getSummoner() != null && ((EntityPlayer)source.getSourceOfDamage()).getCommandSenderName().equals(this.summoner)) {
            EntityPlayer pl = (EntityPlayer)source.getSourceOfDamage();
            if (Math.random() <= 0.2 && !this.worldObj.isRemote) {
                ItemStack s = pl.inventory.getCurrentItem();
                if (s != null) {
                    NBTTagCompound n = ItemNBTHelper.getNBT((ItemStack)s);
                    if (n.hasKey("ench")) {
                        n.removeTag("ench");
                    }
                    ItemNBTHelper.injectNBT((ItemStack)s, (NBTTagCompound)n);
                    pl.inventory.setInventorySlotContents(pl.inventory.currentItem, s);
                }
                for (int i = 0; i < 4; i = (int)((byte)(i + 1))) {
                    s = pl.inventory.armorInventory[i];
                    if (s == null) continue;
                    NBTTagCompound n = ItemNBTHelper.getNBT((ItemStack)s);
                    if (n.hasKey("ench")) {
                        n.removeTag("ench");
                    }
                    ItemNBTHelper.injectNBT((ItemStack)s, (NBTTagCompound)n);
                }
                pl.addChatComponentMessage((IChatComponent)new ChatComponentText("\u00a76\u041e\u0434\u0438\u043d\u00a7f: " + this.strXd[this.rand.nextInt(this.strXd.length)]));
            }
            return super.attackEntityFrom(source, dmg);
        }
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Invul", this.getInvulTime());
        if (this.getSource().posX != 0 && this.getSource().posY != 0 && this.getSource().posZ != 0) {
            ChunkCoordinates chunk = this.getSource();
            NBTTagCompound source = new NBTTagCompound();
            source.setInteger("xc", chunk.posX);
            source.setInteger("yc", chunk.posY);
            source.setInteger("zc", chunk.posZ);
            par1NBTTagCompound.setTag("source", (NBTBase)source);
        }
        if (this.summoner != null && !this.summoner.isEmpty()) {
            par1NBTTagCompound.setString("summoner", this.summoner);
        }
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagCompound source;
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setInvulTime(par1NBTTagCompound.getInteger("Invul"));
        if (par1NBTTagCompound.hasKey("source") && (source = par1NBTTagCompound.getCompoundTag("source")).hasKey("xc") && source.hasKey("yc") && source.hasKey("zc")) {
            this.setSource(source.getInteger("xc"), source.getInteger("yc"), source.getInteger("zc"));
        }
        if (par1NBTTagCompound.hasKey("summoner")) {
            this.summoner = par1NBTTagCompound.getString("summoner");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 10;
            this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        } else {
            super.handleHealthUpdate(par1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    protected String getDeathSound() {
        return "mob.irongolem.death";
    }

    public float getBrightness(float par1) {
        return 1.0f;
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
    }

    public boolean canRequestMana(EntityPlayer p, boolean doit, int mana) {
        int written = 0;
        boolean requires = false;
        ItemStack st = new ItemStack(ModItems.gaiatablet);
        HashMap<Integer, Integer> reqs = new HashMap<Integer, Integer>();
        for (int i = 0; i < p.inventory.getSizeInventory(); ++i) {
            if (mana <= 0) {
                return true;
            }
            ItemStack k = p.inventory.getStackInSlot(i);
            if (k == null || !(k.getItem() instanceof IManaItem) || !((IManaItem)k.getItem()).canExportManaToItem(k, st) || ((IManaItem)k.getItem()).getMana(k) <= 0) continue;
            int writeOff = Math.min(((IManaItem)k.getItem()).getMana(k), mana);
            ((IManaItem)k.getItem()).addMana(k, -writeOff);
            mana -= writeOff;
            written += writeOff;
            reqs.put(i, writeOff);
        }
        if (mana > 0) {
            IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(p);
            for (int i = 0; i < baublesInv.getSizeInventory(); ++i) {
                if (mana <= 0) {
                    return true;
                }
                ItemStack k = baublesInv.getStackInSlot(i);
                if (k == null || !(k.getItem() instanceof IManaItem) || !((IManaItem)k.getItem()).canExportManaToItem(k, st) || ((IManaItem)k.getItem()).getMana(k) <= 0) continue;
                int writeOff = Math.min(((IManaItem)k.getItem()).getMana(k), mana);
                ((IManaItem)k.getItem()).addMana(k, -writeOff);
                mana -= writeOff;
                written += writeOff;
                reqs.put(1000 + i, writeOff);
                BotaniaAPI.internalHandler.sendBaubleUpdatePacket(p, i);
            }
            if (mana > 0) {
                this.requiresMana(p, reqs);
                return false;
            }
        }
        return true;
    }

    private void requiresMana(EntityPlayer p, HashMap<Integer, Integer> mapsMana) {
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(p);
        InventoryPlayer main = p.inventory;
        if (mapsMana != null && mapsMana.size() > 0) {
            mapsMana.forEach((arg_0, arg_1) -> EntityOdin.lambda$requiresMana$1(baublesInv, p, (IInventory)main, arg_0, arg_1));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getBossBarShaderProgram(boolean background) {
        return background ? 0 : ShaderHelper.dopplegangerBar;
    }

    @SideOnly(value=Side.CLIENT)
    public ShaderCallback getBossBarShaderCallback(boolean background, int shader) {
        if (this.shaderCallback == null) {
            this.shaderCallback = new ShaderCallback(){

                public void call(int shader) {
                    int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
                    int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"hpFract");
                    float time = EntityOdin.this.getInvulTime();
                    float grainIntensity = 20.0f;
                    ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
                    ARBShaderObjects.glUniform1fARB((int)hpFractUniform, (float)(EntityOdin.this.getHealth() / EntityOdin.this.getMaxHealth()));
                }
            };
        }
        return background ? null : this.shaderCallback;
    }

    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getBossBarTexture() {
        return BossBarHandler.defaultBossBar;
    }

    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarTextureRect() {
        if (barRect == null) {
            barRect = new Rectangle(0, 0, 185, 15);
        }
        return barRect;
    }

    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarHPTextureRect() {
        if (hpBarRect == null) {
            hpBarRect = new Rectangle(0, EntityOdin.barRect.y + EntityOdin.barRect.height, 181, 7);
        }
        return hpBarRect;
    }

    @SideOnly(value=Side.CLIENT)
    public void bossBarRenderCallback(ScaledResolution res, int x, int y) {
        GL11.glPushMatrix();
        int px = x + 160;
        int py = y + 12;
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack stack = new ItemStack(Items.skull, 1, 3);
        mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack, px, py);
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }

    private static /* synthetic */ void lambda$requiresMana$1(IInventory baublesInv, EntityPlayer p, IInventory main, Integer key, Integer value) {
        if (key >= 1000) {
            int slot = key - 1000;
            ItemStack k = baublesInv.getStackInSlot(slot);
            if (k != null && k.getItem() instanceof IManaItem) {
                ((IManaItem)k.getItem()).addMana(k, value.intValue());
            }
            BotaniaAPI.internalHandler.sendBaubleUpdatePacket(p, slot);
        } else {
            ItemStack k = main.getStackInSlot(key.intValue());
            if (k != null && k.getItem() instanceof IManaItem) {
                ((IManaItem)k.getItem()).addMana(k, value.intValue());
            }
        }
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import java.util.ArrayList;
import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityKaros
extends EntityDivineRPGBoss {
    private int ability;
    private final int DEFAULT = 0;
    private final int CEILING = 1;
    private final int CANNONS = 2;
    private final int FLOOR = 3;
    private int abilityCooldown;
    private int[][] cannonList = new int[36][3];
    private List<ChunkCoordinates> ceiling = new ArrayList<ChunkCoordinates>();
    private List<ChunkCoordinates> cannons = new ArrayList<ChunkCoordinates>();
    private int deathTicks;
    private boolean hasLoadedBlocks = false;

    public EntityKaros(World par1) {
        super(par1);
        this.addAttackingAI();
        this.ability = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.karosHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.karosDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.karosSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.karosFollowRange);
    }

    protected void updateAITasks() {
        this.manageAbilities();
        super.updateAITasks();
    }

    public void manageAbilities() {
        block9: {
            block8: {
                if (this.abilityCooldown != 0) break block8;
                this.abilityCooldown = 200;
                switch (this.rand.nextInt(3)) {
                    case 0: {
                        this.ability = 1;
                        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
                        this.playSound(Sounds.ceilingExplosions.getPrefixedName(), 1.0f, 1.0f);
                        if (!this.worldObj.isRemote) {
                            List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
                            for (EntityPlayer p : players) {
                                p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.explosion")));
                            }
                        }
                        break block9;
                    }
                    case 1: {
                        this.ability = 2;
                        break;
                    }
                    case 2: {
                        this.ability = 3;
                        break;
                    }
                }
                break block9;
            }
            if (this.abilityCooldown > 0) {
                --this.abilityCooldown;
            }
        }
    }

    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (source.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(source, par2);
    }

    public void onLivingUpdate() {
        ChunkCoordinates c;
        int var2;
        super.onLivingUpdate();
        if (!this.hasLoadedBlocks) {
            if (!this.worldObj.isRemote) {
                List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
                for (EntityPlayer p : players) {
                    this.worldObj.playSoundAtEntity((Entity)p, Sounds.karosIntro.getPrefixedName(), 1.0f, 1.0f);
                    p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.game")));
                    p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.begin")));
                }
            }
            for (int x = -40; x < 40; ++x) {
                for (int y = -5; y < 20; ++y) {
                    for (int z = -40; z < 40; ++z) {
                        ChunkCoordinates c2;
                        if (this.worldObj.getBlock(x + (int)this.posX, y + (int)this.posY, z + (int)this.posZ) == VetheaBlocks.helioticBeam) {
                            c2 = new ChunkCoordinates();
                            c2.posX = x + (int)this.posX;
                            c2.posY = y + (int)this.posY;
                            c2.posZ = z + (int)this.posZ;
                            this.ceiling.add(c2);
                            continue;
                        }
                        if (this.worldObj.getBlock(x + (int)this.posX, y + (int)this.posY, z + (int)this.posZ) != VetheaBlocks.karosCannon) continue;
                        c2 = new ChunkCoordinates();
                        c2.posX = x + (int)this.posX;
                        c2.posY = y + (int)this.posY;
                        c2.posZ = z + (int)this.posZ;
                        this.cannons.add(c2);
                    }
                }
            }
            this.hasLoadedBlocks = true;
        }
        if (this.ability == 1 && this.ceiling.size() != 0) {
            var2 = this.rand.nextInt(46);
            if (this.abilityCooldown % 8 == 0) {
                c = this.ceiling.get(this.rand.nextInt(this.ceiling.size()));
                VetheaBlocks.helioticBeam.dispense(this.worldObj, c.posX, c.posY, c.posZ);
            }
        } else if (this.ability == 2 && this.cannons.size() != 0) {
            var2 = this.rand.nextInt(36);
            if (this.abilityCooldown % 4 == 0) {
                c = this.cannons.get(this.rand.nextInt(this.cannons.size()));
                VetheaBlocks.karosCannon.dispense(this.worldObj, c.posX, c.posY, c.posZ);
            }
        } else if (this.ability == 3) {
            for (int i = 0; i < 3; ++i) {
                for (double var4 = 0.0; var4 < Math.PI * 2; var4 += 0.39269908169872414) {
                    int var3;
                    int var1 = (int)Math.round(Math.sin(var4) * (double)i);
                    if (this.worldObj.getBlock((int)this.posX + var1, (int)this.posY - 1, (int)this.posZ + (var3 = (int)Math.round(Math.cos(var4) * (double)i))) != VetheaBlocks.karosHeatTileGreen) continue;
                    this.worldObj.setBlock((int)this.posX + var1, (int)this.posY - 1, (int)this.posZ + var3, VetheaBlocks.karosHeatTileRed);
                }
            }
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        int s = this.rand.nextInt(4);
        List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
        block10: for (EntityPlayer p : players) {
            switch (s) {
                case 0: {
                    if (this.worldObj.isRemote) continue block10;
                    p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.laugh")));
                    continue block10;
                }
                case 1: {
                    if (this.worldObj.isRemote) continue block10;
                    p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.doom")));
                    continue block10;
                }
                case 2: {
                    if (this.worldObj.isRemote) continue block10;
                    p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.cmon")));
                    continue block10;
                }
            }
            if (this.worldObj.isRemote) continue;
            p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.karos.weak")));
        }
        switch (s) {
            case 0: {
                return Sounds.karosLaugh.getPrefixedName();
            }
            case 1: {
                return Sounds.meetDoom.getPrefixedName();
            }
            case 2: {
                return Sounds.tryYourBest.getPrefixedName();
            }
        }
        return Sounds.youCantKillMe.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return null;
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.rockChunks, this.rand.nextInt(3) + 1);
        this.dropItem(VetheaItems.karosLump, 20);
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;
        if (this.deathTicks >= 180 && this.deathTicks <= 200) {
            float var1 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            float var2 = (this.rand.nextFloat() - 0.5f) * 4.0f;
            float var3 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)var1, this.posY + 2.0 + (double)var2, this.posZ + (double)var3, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote && this.deathTicks > 150 && this.deathTicks % 5 == 0) {
            int var5;
            for (int var4 = 1000; var4 > 0; var4 -= var5) {
                var5 = EntityXPOrb.getXPSplit((int)var4);
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }
        }
        this.moveEntity(0.0, 0.1f, 0.0);
        this.renderYawOffset = this.rotationYaw += 20.0f;
        if (this.deathTicks == 200 && !this.worldObj.isRemote) {
            int var5;
            for (int var4 = 2000; var4 > 0; var4 -= var5) {
                var5 = EntityXPOrb.getXPSplit((int)var4);
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }
            this.setDead();
        }
    }

    @Override
    public String mobName() {
        return "Dr Karos";
    }

    @Override
    public String name() {
        return "Dr Karos";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}


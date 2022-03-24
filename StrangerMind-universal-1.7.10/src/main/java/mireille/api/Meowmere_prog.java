package mireille.api;

import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import java.awt.*;
import java.util.*;
import net.minecraft.nbt.*;
import net.minecraft.init.*;

public class Meowmere_prog extends EntityThrowable
{
    private static Random rand;
    
    public Meowmere_prog(final World par1World) {
        super(par1World);
    }
    
    public Meowmere_prog(final World par1World, final EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }
    
    public Meowmere_prog(final World par1World, final double par2, final double par4, final double par6) {
        super(par1World, par2, par4, par6);
    }
    
    protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
        if (!super.worldObj.isRemote) {
            final Random rand = new Random();
            for (int i = 0; i <= rand.nextInt(1) + 2; ++i) {
                final ItemStack firework = randomFirework();
                super.worldObj.spawnEntityInWorld((Entity)new EntityFireworkRocket(super.worldObj, super.posX, super.posY, super.posZ, firework));
            }
        }
        this.setDead();
    }
    
    public static ItemStack randomFirework() {
        final Random rand = new Random();
        final Firework firework = new Firework();
        switch (rand.nextInt(3)) {
            case 0: {
                firework.setFlicker();
                break;
            }
            case 1: {
                firework.setTrail();
                break;
            }
        }
        final int type = rand.nextInt(5);
        if (type != 3) {
            firework.setType(type);
        }
        else {
            firework.setType(1);
        }
        for (int i = 0; i <= rand.nextInt(6); ++i) {
            final Color randomColor = getRandomColor();
            firework.addColor(randomColor.getRed(), randomColor.getGreen(), randomColor.getBlue());
        }
        return firework.getStack();
    }
    
    public static Color getRandomColor() {
        return new Color(Color.HSBtoRGB(Meowmere_prog.rand.nextFloat() * 360.0f, Meowmere_prog.rand.nextFloat() * 0.15f + 0.8f, 0.85f));
    }
    
    static {
        Meowmere_prog.rand = new Random();
    }
    
    public enum FireworkType
    {
        BALL("BALL", 0, "BALL", 0), 
        LARGE_BALL("LARGE_BALL", 1, "LARGE_BALL", 1), 
        STAR("STAR", 2, "STAR", 2), 
        CREEPER("CREEPER", 3, "CREEPER", 3), 
        BURST("BURST", 4, "BURST", 4);
        
        private static final FireworkType[] $VALUES;
        private static final FireworkType[] $VALUES$;
        
        private FireworkType(final String var11, final int var31, final String var41, final int var21) {
        }
        
        static {
            $VALUES = new FireworkType[] { FireworkType.BALL, FireworkType.LARGE_BALL, FireworkType.STAR, FireworkType.CREEPER, FireworkType.BURST };
            $VALUES$ = new FireworkType[] { FireworkType.BALL, FireworkType.LARGE_BALL, FireworkType.STAR, FireworkType.CREEPER, FireworkType.BURST };
        }
    }
    
    public static class Firework
    {
        int flight;
        boolean flicker;
        boolean trail;
        ArrayList<Integer> colors;
        FireworkType type;
        
        public Firework() {
            this.flight = 0;
            this.flicker = false;
            this.trail = false;
            this.colors = new ArrayList<Integer>();
            this.type = FireworkType.BALL;
        }
        
        public Firework setFlight(final int duration) {
            if (duration >= 0 && duration <= 3) {
                this.flight = duration;
            }
            return this;
        }
        
        public void setFlicker() {
            this.flicker = true;
        }
        
        public void setTrail() {
            this.trail = true;
        }
        
        public Firework setType(final FireworkType type) {
            this.type = type;
            return this;
        }
        
        public void setType(final int type) {
            if (type >= 0 && type <= 4) {
                this.type = FireworkType.values()[type];
            }
        }
        
        public void addColor(final int red, final int green, final int blue) {
            this.colors.add((red << 16) + (green << 8) + blue);
        }
        
        private NBTTagCompound getTags() {
            final NBTTagCompound explosionTag = new NBTTagCompound();
            explosionTag.setBoolean("Flicker", this.flicker);
            explosionTag.setBoolean("Trail", this.trail);
            explosionTag.setByte("Type", (byte)this.type.ordinal());
            final int[] intArray = new int[this.colors.size()];
            for (int i = 0; i < this.colors.size(); ++i) {
                intArray[i] = this.colors.get(i);
            }
            explosionTag.setIntArray("Colors", intArray);
            return explosionTag;
        }
        
        public ItemStack getStack() {
            final NBTTagCompound tags = new NBTTagCompound();
            final NBTTagCompound fireworksTag = new NBTTagCompound();
            final NBTTagList explosionsList = new NBTTagList();
            explosionsList.appendTag((NBTBase)this.getTags());
            fireworksTag.setByte("Flight", (byte)(-10));
            fireworksTag.setTag("Explosions", (NBTBase)explosionsList);
            tags.setTag("Fireworks", (NBTBase)fireworksTag);
            final ItemStack stack = new ItemStack(Items.fireworks);
            stack.setTagCompound(tags);
            return stack;
        }
    }
}

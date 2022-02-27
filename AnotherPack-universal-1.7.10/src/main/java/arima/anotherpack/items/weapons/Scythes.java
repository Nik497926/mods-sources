package arima.anotherpack.items.weapons;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import arima.anotherpack.core.AllItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class Scythes extends ItemSword implements ItemRenderInterface {

	final Random random = new Random();
	private int weaponDamage;
	private String ScytheName;

	public Scythes(ToolMaterial material, String texture, String name) {
		super(AllItems.ScytheMat);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setTextureName(texture);
		this.setMaxDamage(-1);
		this.setUnlocalizedName(name);
		this.ScytheName = name;
		this.setFull3D();
		this.weaponDamage = 50 + random.nextInt(60);
		GameRegistry.registerItem(this, name);
	}

	@Override
	public void renderSpecial() {
		GL11.glScalef(1.5F, 1.5F, 1.0F);
		GL11.glTranslatef(0.19F, 0.0F, 0.1F);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}

	public static boolean get_Crescent_Rose_State(ItemStack itemstack) {
		return itemstack.hasTagCompound() ? itemstack.getTagCompound().getBoolean("Crescent_Rose") : false;
	}

	public static void set_Crescent_Rose_State(ItemStack itemstack, boolean flag) {
		if (!itemstack.hasTagCompound()) {
			itemstack.setTagCompound(new NBTTagCompound());
		}

		itemstack.getTagCompound().setBoolean("Crescent_Rose", flag);
	}

	public int getDamageVsEntity(Entity par1Entity) {
		return this.weaponDamage;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		set_Crescent_Rose_State(stack, true);
	}
	
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase entity, EntityLivingBase player)
    {
    	int i = 1 + random.nextInt(10);
		int x = 1 + random.nextInt(3);
		if (i == 10 || i == 9) {
			player.worldObj.playSoundAtEntity(player, "arima:Coup_de_Grace", 0.8F, 1.0F);
			this.weaponDamage = 70 + (random.nextInt(30));
			entity.addPotionEffect(new PotionEffect(Potion.wither.id, 200));
		} else {
			this.weaponDamage = 45 + random.nextInt(15);
			player.worldObj.playSoundAtEntity(player, "arima:scythe_hit_" + x + "_", 0.2F, 1.0F);
		}
        return true;
    }

	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer player, int p_77615_4_) {
		int j = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;

		ArrowLooseEvent event = new ArrowLooseEvent(player, p_77615_1_, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return;
		}
		j = event.charge;
		boolean flag = player.capabilities.isCreativeMode;
		if (flag || player.inventory.hasItem(AllItems.bullet)) {
			p_77615_2_.playSoundAtEntity(player, "arima:rwby", 0.7F, 0.95F);
			Vec3 look;
			EntityArrow fireball;
			look = player.getLookVec();
			player.motionX = -look.xCoord / 1.5D;
			player.motionZ = -look.zCoord / 1.5D;
			player.motionY = -look.yCoord / 1.5D;
			player.motionX = -look.xCoord / 1.5D;
			player.motionZ = -look.zCoord / 1.5D;
			if (!p_77615_2_.isRemote) {
				fireball = new EntityArrow(p_77615_2_, player, 5.0F);
				fireball.canBePickedUp = 0;
				if (!player.capabilities.isCreativeMode) {
					player.inventory.consumeInventoryItem(AllItems.bullet);
				}

				p_77615_2_.spawnEntityInWorld(fireball);
				p_77615_1_.damageItem(1, player);
			}
			set_Crescent_Rose_State(p_77615_1_, false);

		} else {
			set_Crescent_Rose_State(p_77615_1_, false);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add("\u00a79\u0423\u0440\u043e\u043d 45-60");
		p_77624_3_.add(
				"\u00a79\u0417\u0430\u0436\u0430\u0442\u044c \u041f\u041a\u041c \u0434\u043b\u044f \u0441\u0442\u0440\u0435\u043b\u044c\u0431\u044b \u0438\u0437 \u043e\u0440\u0443\u0434\u0438\u044f(\u043d\u0443\u0436\u043d\u044b \u043f\u0443\u043b\u0438)");
		p_77624_3_.add(
				"\u00a79\u0414\u0430\u0451\u0442 \u044d\u0444\u0444\u0435\u043a\u0442\u044b \u0421\u043a\u043e\u0440\u043e\u0441\u0442\u0438 II \u0438 \u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u0438 III");
		p_77624_3_.add(
				"\u00a7c\u0428\u0430\u043d\u0441 \u043a\u0440\u0438\u0442\u0430 \u00a7420%\u00a7c, \u0443\u0440\u043e\u043d \u0432\u044b\u0440\u0430\u0441\u0442\u0435\u0442 \u0434\u043e 70-100");
		}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		super.onUpdate(stack, world, entity, par4, par5);
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack equipped = player.getCurrentEquippedItem();
			if (equipped == stack) {
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 2, 2, true));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, 1, true));
			}
		}

	}

}

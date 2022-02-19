package info.jbcs.minecraft.vending.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.TileEntityIronChest;
import info.jbcs.minecraft.vending.EconomyControl;
import info.jbcs.minecraft.vending.Vending;
import info.jbcs.minecraft.vending.renderer.BlockVendingMachineRenderer;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import ru.mryarik666.xmodpanel.utils.VaultUtils;

public class BlockVendingMachine
extends BlockContainer {
    Block[] supportBlocks;
    boolean isAdvanced;
    boolean isEco;
    IIcon IIconTop;
    IIcon IIconSide;

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        if (p_149734_1_.isRemote) {
            int minPriceBought;
            TileEntityVendingMachine machine = (TileEntityVendingMachine)p_149734_1_.getTileEntity(p_149734_2_, p_149734_3_, p_149734_4_);
            int minPriceSold = EconomyControl.getMinPrice(machine.getSoldItems());
            int n = minPriceBought = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_) == 8 ? machine.getMoney() : EconomyControl.getMinPrice(machine.getBoughtItems()[0]);
            if ((double)minPriceSold > (double)minPriceBought * Vending.priceMultiplier) {
                GL11.glPushMatrix();
                GL11.glColor3d((double)255.0, (double)0.0, (double)0.0);
                p_149734_1_.spawnParticle("reddust", (double)p_149734_2_ + 0.5, (double)(p_149734_3_ + 1), (double)p_149734_4_ + 0.5, 0.0, 1.0, 0.0);
                GL11.glPopMatrix();
            }
        }
    }

    public BlockVendingMachine(Block[] supports, boolean advanced, boolean eco) {
        super(Material.glass);
        this.setBlockName("vendingMachine");
        this.supportBlocks = supports;
        this.setStepSound(Block.soundTypeGlass);
        this.setCreativeTab(Vending.tabVending);
        this.setHardness(0.3f);
        this.setResistance(6000000.0f);
        this.setBlockUnbreakable();
        this.setStepSound(Block.soundTypeGlass);
        this.setBlockBounds(0.0625f, 0.125f, 0.0625f, 0.9375f, 0.9375f, 0.9375f);
        this.isAdvanced = advanced;
        this.isEco = eco;
    }

    void vend(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (world.isRemote) return;

        TileEntityVendingMachine tileEntity = (TileEntityVendingMachine)world.getTileEntity(i, j, k);
        if (tileEntity == null) {
            return;
        }
        TileEntity chestTile = world.getTileEntity(tileEntity.xCoord, tileEntity.yCoord - 1, tileEntity.zCoord);
        if (chestTile == null || !(chestTile instanceof TileEntityIronChest)) {
            return;
        }
        TileEntityIronChest chest = (TileEntityIronChest)chestTile;
        ItemStack soldItems = tileEntity.getSoldItems()[0];
        int minPriceSold = EconomyControl.getMinPrice(soldItems);
        if (tileEntity.eco) {
            if (tileEntity.mode == 0) {
                boolean fits = true;
                if (soldItems == null) {
                    fits = false;
                }
                int money = tileEntity.getMoney();
                if (fits && ((double)minPriceSold > (double)money * Vending.priceMultiplier || money == 0)) {
                    fits = false;
                    entityplayer.addChatComponentMessage(new ChatComponentText("Слишком низкая цена"));
                }
                if (fits) {
                    if (!world.isRemote) {
                        if (VaultUtils.hasMoney(entityplayer, money)) {
                            boolean canGive = this.givestack(world, i, j, k, entityplayer, tileEntity, soldItems);
                            if (canGive) {
                                VaultUtils.withdrawPlayer(entityplayer, money);
                                VaultUtils.depositPlayer(tileEntity.ownerName, money);
                                entityplayer.addChatComponentMessage(new ChatComponentText("Спасибо за покупку"));
                                world.playSoundEffect(i, j, k, "vending:cha-ching", 0.3f, 0.6f);
                            }
                            chest.markDirty();
                            tileEntity.updateCount();
                        } else {
                            entityplayer.addChatComponentMessage(new ChatComponentText("Недостаточно средств"));
                        }
                    }
                }
                return;
            }
            boolean fits = true;
            ItemStack bought = soldItems;
            int minPriceBought = EconomyControl.getMinPrice(bought);
            ItemStack offered = entityplayer.inventory.getCurrentItem();
            if (bought == null) {
                return;
            }
            if (!tileEntity.doesStackFit(bought)) {
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u041d\u0435\u0442 \u043c\u0435\u0441\u0442\u0430 \u0432 \u0441\u0443\u043d\u0434\u0443\u043a\u0435"));
                return;
            }
            if (offered == null) {
                fits = false;
            } else if (bought.getItem() != offered.getItem()) {
                fits = false;
            } else if (bought.hasTagCompound() || offered.hasTagCompound()) {
                if (bought.hasTagCompound() && offered.hasTagCompound()) {
                    if (!bought.getTagCompound().equals((Object)offered.getTagCompound())) {
                        fits = false;
                    }
                } else {
                    fits = false;
                }
            } else if (bought.getItemDamage() != offered.getItemDamage()) {
                fits = false;
            } else if (offered.stackSize < bought.stackSize) {
                fits = false;
            }
            if (!fits && !world.isRemote) {
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0427\u0442\u043e\u0431\u044b \u043f\u0440\u043e\u0434\u0430\u0442\u044c \u044d\u0442\u043e\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442, \u0432\u043e\u0437\u044c\u043c\u0438\u0442\u0435 \u0435\u0433\u043e \u0432 \u0440\u0443\u043a\u0443!"));
                return;
            }
            int money = tileEntity.getMoney();
            if (fits && (double)money > (double)minPriceBought * Vending.priceMultiplier) {
                fits = false;
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u043d\u0438\u0437\u043a\u0430\u044f \u0446\u0435\u043d\u0430"));
            }
            if (!world.isRemote) {
                if (fits) {
                    world.playSoundEffect((double)i, (double)j, (double)k, "vending:cha-ching", 0.3f, 0.6f);
                    if (VaultUtils.hasMoney(tileEntity.ownerName, (double)money)) {
                        ItemStack paid = offered.splitStack(bought.stackSize);
                        if (offered.stackSize == 0) {
                            entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = null;
                        }
                        if (!tileEntity.infinite) {
                            BlockVendingMachine.putInInventory((IInventory)chest, paid, false);
                        }
                        VaultUtils.withdrawPlayer(tileEntity.ownerName, (double)money);
                        VaultUtils.depositPlayer(entityplayer, (double)money);
                        entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0421\u043f\u0430\u0441\u0438\u0431\u043e \u0437\u0430 \u043f\u043e\u043a\u0443\u043f\u043a\u0443"));
                    } else {
                        entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0423 " + tileEntity.ownerName + " \u0437\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u0438\u0441\u044c \u0434\u0435\u043d\u044c\u0433\u0438"));
                    }
                } else {
                    world.playSoundEffect((double)i, (double)j, (double)k, "vending:forbidden", 1.0f, 1.0f);
                }
                chest.markDirty();
                tileEntity.updateCount();
            }
            return;
        }
        ItemStack bought = tileEntity.getBoughtItems()[0];
        int minPriceBought = EconomyControl.getMinPrice(bought);
        ItemStack offered = entityplayer.inventory.getCurrentItem();
        boolean fits = true;
        if (bought == null || soldItems == null) {
            fits = false;
        } else {
            tileEntity.inventory.onInventoryChanged();
            if (offered == null) {
                fits = false;
            } else if (bought.getItem() != offered.getItem()) {
                fits = false;
            } else if (bought.hasTagCompound() || offered.hasTagCompound()) {
                if (bought.hasTagCompound() && offered.hasTagCompound()) {
                    if (!bought.getTagCompound().equals((Object)offered.getTagCompound())) {
                        fits = false;
                    }
                } else {
                    fits = false;
                }
            } else if (bought.getItemDamage() != offered.getItemDamage()) {
                fits = false;
            } else if (offered.stackSize < bought.stackSize) {
                fits = false;
            }
            if (!fits && !world.isRemote) {
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0427\u0442\u043e\u0431\u044b \u043a\u0443\u043f\u0438\u0442\u044c \u044d\u0442\u043e\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442, \u0432\u043e\u0437\u044c\u043c\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0432 \u0440\u0443\u043a\u0443!"));
            }
            if (!tileEntity.doesStackFit(bought)) {
                fits = false;
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u041d\u0435\u0442 \u043c\u0435\u0441\u0442\u0430 \u0432 \u0441\u0443\u043d\u0434\u0443\u043a\u0435"));
            }
        }
        if (fits && (double)minPriceSold > (double)minPriceBought * Vending.priceMultiplier) {
            fits = false;
            entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u043d\u0438\u0437\u043a\u0430\u044f \u0446\u0435\u043d\u0430"));
        }
        if (!world.isRemote) {
            if (fits) {
                boolean canGive = this.givestack(world, i, j, k, entityplayer, tileEntity, soldItems);
                world.playSoundEffect((double)i, (double)j, (double)k, "vending:cha-ching", 0.3f, 0.6f);
                if (canGive && offered != null) {
                    ItemStack paid = offered.splitStack(bought.stackSize);
                    if (offered.stackSize == 0) {
                        entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = null;
                    }
                    if (!tileEntity.infinite) {
                        BlockVendingMachine.putInInventory((IInventory)chest, paid, false);
                    }
                    entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0421\u043f\u0430\u0441\u0438\u0431\u043e \u0437\u0430 \u043f\u043e\u043a\u0443\u043f\u043a\u0443"));
                }
                if (!tileEntity.infinite) {
                    tileEntity.inventory.onInventoryChanged();
                }
            } else {
                world.playSoundEffect((double)i, (double)j, (double)k, "vending:forbidden", 1.0f, 1.0f);
            }
            chest.markDirty();
            tileEntity.updateCount();
        }
    }

    public static int putInInventory(IInventory inv, ItemStack itemStackSource, boolean simulate) {
        int transfer;
        ItemStack itemStack;
        int i;
        if (itemStackSource == null) {
            return 0;
        }
        int toTransfer = itemStackSource.stackSize;
        for (i = 0; i < inv.getSizeInventory() && toTransfer > 0; ++i) {
            if (!inv.isItemValidForSlot(i, itemStackSource) || inv instanceof ISidedInventory && !((ISidedInventory)inv).canInsertItem(i, itemStackSource, 0) || (itemStack = inv.getStackInSlot(i)) == null || !BlockVendingMachine.equalsStack(itemStack, itemStackSource)) continue;
            transfer = Math.min(toTransfer, Math.min(inv.getInventoryStackLimit(), itemStack.getMaxStackSize()) - itemStack.stackSize);
            if (!simulate) {
                itemStack.stackSize += transfer;
            }
            toTransfer -= transfer;
        }
        for (i = 0; i < inv.getSizeInventory() && toTransfer > 0; ++i) {
            if (!inv.isItemValidForSlot(i, itemStackSource) || inv instanceof ISidedInventory && !((ISidedInventory)inv).canInsertItem(i, itemStackSource, 0) || (itemStack = inv.getStackInSlot(i)) != null) continue;
            transfer = Math.min(toTransfer, Math.min(inv.getInventoryStackLimit(), itemStackSource.getMaxStackSize()));
            if (!simulate) {
                ItemStack dest = BlockVendingMachine.copyWithSize(itemStackSource, transfer);
                inv.setInventorySlotContents(i, dest);
            }
            toTransfer -= transfer;
        }
        if (!simulate && toTransfer != itemStackSource.stackSize) {
            inv.markDirty();
        }
        return itemStackSource.stackSize - toTransfer;
    }

    public static ItemStack copyWithSize(ItemStack itemStack, int newSize) {
        ItemStack ret = itemStack.copy();
        ret.stackSize = newSize;
        return ret;
    }

    private static boolean equalsStack(ItemStack itemStack, ItemStack itemStackSource) {
        if (itemStack.getItem() == itemStackSource.getItem() && itemStack.getItemDamage() == itemStackSource.getItemDamage()) {
            return itemStack.hasTagCompound() && itemStackSource.hasTagCompound() ? itemStack.getTagCompound().equals((Object)itemStackSource.getTagCompound()) : true;
        }
        return false;
    }

    private boolean givestack(World world, int i, int j, int k, EntityPlayer entityplayer, TileEntityVendingMachine tileEntity, ItemStack soldItems) {
        if (soldItems != null) {
            boolean res = true;
            if (!tileEntity.infinite) {
                res = tileEntity.inventory.takeItemsFromChest(soldItems, soldItems.getItemDamage(), soldItems.stackSize);
            }
            if (res) {
                NBTTagCompound tag = new NBTTagCompound();
                soldItems.writeToNBT(tag);
                ItemStack vended = ItemStack.loadItemStackFromNBT((NBTTagCompound)tag);
                if (!entityplayer.inventory.addItemStackToInventory(vended)) {
                    entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0412\u0430\u0448 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d!"));
                    TileEntity chestTile = world.getTileEntity(tileEntity.xCoord, tileEntity.yCoord - 1, tileEntity.zCoord);
                    TileEntityIronChest chest = (TileEntityIronChest)chestTile;
                    BlockVendingMachine.putInInventory((IInventory)chest, soldItems, false);
                    return false;
                }
            } else {
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0423\u043f\u0441\u0441\u0441, \u0435\u0442 \u0432 \u043d\u0430\u043b\u0438\u0447\u0438\u0438"));
            }
            return res;
        }
        return false;
    }

    public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer) {
        TileEntityVendingMachine tileEntity = (TileEntityVendingMachine)world.getTileEntity(i, j, k);
        if (tileEntity == null) {
            return;
        }
        if (!entityplayer.getDisplayName().equals(tileEntity.ownerName) || !tileEntity.inventory.isEmpty() || this.findAroundChest(world, i, j, k)) {
            this.vend(world, i, j, k, entityplayer);
            return;
        }
        this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
        world.setBlock(i, j, k, Blocks.air);
        world.playSoundEffect((double)i, (double)j, (double)k, "vending:cha-ching", 0.3f, 0.6f);
    }

    private boolean findAroundChest(World world, int x, int y, int z) {
        if (world.getBlock(x, y - 1, z) instanceof BlockIronChest) {
            ItemStack[] contents;
            TileEntityIronChest tile = (TileEntityIronChest)world.getTileEntity(x, y - 1, z);
            for (ItemStack content : contents = tile.getContents()) {
                if (content == null) continue;
                return true;
            }
        }
        return false;
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int a, float b, float x, float y) {
        TileEntityVendingMachine tileEntity = (TileEntityVendingMachine)world.getTileEntity(i, j, k);
        if (tileEntity == null) {
            return false;
        }
        if (entityplayer.inventory.getCurrentItem() != null && entityplayer.inventory.getCurrentItem().getItem() == Vending.itemWrench) {
            Vending.guiWrench.open(entityplayer, world, i, j, k);
            return true;
        }
        if (entityplayer.getDisplayName().equals(tileEntity.ownerName) && !entityplayer.isSneaking()) {
            Vending.guiVending.open(entityplayer, world, i, j, k);
            return true;
        }
        if (entityplayer.capabilities.isCreativeMode && !entityplayer.isSneaking()) {
            Vending.guiVending.open(entityplayer, world, i, j, k);
            return true;
        }
        if (world.isRemote) {
            entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("\u0427\u0442\u043e\u0431\u044b \u043a\u0443\u043f\u0438\u0442\u044c \u044d\u0442\u043e\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442, \u043d\u0430\u0436\u043c\u0438\u0442\u0435 \u041b\u041a\u041c \u0434\u0435\u0440\u0436\u0430 \u043f\u0440\u043e\u0434\u0430\u0432\u0430\u0435\u043c\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0432 \u0440\u0443\u043a\u0435!"));
        }
        return true;
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
        TileEntityVendingMachine e = new TileEntityVendingMachine();
        e.advanced = this.isAdvanced;
        e.eco = this.isEco;
        if (entityliving != null) {
            EntityPlayer player = (EntityPlayer)entityliving;
            e.ownerName = player.getDisplayName();
            world.setTileEntity(i, j, k, (TileEntity)e);
        }
    }

    public int getRenderBlockPass() {
        return 0;
    }

    public IIcon getIcon(int side, int meta) {
        return side < 2 ? this.IIconTop : this.IIconSide;
    }

    public TileEntity createNewTileEntity(World var1, int metadata) {
        TileEntityVendingMachine e = new TileEntityVendingMachine();
        e.advanced = this.isAdvanced;
        e.eco = this.isEco;
        return e;
    }

    public void breakBlock(World world, int i, int j, int k, Block a, int b) {
        TileEntityVendingMachine tileentitychest = (TileEntityVendingMachine)world.getTileEntity(i, j, k);
        if (tileentitychest == null) {
            return;
        }
        for (int l = 0; l < tileentitychest.getSizeInventory(); ++l) {
            ItemStack itemstack;
            if (tileentitychest.advanced && l == 10 || (itemstack = tileentitychest.getStackInSlot(l)) == null) continue;
            float f = world.rand.nextFloat() * 0.8f + 0.1f;
            float f1 = world.rand.nextFloat() * 0.8f + 0.1f;
            float f2 = world.rand.nextFloat() * 0.8f + 0.1f;
            while (itemstack.stackSize > 0) {
                int i1 = world.rand.nextInt(21) + 10;
                if (i1 > itemstack.stackSize) {
                    i1 = itemstack.stackSize;
                }
                itemstack.stackSize -= i1;
                EntityItem entityitem = new EntityItem(world, (double)((float)i + f), (double)((float)j + f1), (double)((float)k + f2), new ItemStack(itemstack.getItem(), i1, itemstack.getItemDamage()));
                float f3 = 0.05f;
                entityitem.motionX = (float)world.rand.nextGaussian() * f3;
                entityitem.motionY = (float)world.rand.nextGaussian() * f3 + 0.2f;
                entityitem.motionZ = (float)world.rand.nextGaussian() * f3;
                world.spawnEntityInWorld((Entity)entityitem);
            }
        }
        super.breakBlock(world, i, j, k, a, b);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int damageDropped(int i) {
        return i;
    }

    public void registerBlockIcons(IIconRegister register) {
        this.IIconTop = register.registerIcon("Vending:vendor-top");
        this.IIconSide = register.registerIcon("Vending:vendor-side");
    }

    public int getRenderType() {
        return BlockVendingMachineRenderer.id;
    }

    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < this.supportBlocks.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}


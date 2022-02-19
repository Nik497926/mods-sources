package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemFocusPouch;

import java.util.UUID;

public class ContainerFocusPouch extends Container
{
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	private int blockSlot;
	public IInventory input = new InventoryFocusPouch(this);
	ItemStack pouch;
	EntityPlayer player;

	// TODO gamerforEA code start
	private static final String NBT_KEY_UID = "UID";
	private int blockedSlot;

	@Override
	public void onCraftMatrixChanged(IInventory inventory)
	{
		((ItemFocusPouch) this.pouch.getItem()).setInventory(this.pouch, ((InventoryFocusPouch) this.input).stackList);
		if (this.player != null && this.canInteractWith(this.player) && this.player.getHeldItem() != null && this.player.getHeldItem().isItemEqual(this.pouch))
			this.player.setCurrentItemOrArmor(0, this.pouch);
		super.onCraftMatrixChanged(inventory);
	}

	private static boolean isSameItemInventory(ItemStack base, ItemStack comparison)
	{
		if (base == null || comparison == null)
			return false;

		if (base.getItem() != comparison.getItem())
			return false;

		if (!base.hasTagCompound() || !comparison.hasTagCompound())
			return false;

		String baseUID = base.getTagCompound().getString(NBT_KEY_UID);
		String comparisonUID = comparison.getTagCompound().getString(NBT_KEY_UID);
		return baseUID != null && comparisonUID != null && baseUID.equals(comparisonUID);
	}
	// TODO gamerforEA code end

	public ContainerFocusPouch(InventoryPlayer iinventory, World world, int x, int y, int z)
	{
		this.worldObj = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.player = iinventory.player;
		this.pouch = iinventory.getCurrentItem();
		this.blockSlot = iinventory.currentItem + 45;

		// TODO gamerforEA code start
		if (this.pouch != null && this.pouch.getItem() instanceof ItemFocusPouch)
		{
			if (!this.pouch.hasTagCompound())
				this.pouch.setTagCompound(new NBTTagCompound());
			NBTTagCompound nbt = this.pouch.getTagCompound();
			if (!nbt.hasKey(NBT_KEY_UID))
				nbt.setString(NBT_KEY_UID, UUID.randomUUID().toString());
		}
		// TODO gamerforEA code end

		for (int a = 0; a < 18; ++a)
		{
			this.addSlotToContainer(new SlotLimitedByClass(ItemFocusBasic.class, this.input, a, 37 + a % 6 * 18, 51 + a / 6 * 18));
		}

		this.bindPlayerInventory(iinventory);
		if (!world.isRemote)
			try
			{
				((InventoryFocusPouch) this.input).stackList = ((ItemFocusPouch) this.pouch.getItem()).getInventory(this.pouch);
			}
			catch (Exception ignore)
			{
			}

		this.onCraftMatrixChanged(this.input);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
			}
		}

		// TODO gamerforEA code start
		int currentSlot = inventoryPlayer.currentItem;
		// TODO gamerforEA code end

		for (int i = 0; i < 9; ++i)
		{
			Slot slot = this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 209));

			// TODO gamerforEA code start
			if (slot.getSlotIndex() == currentSlot)
				this.blockedSlot = slot.slotNumber;
			// TODO gamerforEA code end
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		if (slot == this.blockSlot)
			return null;
		else
		{
			// TODO gamerforEA code start
			if (slot == this.blockedSlot)
				return null;
			if (!this.canInteractWith(player))
				return null;
			// TODO gamerforEA code end

			ItemStack stack = null;
			Slot slotObject = (Slot) this.inventorySlots.get(slot);
			if (slotObject != null && slotObject.getHasStack())
			{
				ItemStack stackInSlot = slotObject.getStack();
				stack = stackInSlot.copy();
				if (slot < 18)
				{
					if (!this.input.isItemValidForSlot(slot, stackInSlot) || !this.mergeItemStack(stackInSlot, 18, this.inventorySlots.size(), true))
						return null;
				}
				else if (!this.input.isItemValidForSlot(slot, stackInSlot) || !this.mergeItemStack(stackInSlot, 0, 18, false))
					return null;

				if (stackInSlot.stackSize == 0)
					slotObject.putStack(null);
				else
					slotObject.onSlotChanged();
			}

			return stack;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		// TODO gamerforEA code replace, old code: return true;
		return isSameItemInventory(this.pouch, player.getCurrentEquippedItem());
		// TODO gamerforEA code end
	}

	@Override
	public ItemStack slotClick(int slot, int button, int buttonType, EntityPlayer player)
	{
		if (slot == this.blockSlot)
			return null;

		// TODO gamerforEA code start
		if (slot == this.blockedSlot)
			return null;
		if (buttonType == 2 && button == this.blockedSlot)
			return null;
		if (!this.canInteractWith(player))
			return null;
		// TODO gamerforEA code end

		return super.slotClick(slot, button, buttonType, player);
	}

	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
		if (!this.worldObj.isRemote)
		{
			((ItemFocusPouch) this.pouch.getItem()).setInventory(this.pouch, ((InventoryFocusPouch) this.input).stackList);

			// TODO gamerforEA add condition [2]
			if (this.player == null || !this.canInteractWith(this.player))
				return;

			if (this.player.getHeldItem() != null && this.player.getHeldItem().isItemEqual(this.pouch))
				this.player.setCurrentItemOrArmor(0, this.pouch);

			this.player.inventory.markDirty();
		}
	}
}

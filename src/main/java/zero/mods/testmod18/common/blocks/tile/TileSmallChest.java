package zero.mods.testmod18.common.blocks.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.client.gui.GuiSmallChest;
import zero.mods.testmod18.common.blocks.ContainerSmallChest;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;
import zero.mods.zerocore.common.helpers.CodeHelper;
import zero.mods.zerocore.common.helpers.InventoryHelper;
import zero.mods.zerocore.common.helpers.ModObjects;
import zero.mods.zerocore.common.helpers.StacksHelper;

import java.util.Arrays;


public class TileSmallChest extends ModTileEntity implements IInventory {

    public static final int STORAGE_SLOT_COUNT = 3;
    public static final int FILTER_SLOT_FIRST_INDEX = 0;
    public static final int STORAGE_SLOT_FIRST_INDEX = 3;



    public TileSmallChest() {

        this._slots = new ItemStack[TileSmallChest.STORAGE_SLOT_COUNT * 2];
    }


    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    /**
     * Check if the tile entity has a GUI or not
     * Override in derived classes to return true if your tile entity got a GUI
     */
    public boolean canOpenGui() {

        return true;
    }

    @Override
    public Object getServerGuiElement(InventoryPlayer playerInventory) {

        return new ContainerSmallChest(playerInventory, this);
    }

    @Override
    public Object getClientGuiElement(InventoryPlayer playerInventory) {

        return new GuiSmallChest(playerInventory, this);
    }

    ///// IInventory /////////////////


    @Override
    public int getSizeInventory() {

        return this._slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return this.isValidSlotIndex(index) ? this._slots[index] : null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (this.isValidSlotIndex(index)) {

            this._slots[index] = stack;

            if (null != stack) {

                int limit = this.getInventoryStackLimit();

                if (stack.stackSize > limit)
                    stack.stackSize = limit;
            }

            this.markDirty();
        }
    }

    /**
     * Removes some of the units from itemstack in the given slot, and returns as a separate itemstack
     * @param index the slot number to remove the items from
     * @param count the number of units to remove
     * @return a new itemstack containing the units removed from the slot
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {

        return InventoryHelper.decreaseStackSize(this, index, count);
    }

    /**
     * This method removes the entire contents of the given slot and returns it.
     * Used by containers such as crafting tables which return any items in their slots when you close the GUI
     * @param index
     * @return
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int index) {

        ItemStack itemStack = this.getStackInSlot(index);

        if (itemStack != null)
            this.setInventorySlotContents(index, null);

        return itemStack;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    // Return true if the given player is able to use this block. In this case it checks that
    // 1) the world tileentity hasn't been replaced in the meantime, and
    // 2) the player isn't too far away from the centre of the block
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        if (this.worldObj.getTileEntity(this.pos) != this)
            return false;

        return CodeHelper.isEntityInRange(player, this.pos, 8.0);
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return true; // FIX implement filters
    }

    @Override
    public int getField(int id) {

        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {

        return 0;
    }

    @Override
    public void clear() {

        Arrays.fill(this._slots, null);
    }

    @Override
    public String getName() {

        return ModObjects.formatFullyQualifiedName(this.getMod().getModId(), "tilesmallchest.name");
    }

    @Override
    public boolean hasCustomName() {

        return false;
    }

    @Override
    public IChatComponent getDisplayName() {

        return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
    }

    ///// /IInventory /////////////////

    // This is where you save any data that you don't want to lose when the tile entity unloads
    // In this case, it saves the itemstacks stored in the container
    @Override
    public void writeToNBT(NBTTagCompound parentNBTTagCompound) {

        super.writeToNBT(parentNBTTagCompound); // The super call is required to save and load the tileEntity's location

        // to use an analogy with Java, this code generates an array of hashmaps
        // The itemStack in each slot is converted to an NBTTagCompound, which is effectively a hashmap of key->value pairs such
        //   as slot=1, id=2353, count=1, etc
        // Each of these NBTTagCompound are then inserted into NBTTagList, which is similar to an array.

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this._slots.length; ++i) {

            if (this._slots[i] != null)	{

                NBTTagCompound nbt = new NBTTagCompound();

                nbt.setByte("Index", (byte)i);
                this._slots[i].writeToNBT(nbt);
                list.appendTag(nbt);
            }
        }
        // the array of hashmaps is then inserted into the parent hashmap for the container
        parentNBTTagCompound.setTag("Items", list);
    }

    // This is where you load the data that you saved in writeToNBT
    @Override
    public void readFromNBT(NBTTagCompound parentNBTTagCompound) {

        super.readFromNBT(parentNBTTagCompound); // The super call is required to save and load the tiles location

        final byte NBT_TYPE_COMPOUND = 10;       // See NBTBase.createNewByType() for a listing
        NBTTagList list = parentNBTTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

        Arrays.fill(this._slots, null);           // set all slots to empty

        for (int i = 0; i < list.tagCount(); ++i) {

            NBTTagCompound nbt = list.getCompoundTagAt(i);
            int index = nbt.getByte("Index");// & 255;

            if (this.isValidSlotIndex(index))
                this._slots[index] = ItemStack.loadItemStackFromNBT(nbt);
        }
    }


    protected boolean isValidSlotIndex(int slotIndex) {

        return this.isValidFilterSlotIndex(slotIndex) || this.isValidStorageSlotIndex(slotIndex);
    }

    protected boolean isValidFilterSlotIndex(int slotIndex) {

        return (slotIndex >= FILTER_SLOT_FIRST_INDEX) && (slotIndex < FILTER_SLOT_FIRST_INDEX + STORAGE_SLOT_COUNT);
    }
    protected boolean isValidStorageSlotIndex(int slotIndex) {

        return (slotIndex >= STORAGE_SLOT_FIRST_INDEX) && (slotIndex < STORAGE_SLOT_FIRST_INDEX + STORAGE_SLOT_COUNT);
    }


    ItemStack[] _slots;
}

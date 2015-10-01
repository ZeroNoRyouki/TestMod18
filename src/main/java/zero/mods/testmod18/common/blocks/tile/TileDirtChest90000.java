package zero.mods.testmod18.common.blocks.tile;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.client.gui.GuiDirtChest90000;
import zero.mods.testmod18.common.blocks.ContainerDirtChest90000;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntityContainer;

public class TileDirtChest90000 extends ModTileEntityContainer {

    public TileDirtChest90000() {

        super(1, 64);
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    @Override
    public String getName() {

        return "te.dirtchest90000";
    }

    /**
     * Check if the tile entity has a GUI or not
     * Override in derived classes to return true if your tile entity got a GUI
     */
    public boolean canOpenGui() {

        return true;
    }

    /**
     * Returns a Server side Container to be displayed to the user.
     *
     * @param playerInventory The inventory of the player
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    public Object getServerGuiElement(InventoryPlayer playerInventory) {

        return new ContainerDirtChest90000(playerInventory, this);
    }

    /**
     * Returns a Container to be displayed to the user. On the client side, this
     * needs to return a instance of GuiScreen On the server side, this needs to
     * return a instance of Container
     *
     * @param playerInventory The inventory of the player
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    public Object getClientGuiElement(InventoryPlayer playerInventory) {

        return new GuiDirtChest90000(playerInventory, this);
    }


    @Override
    public void writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);

        ItemStack stack = this.getStackInSlot(0);

        if (null != stack) {

            NBTTagCompound itemTag = new NBTTagCompound();

            stack.writeToNBT(itemTag);
            compound.setTag("item", itemTag);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        ItemStack stack = null;
        NBTBase tag;

        super.readFromNBT(compound);

        if (compound.hasKey("item") && ((tag = compound.getTag("item")) instanceof NBTTagCompound))
            stack = ItemStack.loadItemStackFromNBT((NBTTagCompound)tag);

        if (null != stack)
            this.setInventorySlotContents(0, stack);
    }


}

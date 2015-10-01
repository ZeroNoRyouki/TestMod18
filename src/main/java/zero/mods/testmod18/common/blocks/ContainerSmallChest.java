package zero.mods.testmod18.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import zero.mods.testmod18.common.blocks.tile.TileSmallChest;
import zero.mods.zerocore.common.inventory.GhostSlot;
import zero.mods.zerocore.common.inventory.ModContainer;


public class ContainerSmallChest extends ModContainer {




    public ContainerSmallChest(InventoryPlayer playerInventory, TileSmallChest tileEntity) {

        this._tileEntity = tileEntity;
        this.addVanillaInventorySlots(playerInventory, 7, 83, 141);


/*
        int slotIndex = 0, x, y, row, column;

        final int PLAYER_INVENTORY_STARTING_X = 7;
        final int PLAYER_INVENTORY_STARTING_Y = 83;
        final int PLAYER_INVENTORY_HOTBAR_STARTING_Y = 141;
        final int SLOT_SIDE = 18;

        //////////// add hot bar slots

        int hotbarSize = InventoryPlayer.getHotbarSize();

        for (x = PLAYER_INVENTORY_STARTING_X; slotIndex < hotbarSize; ++slotIndex, x += SLOT_SIDE)
            this.addSlotToContainer(new Slot(playerInventory, slotIndex, x, PLAYER_INVENTORY_HOTBAR_STARTING_Y));

        ////////////// add the other player  inventory slots

        for (row = 0, y = PLAYER_INVENTORY_STARTING_Y; row < PLAYER_INVENTORY_ROWS_COUNT; ++row, y += SLOT_SIDE)
            for (column = 0, x = PLAYER_INVENTORY_STARTING_X; column < PLAYER_INVENTORY_COLUMNS_COUNT; ++column, ++slotIndex, x += SLOT_SIDE)
                this.addSlotToContainer(new Slot(playerInventory, slotIndex, x, y));
*/

        ////////////// add TE slots

        final int FILTER_Y = 14;
        final int STORAGE_Y = 46;
        final int STARTING_X = 25;
        final int SLOT_OFFSET = 54;

        int x, column;

        for (column = 0, x = STARTING_X; column < TileSmallChest.STORAGE_SLOT_COUNT; ++column, x += SLOT_OFFSET) {

            // add filter slot
            this.addSlotToContainer(new GhostSlot(this._tileEntity, TileSmallChest.FILTER_SLOT_FIRST_INDEX + column, x, FILTER_Y));

            // add storage slot
            this.addSlotToContainer(new Slot(this._tileEntity, TileSmallChest.STORAGE_SLOT_FIRST_INDEX + column, x, STORAGE_Y));
        }
    }

    ////////// Container overrides

/*
    @Override
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {

        FMLLog.info("SLOTCK %d %d %d", slotId, clickedButton, mode);

        return super.slotClick(slotId, clickedButton, mode, playerIn);
    }*/

    // Vanilla calls this method every tick to make sure the player is still able to access the inventory, and if not closes the gui
    @Override
    public boolean canInteractWith(EntityPlayer player) {

        return this._tileEntity.isUseableByPlayer(player);
    }

    // pass the close container message to the tileEntityInventory (not strictly needed for this example)
    //  see ContainerChest and TileEntityChest // GENERAL ?
    @Override
    public void onContainerClosed(EntityPlayer player) {

        super.onContainerClosed(player);
        this._tileEntity.closeInventory(player);
    }

    ////////// Container overrides/




    private TileSmallChest _tileEntity;
}

package zero.mods.testmod18.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.common.blocks.tile.TileEnchanter;
import zero.mods.zerocore.common.inventory.ModContainer;

public class ContainerEnchanter extends ModContainer {

    public ContainerEnchanter(InventoryPlayer inventoryPlayer, TileEnchanter tile) {

        this._tileEntity = tile;
        this._tileEnchantingCache = this._tileProcessingTimeCache = this._tileEnchantTypeOrdinalCache = -1;


        this.addVanillaInventorySlots(inventoryPlayer, 8, 84, 142);

        this.addSlotToContainer(new Slot(tile, TileEnchanter.SLOT_INDEX_ITEM, 56, 17));
        this.addSlotToContainer(new Slot(tile, TileEnchanter.SLOT_INDEX_ESSENCE, 56, 53));
        this.addSlotToContainer(new Slot(tile, TileEnchanter.SLOT_INDEX_OUTPUT, 116, 35));

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {

        return this._tileEntity.isUseableByPlayer(player);
    }

    /**
     * Add the given Listener to the list of Listeners. Method name is for legacy.
     */
    public void addCraftingToCrafters(ICrafting listener) {

        super.addCraftingToCrafters(listener);
        listener.func_175173_a(this, this._tileEntity);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {

        super.detectAndSendChanges();

        int currentEnchanting = this._tileEntity.getField(0);
        int currentProcessingTime = this._tileEntity.getField(1);
        int currentEnchantTypeOrdinal = this._tileEntity.getEnchantType().ordinal();

        for (int i = 0; i < this.crafters.size(); ++i) {

            ICrafting listener = (ICrafting)this.crafters.get(i);


            if (this._tileEnchantingCache != currentEnchanting)
                listener.sendProgressBarUpdate(this, 0, currentEnchanting);

            if (this._tileProcessingTimeCache != currentProcessingTime)
                listener.sendProgressBarUpdate(this, 1, currentProcessingTime);

            if (this._tileEnchantTypeOrdinalCache != currentEnchantTypeOrdinal)
                listener.sendProgressBarUpdate(this, 2, currentEnchantTypeOrdinal);
        }

        this._tileEnchantingCache = currentEnchanting;
        this._tileProcessingTimeCache = currentProcessingTime;
        this._tileEnchantTypeOrdinalCache = currentEnchantTypeOrdinal;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {

        this._tileEntity.setField(id, data);
    }


    private TileEnchanter _tileEntity;

    private int _tileEnchantingCache;
    private int _tileProcessingTimeCache;
    private int _tileEnchantTypeOrdinalCache;
}

package zero.mods.testmod18.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import zero.mods.testmod18.common.blocks.tile.TileDirtChest90000;
import zero.mods.zerocore.common.inventory.ModContainer;

public class ContainerDirtChest90000 extends ModContainer {


    public ContainerDirtChest90000(InventoryPlayer playerInventory, TileDirtChest90000 tileEntity) {

        this._tileEntity = tileEntity;
        this.addVanillaInventorySlots(playerInventory, 7, 83, 141);

        this.addSlotToContainer(new Slot(this._tileEntity, 0, 79, 38));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {

        return this._tileEntity.isUseableByPlayer(player);
    }


    private TileDirtChest90000 _tileEntity;
}

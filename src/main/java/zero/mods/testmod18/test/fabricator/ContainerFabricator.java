package zero.mods.testmod18.test.fabricator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.common.inventory.GhostSlot;
import zero.mods.zerocore.common.inventory.ModContainer;
import zero.mods.zerocore.common.inventory.PersistentInventoryCrafting;
import zero.mods.zerocore.common.inventory.PersistentInventoryCraftingResult;

public class ContainerFabricator extends ModContainer {

    public ContainerFabricator(InventoryPlayer playerInventory, TileFabricator tileEntity) {

        this._playerInventory = playerInventory;
        this._tileEntity = tileEntity;
        this._craftingInv = new PersistentInventoryCrafting(this, 3, 3);
        this._craftingResultInv = new PersistentInventoryCraftingResult();

        this._craftingInv.connectToInventory(this._tileEntity, 0, 9);
        this._craftingResultInv.connectToInventory(this._tileEntity, 9, 1);


        this.addVanillaInventorySlots(playerInventory, 7, 83, 141);


        int x, y, column, row, slotIndex;

        for (row = 1, y = 20, slotIndex = 0; row <= 3; ++row, y += ModContainer.VANILLA_GUI_SLOT_SIZE)
            for (column = 1, x = 7; column <= 3; ++column, x += ModContainer.VANILLA_GUI_SLOT_SIZE, ++slotIndex)
                this.addSlotToContainer(new GhostSlot(this._craftingInv, slotIndex, x, y));

        this.addSlotToContainer(new GhostSlot(this._craftingResultInv, 0, 79, 38));

        // add i/o slots...
        // 115,20
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {

        ItemStack result = CraftingManager.getInstance().findMatchingRecipe(this._craftingInv, this._tileEntity.getWorld());

        this._craftingResultInv.setInventorySlotContents(0, result);

        //if (result != null)
        FMLLog.info("Crafting result changed: %s", null != result ? result.toString() : "null");

        super.onCraftMatrixChanged(inventoryIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {

        return this._tileEntity.isUseableByPlayer(player);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {

        super.onContainerClosed(player);
        this._tileEntity.closeInventory(player);
    }

    private final InventoryPlayer _playerInventory;
    private final TileFabricator _tileEntity;
    private final PersistentInventoryCrafting _craftingInv;
    private final PersistentInventoryCraftingResult _craftingResultInv;
}

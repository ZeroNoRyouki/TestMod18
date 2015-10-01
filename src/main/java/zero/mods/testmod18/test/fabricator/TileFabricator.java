package zero.mods.testmod18.test.fabricator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntityContainer;

public class TileFabricator extends ModTileEntityContainer implements IInventory {

    public TileFabricator() {

        super(9 + 1 + 9, 64);

    }


    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    @Override
    public boolean canOpenGui() {

        return true;
    }

    @Override
    public Object getServerGuiElement(InventoryPlayer playerInventory) {

        return new ContainerFabricator(playerInventory, this);
    }

    @Override
    public Object getClientGuiElement(InventoryPlayer playerInventory) {

        return new GuiFabricator(playerInventory, this);
    }

    // IInventory start

    @Override
    public String getName() {

        return "tile.fabricator";
    }

    // IInventory end

    public void onCraftMatrixChanged(InventoryCrafting inventoryIn) {


    }


}

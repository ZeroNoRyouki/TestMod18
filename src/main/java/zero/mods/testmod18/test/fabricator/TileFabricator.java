package zero.mods.testmod18.test.fabricator;

import net.minecraft.entity.player.InventoryPlayer;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;

public class TileFabricator extends ModTileEntity {

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

        return new ContainerFabricator(player.inventory, world, x, y, z);

        return super.getServerGuiElement(playerInventory);
    }

    @Override
    public Object getClientGuiElement(InventoryPlayer playerInventory) {

        return super.getClientGuiElement(playerInventory);
    }



}

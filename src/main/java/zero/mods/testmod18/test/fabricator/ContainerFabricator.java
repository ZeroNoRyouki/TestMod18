package zero.mods.testmod18.test.fabricator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class ContainerFabricator extends Container {

    public ContainerFabricator(World world, InventoryPlayer playerInventory) {

        this._world = world;
        this._playerInventory = playerInventory;

        this.addSlotToContainer()ddSlotToContainer
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {

        return true;
    }


    private final InventoryPlayer _playerInventory;
    private final World _world;
}

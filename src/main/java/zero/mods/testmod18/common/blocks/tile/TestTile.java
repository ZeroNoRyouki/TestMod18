package zero.mods.testmod18.common.blocks.tile;

import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;


public class TestTile extends ModTileEntity {

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }
}

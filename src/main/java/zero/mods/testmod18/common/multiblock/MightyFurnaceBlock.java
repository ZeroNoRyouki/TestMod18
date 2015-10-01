package zero.mods.testmod18.common.multiblock;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;
import zero.mods.testmod18.common.blocks.BlockWithState;
import zero.mods.zerocore.common.multiblock.rectangular.PartPosition;

public class MightyFurnaceBlock extends BlockWithState {

    public static final PropertyBool FORMED = PropertyBool.create("formed");
    public static final PropertyEnum POSITION = PartPosition.createProperty("position");
    public static final PropertyEnum PARTTYPE = PropertyEnum.create("parttype", PartType.class);


    public enum PartType implements IStringSerializable {

        Wall,
        Input,
        Output;

        @Override
        public String getName() {

            return this.toString();
        }

    }


    public MightyFurnaceBlock(String name) {
        super(name);
    }






}

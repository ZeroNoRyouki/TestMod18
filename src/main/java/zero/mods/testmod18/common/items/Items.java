package zero.mods.testmod18.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by marco on 19/05/2015.
 */
@SuppressWarnings("unused")
public class Items {

    public static final TestModItem testItem = new TestModItem("testItem");
    public static final TestModItem simpleItem = new TestModItem("simpleItem");


    public static void Initialize() {
        // nothing to do here
    }

    @SideOnly(Side.CLIENT)
    public static void RegisterModels() {

        Items.simpleItem.registerModels();
        Items.testItem.registerModels();
    }

}

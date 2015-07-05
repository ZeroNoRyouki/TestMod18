package zero.mods.testmod18;


import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zero.mods.testmod18.common.CommonProxy;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.common.IModInstance;


@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.MOD_VERSION, dependencies = References.MOD_DEPENDENCIES)
public class EntryPoint implements IModInstance {

    public static EntryPoint getInstance() {

        return EntryPoint.s_instance;
    }



    @Override
    public String getModId() {

        return References.MOD_ID;
    }

    @Override
    public CommonProxy getProxy() {

        return EntryPoint.s_proxy;
    }


    @Mod.EventHandler
    @Override
    public void onPreInit(FMLPreInitializationEvent event) {

        org.apache.logging.log4j.Logger log = FMLLog.getLogger();

        log.info("TestMod MC1.8 preInit!");


        this.getProxy().onPreInit(event);

    }

    @Mod.EventHandler
    @Override
    public void onInit(FMLInitializationEvent event) {

        org.apache.logging.log4j.Logger log = FMLLog.getLogger();

        log.info("TestMod MC1.8 init!");

        this.getProxy().onInit(event);
    }

    @Mod.EventHandler
    @Override
    public void onPostInit(FMLPostInitializationEvent event) {

        this.getProxy().onPostInit(event);
    }



    @Mod.Instance
    private static EntryPoint s_instance;

    @SidedProxy (modId = References.MOD_ID, serverSide = References.COMMON_PROXY_CLASS, clientSide = References.CLIENT_PROXY_CLASS)
    private static CommonProxy s_proxy;


}

package djsrv.supersecretsettings;

import djsrv.supersecretsettings.eventhandler.GuiOptionsEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import djsrv.supersecretsettings.render.ShaderHandler;

@Mod(modid = SuperSecretSettings.MOD_ID, version = SuperSecretSettings.VERSION, clientSideOnly = true)
public class SuperSecretSettings
{
	@Instance(SuperSecretSettings.MOD_ID)
	public static SuperSecretSettings instance;
	
    public static final String MOD_ID = "supersecretsettings";
    public static final String VERSION = "1.0";
    
    public ShaderHandler shaderHandler;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	this.shaderHandler = new ShaderHandler(Minecraft.getMinecraft());
        MinecraftForge.EVENT_BUS.register(new GuiOptionsEventHandler());
    }
}

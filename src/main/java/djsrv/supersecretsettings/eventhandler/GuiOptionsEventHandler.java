package djsrv.supersecretsettings.eventhandler;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import djsrv.supersecretsettings.gui.GuiSuperSecretOptions;
import djsrv.supersecretsettings.lib.LibObfuscation;

public class GuiOptionsEventHandler
{	
	@SubscribeEvent
	public void modifyGuiOptions(GuiOpenEvent event)
	{
		if (event.getGui() instanceof GuiOptions && !(event.getGui() instanceof GuiSuperSecretOptions))
		{
			GuiOptions originalGui = (GuiOptions) event.getGui();
			GuiScreen lastScreen = ReflectionHelper.getPrivateValue(GuiOptions.class, originalGui, LibObfuscation.LAST_SCREEN);
			GameSettings settings = ReflectionHelper.getPrivateValue(GuiOptions.class, originalGui, LibObfuscation.SETTINGS);
			event.setGui(new GuiSuperSecretOptions(lastScreen, settings));
		}
	}
}

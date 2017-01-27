package djsrv.supersecretsettings.gui;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.SoundEvents;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.ResourceLocation;
import djsrv.supersecretsettings.SuperSecretSettings;
import djsrv.supersecretsettings.render.ShaderHandler;

public class GuiSuperSecretOptions extends GuiOptions 
{
	private ShaderHandler shaderHandler;
	
	public GuiSuperSecretOptions(GuiScreen lastScreen, GameSettings settings) 
	{
		super(lastScreen, settings);
		this.shaderHandler = SuperSecretSettings.instance.shaderHandler;
	}
	
	public void initGui() 
	{
		super.initGui();
		
		GuiButton doneButton = this.buttonList.stream().filter(button -> button.id == 200).findFirst().get();
		doneButton.yPosition += 24;
		
		this.buttonList.add(new GuiButton(8675309, this.width / 2 - 75, this.height / 6 + 144 - 6, 150, 20, "Super Secret Settings...")
		{	
		    public void playPressSound(SoundHandler soundHandlerIn)
		    {
				String[] allowedPrefixes = { "block", "entity" };
				
		    	List<ResourceLocation> soundList = SoundEvent.REGISTRY.getKeys().stream().filter(resource -> {
		    		return StringUtils.startsWithAny(resource.getResourcePath(), allowedPrefixes);
		    	}).collect(Collectors.toList());
		    	
		    	SoundEvent soundEvent = null;
		    	if (!soundList.isEmpty())
		    	{
		    		soundEvent = SoundEvent.REGISTRY.getObject(soundList.get(new Random().nextInt(soundList.size())));
		    	}
		    	if (soundEvent != null)
		    	{
		    		soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(soundEvent, 0.5F));
		    	}
		    }
		});
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.enabled && button.id == 8675309)
		{
			this.shaderHandler.activateNextShader();
		}
		super.actionPerformed(button);
	}
}

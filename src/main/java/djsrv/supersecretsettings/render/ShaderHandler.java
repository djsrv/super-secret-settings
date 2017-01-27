package djsrv.supersecretsettings.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import djsrv.supersecretsettings.lib.LibObfuscation;

public class ShaderHandler {
	
	private Minecraft mc;
	
	public ShaderHandler(Minecraft mc)
	{
		this.mc = mc;
	}
	
	public void activateNextShader()
	{		
		if (OpenGlHelper.shadersSupported)
		{
			if (this.mc.getRenderViewEntity() instanceof EntityPlayer)
			{
				EntityRenderer entityRenderer = this.mc.entityRenderer;
				ResourceLocation[] shaderResourceLocations = ReflectionHelper.getPrivateValue(EntityRenderer.class, entityRenderer, LibObfuscation.SHADERS_TEXTURES);
				
				ShaderGroup theShaderGroup = ReflectionHelper.getPrivateValue(EntityRenderer.class, entityRenderer, LibObfuscation.SHADER_GROUP);
				if (theShaderGroup != null)
				{
					theShaderGroup.deleteShaderGroup();
				}

				int shaderIndex = ReflectionHelper.getPrivateValue(EntityRenderer.class, entityRenderer, LibObfuscation.SHADER_INDEX);
				shaderIndex = (shaderIndex + 1) % (entityRenderer.SHADER_COUNT + 1);
				ReflectionHelper.setPrivateValue(EntityRenderer.class, entityRenderer, shaderIndex, LibObfuscation.SHADER_INDEX);

				if (shaderIndex != entityRenderer.SHADER_COUNT)
				{
					entityRenderer.loadShader(shaderResourceLocations[shaderIndex]);
				}
				else
				{
					ReflectionHelper.setPrivateValue(EntityRenderer.class, entityRenderer, null, LibObfuscation.SHADER_GROUP);
				}
			}
		}
	}
}

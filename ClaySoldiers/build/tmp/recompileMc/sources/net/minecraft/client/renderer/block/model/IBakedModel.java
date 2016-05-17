package net.minecraft.client.renderer.block.model;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IBakedModel
{
    List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand);

    boolean isAmbientOcclusion();

    boolean isGui3d();

    boolean isBuiltInRenderer();

    TextureAtlasSprite getParticleTexture();

    @Deprecated
    ItemCameraTransforms getItemCameraTransforms();

    ItemOverrideList getOverrides();
}
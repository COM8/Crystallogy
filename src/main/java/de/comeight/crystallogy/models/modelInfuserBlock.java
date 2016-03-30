package de.comeight.crystallogy.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelInfuserBlock extends ModelBase
{
	ModelRenderer Elem9;//Cube
	ModelRenderer Elem8;//Cube
	ModelRenderer Elem7;//Cube
	ModelRenderer Elem6;//Cube
	ModelRenderer Elem5;//Cube
	ModelRenderer Elem4;//Cube

	public modelInfuserBlock()
	{
		textureWidth = 64;
		textureHeight = 32;

		//Cube
		Elem9 = new ModelRenderer(this, 0, 0);
		Elem9.addBox(0F, 0F, 0F, 1, 1, 8);
		Elem9.setRotationPoint(4F, 15F, -4F);
		Elem9.setTextureSize(64, 32);
		setRotation(Elem9, -0F, -0F, -0F);
		//Elem9.rotateAngleX = -0F;
		//Elem9.rotateAngleY = -0F;
		//Elem9.rotateAngleZ = -0F;
		Elem9.mirror = true;
		//Cube
		Elem8 = new ModelRenderer(this, 0, 0);
		Elem8.addBox(0F, 0F, 0F, 1, 1, 8);
		Elem8.setRotationPoint(-5F, 15F, -4F);
		Elem8.setTextureSize(64, 32);
		setRotation(Elem8, -0F, -0F, -0F);
		//Elem8.rotateAngleX = -0F;
		//Elem8.rotateAngleY = -0F;
		//Elem8.rotateAngleZ = -0F;
		Elem8.mirror = true;
		//Cube
		Elem7 = new ModelRenderer(this, 0, 0);
		Elem7.addBox(0F, 0F, 0F, 10, 1, 1);
		Elem7.setRotationPoint(-5F, 15F, 4F);
		Elem7.setTextureSize(64, 32);
		setRotation(Elem7, -0F, -0F, -0F);
		//Elem7.rotateAngleX = -0F;
		//Elem7.rotateAngleY = -0F;
		//Elem7.rotateAngleZ = -0F;
		Elem7.mirror = true;
		//Cube
		Elem6 = new ModelRenderer(this, 0, 0);
		Elem6.addBox(0F, 0F, 0F, 10, 1, 1);
		Elem6.setRotationPoint(-5F, 15F, -5F);
		Elem6.setTextureSize(64, 32);
		setRotation(Elem6, -0F, -0F, -0F);
		//Elem6.rotateAngleX = -0F;
		//Elem6.rotateAngleY = -0F;
		//Elem6.rotateAngleZ = -0F;
		Elem6.mirror = true;
		//Cube
		Elem5 = new ModelRenderer(this, 0, 0);
		Elem5.addBox(0F, 0F, 0F, 8, 7, 8);
		Elem5.setRotationPoint(-4F, 16F, -4F);
		Elem5.setTextureSize(64, 32);
		setRotation(Elem5, -0F, -0F, -0F);
		//Elem5.rotateAngleX = -0F;
		//Elem5.rotateAngleY = -0F;
		//Elem5.rotateAngleZ = -0F;
		Elem5.mirror = true;
		//Cube
		Elem4 = new ModelRenderer(this, 0, 0);
		Elem4.addBox(0F, 0F, 0F, 16, 1, 16);
		Elem4.setRotationPoint(-8F, 23F, -8F);
		Elem4.setTextureSize(64, 32);
		setRotation(Elem4, -0F, -0F, -0F);
		//Elem4.rotateAngleX = -0F;
		//Elem4.rotateAngleY = -0F;
		//Elem4.rotateAngleZ = -0F;
		Elem4.mirror = true;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Elem9.render(f5);//Cube
		Elem8.render(f5);//Cube
		Elem7.render(f5);//Cube
		Elem6.render(f5);//Cube
		Elem5.render(f5);//Cube
		Elem4.render(f5);//Cube
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
package de.comeight.crystallogy.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modellCrystallCrusher extends ModelBase
{
	ModelRenderer Elem15;//Cube*
	ModelRenderer Elem14;//Cube
	ModelRenderer Elem13;//Cube
	ModelRenderer Elem12;//Cube*
	ModelRenderer Elem11;//Cube
	ModelRenderer Elem10;//Cube
	ModelRenderer Elem9;//Cube
	ModelRenderer Elem8;//Cube
	ModelRenderer Elem7;//Cube
	ModelRenderer Elem6;//Cube
	ModelRenderer Elem5;//Cube
	ModelRenderer Elem4;//Cube

	public modellCrystallCrusher()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		//Cube*
		Elem15 = new ModelRenderer(this, 0, 0);
		Elem15.addBox(0F, 0F, 0F, 6, 1, 14);
		Elem15.setRotationPoint(-7F, 13F, -7F);
		Elem15.setTextureSize(64, 32);
		setRotation(Elem15, -0F, -0F, -0F);
		//Elem15.rotateAngleX = -0F;
		//Elem15.rotateAngleY = -0F;
		//Elem15.rotateAngleZ = -0F;
		Elem15.mirror = true;
		
		//Cube
		Elem14 = new ModelRenderer(this, 0, 0);
		Elem14.addBox(0F, 0F, 0F, 6, 1, 14);
		Elem14.setRotationPoint(1F, 13F, -7F);
		Elem14.setTextureSize(64, 32);
		setRotation(Elem14, -0F, -0F, -0F);
		//Elem14.rotateAngleX = -0F;
		//Elem14.rotateAngleY = -0F;
		//Elem14.rotateAngleZ = -0F;
		Elem14.mirror = true;
		
		//Cube
		Elem13 = new ModelRenderer(this, 0, 0);
		Elem13.addBox(0F, 0F, 0F, 16, 1, 16);
		Elem13.setRotationPoint(-8F, 12F, -8F);
		Elem13.setTextureSize(64, 32);
		setRotation(Elem13, -0F, -0F, -0F);
		//Elem13.rotateAngleX = -0F;
		//Elem13.rotateAngleY = -0F;
		//Elem13.rotateAngleZ = -0F;
		Elem13.mirror = true;
		
		//Cube*
		Elem12 = new ModelRenderer(this, 0, 0);
		Elem12.addBox(0F, 0F, 0F, 1, 7, 1);
		Elem12.setRotationPoint(-0.5F, 13F, -6.5F);
		Elem12.setTextureSize(64, 32);
		setRotation(Elem12, -0F, -0F, -0F);
		//Elem12.rotateAngleX = -0F;
		//Elem12.rotateAngleY = -0F;
		//Elem12.rotateAngleZ = -0F;
		Elem12.mirror = true;
		
		//Cube
		Elem11 = new ModelRenderer(this, 0, 0);
		Elem11.addBox(0F, 0F, 0F, 1, 7, 1);
		Elem11.setRotationPoint(-0.5F, 13F, 5.5F);
		Elem11.setTextureSize(64, 32);
		setRotation(Elem11, -0F, -0F, -0F);
		//Elem11.rotateAngleX = -0F;
		//Elem11.rotateAngleY = -0F;
		//Elem11.rotateAngleZ = -0F;
		Elem11.mirror = true;
		
		//Cube
		Elem10 = new ModelRenderer(this, 0, 0);
		Elem10.addBox(0F, 0F, 0F, 2, 7, 2);
		Elem10.setRotationPoint(-1F, 16F, 5F);
		Elem10.setTextureSize(64, 32);
		setRotation(Elem10, -0F, -0F, -0F);
		//Elem10.rotateAngleX = -0F;
		//Elem10.rotateAngleY = -0F;
		//Elem10.rotateAngleZ = -0F;
		Elem10.mirror = true;
		
		//Cube
		Elem9 = new ModelRenderer(this, 0, 0);
		Elem9.addBox(0F, 0F, 0F, 2, 7, 2);
		Elem9.setRotationPoint(-1F, 16F, -7F);
		Elem9.setTextureSize(64, 32);
		setRotation(Elem9, -0F, -0F, -0F);
		//Elem9.rotateAngleX = -0F;
		//Elem9.rotateAngleY = -0F;
		//Elem9.rotateAngleZ = -0F;
		Elem9.mirror = true;
		
		//Cube
		Elem8 = new ModelRenderer(this, 0, 0);
		Elem8.addBox(0F, 0F, 0F, 1, 7, 15);
		Elem8.setRotationPoint(-8F, 16F, -7F);
		Elem8.setTextureSize(64, 32);
		setRotation(Elem8, -0F, -0F, -0F);
		//Elem8.rotateAngleX = -0F;
		//Elem8.rotateAngleY = -0F;
		//Elem8.rotateAngleZ = -0F;
		Elem8.mirror = true;
		
		//Cube
		Elem7 = new ModelRenderer(this, 0, 0);
		Elem7.addBox(0F, 0F, 0F, 15, 7, 1);
		Elem7.setRotationPoint(-7F, 16F, 7F);
		Elem7.setTextureSize(64, 32);
		setRotation(Elem7, -0F, -0F, -0F);
		//Elem7.rotateAngleX = -0F;
		//Elem7.rotateAngleY = -0F;
		//Elem7.rotateAngleZ = -0F;
		Elem7.mirror = true;
		
		//Cube
		Elem6 = new ModelRenderer(this, 0, 0);
		Elem6.addBox(0F, 0F, 0F, 1, 7, 15);
		Elem6.setRotationPoint(7F, 16F, -8F);
		Elem6.setTextureSize(64, 32);
		setRotation(Elem6, -0F, -0F, -0F);
		//Elem6.rotateAngleX = -0F;
		//Elem6.rotateAngleY = -0F;
		//Elem6.rotateAngleZ = -0F;
		Elem6.mirror = true;
		
		//Cube
		Elem5 = new ModelRenderer(this, 0, 0);
		Elem5.addBox(0F, 0F, 0F, 15, 7, 1);
		Elem5.setRotationPoint(-8F, 16F, -8F);
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
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Elem15.render(f5);//Cube*
		Elem14.render(f5);//Cube
		Elem13.render(f5);//Cube
		Elem12.render(f5);//Cube*
		Elem11.render(f5);//Cube
		Elem10.render(f5);//Cube
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
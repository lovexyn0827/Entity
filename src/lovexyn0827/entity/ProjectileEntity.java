package lovexyn0827.entity;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ProjectileEntity extends Entity {
	
	private double gravity;
	private double drag;
	
	public ProjectileEntity(Vec3d pos, Vec3d motion, double gravity, double drag) {
		super(pos, motion);
		this.gravity = gravity;
		this.drag = drag;
	}
	
	public void throwTowards(float yaw, float pitch, float speed, float error) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float g = -MathHelper.sin((pitch) * 0.017453292F);
		float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		Vec3d vec3d = (new Vec3d(f, g, h)).normalize().add(this.rand.nextGaussian() * 0.007499999832361937D * error, 
				this.rand.nextGaussian() * 0.007499999832361937D * error, 
				this.rand.nextGaussian() * 0.007499999832361937D * error).multiply(speed);
		this.motion = vec3d;
	}

	@Override
	public void tick() {
		this.moveP();
		this.motion = this.motion.multiply(this.drag);
		this.accelerate(0, -gravity, 0);
		this.firstUpdate = false;
	}
	
}

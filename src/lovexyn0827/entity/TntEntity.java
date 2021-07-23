package lovexyn0827.entity;

import net.minecraft.util.math.Vec3d;

public class TntEntity extends Entity {

	int fuse = 80;
	
	public TntEntity(Vec3d pos, Vec3d motion) {
		super(pos, motion);
	}

	@Override
	public void tick() {
		this.accelerate(0, -0.04, 0);
		this.move();
		this.motion = this.motion.multiply(0.98);
		if(this.onGround) {
			this.motion = this.motion.multiply(0.7, 0, 0.7);
		}
		--this.fuse;
	}

}

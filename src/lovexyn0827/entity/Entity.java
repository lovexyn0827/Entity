package lovexyn0827.entity;

import java.util.Random;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public abstract class Entity {
	private static final Random MOTHER_RAND = new Random(827);
	Vec3d pos;
	Vec3d motion;
	int age = 0;
	Box aabb = new Box(0, 0, 0, 0, 0, 0);
	float yaw;
	float pitch;
	boolean onGround;
	Random rand = new Random(MOTHER_RAND.nextLong());
	boolean hit = false;
	boolean firstUpdate = true;
	boolean leftNegative = false;
	
	public Entity(Vec3d pos, Vec3d motion) {
		this.pos = pos;
		this.motion = motion;
	}
	
	public abstract void tick();
	
	public void accelerate(double x, double y, double z) {
		this.motion = this.motion.add(x, y, z);
	}
	
	public void move(double x, double y, double z) {
		if((this.pos.y + y) * this.pos.y <0 && this.leftNegative) {
			this.onGround = true;
			y = -this.pos.y;
			this.hit = true;
		} else {
			this.onGround = false;
		}
		this.aabb = this.aabb.offset(x, y, z);
		this.pos = this.pos.add(x, y, z);
		this.leftNegative |= this.pos.y > 0;
	}
	
	public void move() {
		this.move(this.motion.x, this.motion.y, this.motion.z);
	}
	
	public void moveP(double x, double y, double z) {
		if(this.pos.y + y <= 0 && this.leftNegative) {
			double progress = -this.pos.y / y;
			x *= progress;
			y = -this.pos.y;
			z *= progress;
			this.hit = !this.firstUpdate;
		}
		
		this.aabb = this.aabb.offset(x, y, z);
		this.pos = this.pos.add(x, y, z);
		this.leftNegative |= this.pos.y > 0;
	}
	
	public void moveP() {
		this.moveP(this.motion.x, this.motion.y, this.motion.z);
	}
}

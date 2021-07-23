package lovexyn0827.entity;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ElytraEntity extends Entity {

	public ElytraEntity(float yaw, float pitch, Vec3d pos, Vec3d motion) {
		super(pos, motion);
		this.yaw = yaw;
		this.pitch = pitch;
	}

	@Override
	public void tick() {
		Vec3d v = this.motion;
        Vec3d facing = this.getRotationVector();	//视线方向的单位向量
        float pitch = this.pitch * 0.017453292F;	//俯仰角
        double cosp = Math.sqrt(facing.x * facing.x + facing.z * facing.z);		//俯仰角的余弦
        double vxz0 = Math.sqrt(v.x * v.x + v.z * v.z);		//水平方向合速度
        double c1 = facing.length();	//永远等于1
        float n = MathHelper.cos(pitch);	//有一个余弦，用以不明
        n = (float)((double)n * (double)n * Math.min(1.0D, c1 / 0.4D));	//不懂Mojang
        v = this.motion.add(0.0D, 0.08 * (-1.0D + (double)n * 0.75D), 0.0D);	//重力
        double q;
        if (v.y < 0.0D && cosp > 0.0D) {	//平时或视线斜向下且Y轴Motion小于0
        	//水平轴加速，Y轴收到大小0.1cos^2p gt^-1的阻力
            q = v.y * -0.1D * (double)n;
            v = v.add(facing.x * q / cosp, q, facing.z * q / cosp);
        }

        if (pitch < 0.0F && cosp > 0.0D) {	//视线斜向上
        	//Y轴向上加速，水平轴受阻力
            q = vxz0 * (double)(-MathHelper.sin(pitch)) * 0.04D;
            v = v.add(-facing.x * q / cosp, q * 3.2D, -facing.z * q / cosp);
        }

        if (cosp > 0.0D) {	//视线不竖直
        	//从开始到现在的水平轴的速度变化量削减0.1倍
            v = v.add((facing.x / cosp * vxz0 - v.x) * 0.1D, 0.0D, (facing.z / cosp * vxz0 - v.z) * 0.1D);
        }

        this.motion = v.multiply(0.9900000095367432D, 0.9800000190734863D, 0.9900000095367432D);
        this.move();
	}

	private Vec3d getRotationVector() {
		float f = this.pitch * 0.017453292F;
		float g = -this.yaw * 0.017453292F;
		float h = MathHelper.cos(g);
		float i = MathHelper.sin(g);
		float j = MathHelper.cos(f);
		float k = MathHelper.sin(f);
		return new Vec3d((double)(i * j), (double)(-k), (double)(h * j));
	}

}

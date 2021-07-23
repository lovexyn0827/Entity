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
        Vec3d facing = this.getRotationVector();	//���߷���ĵ�λ����
        float pitch = this.pitch * 0.017453292F;	//������
        double cosp = Math.sqrt(facing.x * facing.x + facing.z * facing.z);		//�����ǵ�����
        double vxz0 = Math.sqrt(v.x * v.x + v.z * v.z);		//ˮƽ������ٶ�
        double c1 = facing.length();	//��Զ����1
        float n = MathHelper.cos(pitch);	//��һ�����ң����Բ���
        n = (float)((double)n * (double)n * Math.min(1.0D, c1 / 0.4D));	//����Mojang
        v = this.motion.add(0.0D, 0.08 * (-1.0D + (double)n * 0.75D), 0.0D);	//����
        double q;
        if (v.y < 0.0D && cosp > 0.0D) {	//ƽʱ������б������Y��MotionС��0
        	//ˮƽ����٣�Y���յ���С0.1cos^2p gt^-1������
            q = v.y * -0.1D * (double)n;
            v = v.add(facing.x * q / cosp, q, facing.z * q / cosp);
        }

        if (pitch < 0.0F && cosp > 0.0D) {	//����б����
        	//Y�����ϼ��٣�ˮƽ��������
            q = vxz0 * (double)(-MathHelper.sin(pitch)) * 0.04D;
            v = v.add(-facing.x * q / cosp, q * 3.2D, -facing.z * q / cosp);
        }

        if (cosp > 0.0D) {	//���߲���ֱ
        	//�ӿ�ʼ�����ڵ�ˮƽ����ٶȱ仯������0.1��
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

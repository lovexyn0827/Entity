package lovexyn0827.entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Main {

	public static void main(String[] args) {
		//projectileHit();
		//speedToMaxDist();
		//elytraTestA();
		//tntExplosion();
		projectileHitB();
		//ExplosionSamplingPoints.getExposureVisible();
		System.out.println("Finished");
	}

	@SuppressWarnings("unused")
	private static void projectileHitB() {
		JFrame f = new JFrame();
		f.setSize(1024, 640);
		Canvas c = new Canvas();
		c.setSize(1024,600);
		f.add(c);
		f.setVisible(true);
		Image im = c.createImage(1024, 6000);
		Graphics g = im.getGraphics();
		g.setColor(Color.BLACK);
		for(double d = 0; d <= 50; d += 0.01) {
			double dist = 0;
			double pmax = 0;
			//System.out.println("-");
			for(double p = -90; p <= 90; p += 0.01) {
				ProjectileEntity e = new ProjectileEntity(new Vec3d(0, d, 0), Vec3d.ZERO, 0.03, 0.99);
				e.throwTowards((float)270.0, (float)p, (float) d, 0);
				while(!e.hit && !(e.pos.y < d && e.motion.y < 0)) {
					e.tick();
				}
				
				if(e.pos.x > dist) {
					dist = e.pos.x;
					pmax = p;
				}
			}
			//
			//g.drawRect((int) (d * 100), (int) (600 - dist / 2), 1, 1);
			g.drawRect((int) (d * 20),  600 - (int) (-pmax * 5), 0, 0);
		}
		
		g.drawLine(0, 600, 900, 600);
		for(int i = 0; i <= 1000; i += 10) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 1; i <= 1001; i += 100) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 0; i <= 600; i += 10) {
			g.drawLine(0, i, 1000, i);
		}
		
		for(int i = 1; i <= 601; i += 100) {
			g.drawLine(0, i, 1000, i);
		}
		while(true) {
			c.getGraphics().drawImage(im, 0, 0, c);
		}
	}

	@SuppressWarnings("unused")
	//Maybe buggy
	private static void tntExplosion() {
		JFrame f = new JFrame();
		f.setSize(920, 640);
		Canvas c = new Canvas();
		c.setSize(920,600);
		f.add(c);
		f.setVisible(true);
		Graphics g = c.getGraphics();
		g.setColor(Color.BLACK);
		for(float p = -90.0F; p <= 0; p+=0.001) {
			TntEntity e = new TntEntity(Vec3d.ZERO, new Vec3d(MathHelper.cos(p), -MathHelper.sin(p), 0));
			for(int i = 0; i < 80; i++) {
				e.tick();
			}
			g.drawRect((int) (-p * 10 ), (int) (600 - e.pos.x * 10), 1, 1);
		}
		
		g.drawLine(0, 600, 900, 600);
		for(int i = 00; i <= 900; i += 10) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 1; i <= 901; i += 100) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 0; i <= 600; i += 10) {
			g.drawLine(0, i, 900, i);
		}
		
		for(int i = 1; i <= 601; i += 100) {
			g.drawLine(0, i, 900, i);
		}
	}

	@SuppressWarnings("unused")
	private static void elytraTestA() {
		JFrame f = new JFrame();
		f.setSize(920, 740);
		Canvas c = new Canvas();
		c.setSize(920,700);
		f.add(c);
		f.setVisible(true);
		Graphics g = c.getGraphics();
		g.setColor(Color.BLACK);
		for(float p = -90.0F; p <= 90.0F; p+=0.01) {
			ElytraEntity e = new ElytraEntity(0, p, new Vec3d(0.0F, 10E7, 0), Vec3d.ZERO);
			for(int i = 0; i < 72000; i++) {
				e.tick();
			}
			g.drawRect((int) (p * 5 + 450), (int) (700 - e.motion.z * 198), 1, 1);
		}
		
		g.drawLine(0, 700, 900, 700);
		for(int i = 00; i <= 900; i += 10) {
			g.drawLine(i, 0, i, 700);
		}
		
		for(int i = 1; i <= 901; i += 100) {
			g.drawLine(i, 0, i, 700);
		}
		
		for(int i = 0; i <= 700; i += 10) {
			g.drawLine(0, i, 900, i);
		}
		
		for(int i = 1; i <= 701; i += 100) {
			g.drawLine(0, i, 900, i);
		}
	}

	@SuppressWarnings("unused")
	private static void speedToMaxDist() {
		JFrame f = new JFrame();
		f.setSize(1024, 640);
		Canvas c = new Canvas();
		c.setSize(1024,600);
		f.add(c);
		f.setVisible(true);
		Graphics g = c.getGraphics();
		g.setColor(Color.BLACK);
		for(double d = 10E-7; d <= 10; d += 0.01) {
			double dist = 0;
			double pmax = 0;
			for(double p = -90.0; p <= 0; p += 0.01) {
				ProjectileEntity e = new ProjectileEntity(Vec3d.ZERO, Vec3d.ZERO, 0.03, 0.99);
				e.throwTowards((float)270.0, (float)p, (float) d, 0);
				while(!e.hit) {
					e.tick();
				}
				
				if(e.pos.x > dist) {
					dist = e.pos.x;
					pmax = p;
				}
			}
			g.drawRect((int) (d * 100), (int) (600 - dist / 2), 1, 1);
			//g.drawRect((int) (d * 100),  600 - (int) (-pmax * 5), 0, 0);
		}
		
		g.drawLine(0, 600, 900, 600);
		for(int i = 0; i <= 1000; i += 10) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 1; i <= 1001; i += 100) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 0; i <= 600; i += 10) {
			g.drawLine(0, i, 1000, i);
		}
		
		for(int i = 1; i <= 601; i += 100) {
			g.drawLine(0, i, 1000, i);
		}
	}

	@SuppressWarnings("unused")
	private static void projectileHit() {
		JFrame f = new JFrame();
		f.setSize(920, 640);
		Canvas c = new Canvas();
		c.setSize(920,600);
		f.add(c);
		f.setVisible(true);
		Graphics g = c.getGraphics();
		g.setColor(Color.BLACK);
		for(double p = -90.0; p <= 0; p+=0.01) {
			ProjectileEntity e = new ProjectileEntity(Vec3d.ZERO, Vec3d.ZERO, 0.03, 0.99);
			e.throwTowards((float)270.0, (float)p, (float) 1.5, 0);
			while(!e.hit) {
				e.tick();
			}
			g.drawRect((int) (-p * 10 ), (int) (600 - e.pos.x * 10), 1, 1);
		}
		
		g.drawLine(0, 600, 900, 600);
		for(int i = 00; i <= 900; i += 10) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 1; i <= 901; i += 100) {
			g.drawLine(i, 0, i, 600);
		}
		
		for(int i = 0; i <= 600; i += 10) {
			g.drawLine(0, i, 900, i);
		}
		
		for(int i = 1; i <= 601; i += 100) {
			g.drawLine(0, i, 900, i);
		}
	}

}

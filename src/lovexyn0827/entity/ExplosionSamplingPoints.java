package lovexyn0827.entity;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class ExplosionSamplingPoints {
	public static List<Vec3d> getExposureSamplingPoints(Box box) {
	      double d = 1.0D / ((box.maxX - box.minX) * 2.0D + 1.0D);
	      double e = 1.0D / ((box.maxY - box.minY) * 2.0D + 1.0D);
	      double f = 1.0D / ((box.maxZ - box.minZ) * 2.0D + 1.0D);
	      double g = (1.0D - Math.floor(1.0D / d) * d) / 2.0D;
	      double h = (1.0D - Math.floor(1.0D / f) * f) / 2.0D;
	      List<Vec3d> list = new ArrayList<>();
	      if (d >= 0.0D && e >= 0.0D && f >= 0.0D) {
	         for(float k = 0.0F; k <= 1.0F; k = (float)((double)k + d)) {
	            for(float l = 0.0F; l <= 1.0F; l = (float)((double)l + e)) {
	               for(float m = 0.0F; m <= 1.0F; m = (float)((double)m + f)) {
	                  double n = lerp((double)k, box.minX, box.maxX);
	                  double o = lerp((double)l, box.minY, box.maxY);
	                  double p = lerp((double)m, box.minZ, box.maxZ);
	                  Vec3d vec3d = new Vec3d(n + g, o, p + h);
	                  list.add(vec3d);
	               }
	            }
	         }
	         
	         return list;
	      } else {
	         return list;
	      }
	   }

	private static double lerp(double l, double min, double max) {
		return l*(max-min)+min;
	}

	public static void getExposureVisible() {
		JFrame jf = new JFrame();
		jf.setLayout(new FlowLayout());
		jf.setSize(600, 600);
		Canvas c = new Canvas();
		c.setSize(520, 520);
		jf.add(c);
		//c.setLocation(0, 80);
		JSlider js = new JSlider();
		js.setSize(500, 50);
		js.setMinimum(0);
		js.setMaximum(200);
		jf.add(js);
		JLabel l = new JLabel("0");
		jf.add(l);
		jf.setVisible(true);
		while(true) {
			Image im = c.createImage(520, 520);
			Graphics g = im.getGraphics();
			double d = (double) (js.getValue()) / 100;
			l.setText(Double.toString(d));
			g.drawRect(0, 0, js.getValue() * 2, js.getValue() * 2);
			for(Vec3d p : getExposureSamplingPoints(new Box(0, 0, 0, d, 0.98, d))) {
				g.fillRect((int) (p.x * 200 - 1), (int) (p.z * 200 - 1), 3, 3);
			}
			c.getGraphics().drawImage(im, 0, 0, c);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			System.gc();
		}
	}
	
	static class Vec3d {
		public final double x;
		public final double y;
		public final double z;
		
		public Vec3d(double x,double y,double z) {
			this.x = x;
			this.y = y;
			this.z = z;
			
		}
	}

	static class Box {
		
		public Box(double minZ, double minY, double minX, double maxZ, double maxY, double maxX) {
			this.maxX = maxX;
			this.minX = minX;
			this.maxY = maxY;
			this.minY = minY;
			this.maxZ = maxZ;
			this.minZ = minZ;
		}
		
		public final double maxX;
		public final double minX;
		public final double maxY;
		public final double minY;
		public final double maxZ;
		public final double minZ;
	
	}
}

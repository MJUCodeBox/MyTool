package shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
/**
 * @author SanghyeonJung
 *
 */
public class NStar{

	public static Shape getShape(double centerX, double centerY, double outR, double inR, int nSpikes) {
		int nPoints = nSpikes * 2 + 1;

		GeneralPath path = new GeneralPath();
		path.moveTo(centerX + outR * Math.cos(Math.toRadians(- 90)), centerY + outR * Math.sin(Math.toRadians(- 90)));
		
		for (int i = 1; i < nPoints; i++) {
			double r = (i % 2 == 0) ? outR : inR;
			double angle = (i * 360.0) / (2 * nSpikes);
			path.lineTo(centerX + r * Math.cos(Math.toRadians(angle - 90)), centerY + r * Math.sin(Math.toRadians(angle - 90)));
		}

		path.closePath();
		return path;
	}
}

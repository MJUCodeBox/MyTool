package tool;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import math.SSMath;
/**
 * @author SanghyeonJung
 *
 */
public class ShapeTool {

	// Dictionary
	// Point2D getCenterPoint(Shape s)
	// Shape getCompound(Shape... shapes)
	// Shape getCompound(Vector<Shape> shapes)
	// Shape getSubtract(Shape target, Shape... shapes)
	// Shape getSubtract(Shape target, Vector<Rectangle> shapes)
	// Shape getIntersect(Shape s1, Shape s2)
	// boolean isIntersect(Shape s1, Shape s2)    
	// Shape getShapeByArea(Area a)
	// Shape getMovedShapeCenterTo(Shape s, double x, double y)
	// Shape getMovedShape(Shape s, double dx, double dy)
	// Shape getRotatedShapeHeadTo(Shape s, Point2D headPoint)
	// Shape getScaledShape(Shape s, double scaleX, double scaleY)
	// Shape getMaintainCenterScaledShape(Shape s, double scaleX, double scaleY)
	// Shape getRotatedShape(Shape s, double angle)
	// Shape getUpDownMirrorShape(Shape s)
	// Shape getLeftRightMirrorShape(Shape s)
	
	public static Point2D getCenterPoint(Shape s) {
		Rectangle2D bound = s.getBounds2D();
		double centerX = bound.getCenterX();
		double centerY = bound.getCenterY();
		return new Point2D.Double(centerX, centerY);
	}

	public static Shape getCompound(Shape... shapes) {
		Path2D result = new Path2D.Float();
		for(Shape s : shapes) {result.append(s, false);}
		return result;
	}
	
	public static Shape getCompound(Vector<Shape> shapes) {
		Path2D result = new Path2D.Float();
		for(Shape s : shapes) {result.append(s, false);}
		return result;
	}
	
	public static Shape getSubtract(Shape target, Shape... shapes) {
		Area a = new Area(target);
		for(int i=0; i<shapes.length; i++) {a.subtract(new Area(shapes[i]));}
		return getShapeByArea(a);
	}
	
	public static Shape getSubtract(Shape target, Vector<Rectangle> shapes) {
		Area a = new Area(target);
		for(Rectangle s : shapes) {a.subtract(new Area(s));}
		return getShapeByArea(a);
	}
	
	public static Shape getIntersect(Shape s1, Shape s2) {
		Area a = new Area(s1);
		a.intersect(new Area(s2));
		return getShapeByArea(a);
	}
	
	public static boolean isIntersect(Shape s1, Shape s2) {
		Area a = new Area(s1);
		a.intersect(new Area(s2));
		return !a.isEmpty();
	}
	
	public static Shape getShapeByArea(Area a) {
		return AffineTransform.getTranslateInstance(0,0).createTransformedShape(a);
	}
	
	public static Shape getMovedShapeCenterTo(Shape s, double x, double y) {
		Rectangle2D bound = s.getBounds2D();
		AffineTransform at = new AffineTransform();
		at.translate(x - bound.getCenterX(), y - bound.getCenterY());
		return at.createTransformedShape(s);
	}

	public static Shape getMovedShape(Shape s, double dx, double dy) {
		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		return at.createTransformedShape(s);
	}
	
	public static Shape getRotatedShapeHeadTo(Shape s, Point2D headPoint) {
		Rectangle2D bound = s.getBounds2D();
		Point2D rotateCenterPoint = ShapeTool.getCenterPoint(s);
		Point2D shapeHeadPoint = new Point2D.Double(bound.getX()+bound.getWidth()/2, bound.getY());
		double angle = SSMath.computeRotationAngle(rotateCenterPoint, shapeHeadPoint, headPoint);
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), rotateCenterPoint.getX(), rotateCenterPoint.getY());
		return at.createTransformedShape(s);
	}
	
	public static Shape getScaledShape(Shape s, double scaleX, double scaleY) {
		AffineTransform at = new AffineTransform();
		at.scale(scaleX, scaleY);
		return at.createTransformedShape(s);
	}
	
	public static Shape getMaintainCenterScaledShape(Shape s, double scaleX, double scaleY) {
		Point2D originalCenter = ShapeTool.getCenterPoint(s);
		Shape result = ShapeTool.getScaledShape(s, scaleX, scaleY);
		return ShapeTool.getMovedShapeCenterTo(result, originalCenter.getX(), originalCenter.getY());
	}
	
	public static Shape getRotatedShape(Shape s, double angle) {
		Point2D center = ShapeTool.getCenterPoint(s);
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), center.getX(), center.getY());
		return at.createTransformedShape(s);
	}

	public static Shape getUpDownMirrorShape(Shape s) {
		AffineTransform at = new AffineTransform();
		at.scale(1, -1);
		return at.createTransformedShape(s);
	}
	
	public static Shape getLeftRightMirrorShape(Shape s) {
		AffineTransform at = new AffineTransform();
		at.scale(-1, 1);
		return at.createTransformedShape(s);
	}
	
	public static Shape getTextShapeCenterAt(Graphics2D g, Font f, String text, int x, int y) {
		GlyphVector gv = f.createGlyphVector(g.getFontRenderContext(), text);
		return ShapeTool.getMovedShapeCenterTo(gv.getOutline(), x, y);
	}
}

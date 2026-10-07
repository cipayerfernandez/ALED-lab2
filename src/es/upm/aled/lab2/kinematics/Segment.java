package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing one segment. Each segment is identified by its length in cm
 * and the angle in radians it forms with its father segment.  Every Node has a List of their
 * children Nodes; those it's connected to.
 * 
 */
public class Segment {
	
	private double length;
	private double angle;
	private List<Segment> children = new ArrayList<Segment>();
	
	public Segment (double length, double angle) {
		this.length = length;
		this.angle = angle;
	}
	
	// Getter and setter methods of the class Segment.
	public double getLength() {
		return length;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public List<Segment> getChildren(){
		return children;
	}
	
	// Method that adds a child segment to the current list of segments.
	// It only adds the child if it was not already on the list.
	public void addChild(Segment child) {
		if (!children.contains(child)) {
			children.add(child);
		}
	}
}

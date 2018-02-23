package assignment.amitsir.mma1;

/*
 * 
 * Assignment by :
 * 				Pritom Saha Akash(0604)
 * 				Sharafat Ahmed(0617)
 * 
 * */

public class Segment {
	public int type;
	public double start;
	public double length;
	public Segment next;
	public Segment previous;
	
	public Segment(){
		this.next=this.previous=null;
	}
	public Segment(double start,double length){
		this.type=0;
		this.start=start;
		this.length=length;
		this.next=this.previous=null;
	}
	public Segment(double length){
		this.length=length;
	}
}

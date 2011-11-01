package tests;

import manipSef.SefIntersection;

import org.jfree.data.xy.XYDataItem;

import exceptions.SegmentAboveException;
import exceptions.SegmentsConfondusException;

public class TestSegmentIntersection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XYDataItem pointInter;
		double a1,b1,a2,b2,inf,sup;
		a1=0.3636363636363636;
		b1=0.5454545454545454;
		a2=0;
		b2=0;
		inf= -1.5;
		sup= 0;
		 try {
			pointInter = SefIntersection.segmentIntersection(a1,b1,a2, b2, inf,  sup);
			System.out.println("X: "+pointInter.getXValue()+",Y: "+pointInter.getYValue());
		} catch (SegmentsConfondusException e) {
			//e.printStackTrace();
			System.out.println("segments confondus");
		} catch (SegmentAboveException e) {
//			e.printStackTrace();
			System.out.println("segmentAbove");
		}

	}

}

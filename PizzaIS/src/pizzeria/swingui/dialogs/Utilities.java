package pizzeria.swingui.dialogs;

import java.awt.GraphicsEnvironment;
import java.awt.Point;

public class Utilities {
	public static int[] getCenterPosition(int width, int height){
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		return new int[]{center.x - width / 2, center.y - height / 2};
	}
}

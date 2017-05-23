package algorithm.leetcode.algorithm;
/*
 * medium
 * 223. Rectangle Area
 * Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
Rectangle Area

see NO223_RectangleArea.png

Assume that the total area is never beyond the maximum possible value of int.

 */
public class NO223_RectangleArea {
	public static void main(String[] args) {
		System.out.println(computeArea(-2, -2, 2, 2, -3, 3, -4, 4));
	}
	public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = 0,area2 = 0,area3 = 0;
        area1 = (C-A)*(D-B);
        area2 = (G-E)*(H-F);
        int left = Math.max(A,E);
        int right = Math.min(C,G);
        int bottom = Math.max(B,F);
        int top = Math.min(D,H);
        if(left < right && top > bottom){
            area3 = (right - left) * (top - bottom);
        }
        return area1 + area2 - area3;
    }
}

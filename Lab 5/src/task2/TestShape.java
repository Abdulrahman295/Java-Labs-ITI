package task2;

import java.util.List;

public class TestShape {
    public static void runTests() {
        Rect rect = new Rect();
        Circle circle = new Circle();
        List<Shape> shapes = List.of(rect, circle);
        testDrawShape(shapes);
    }

    public static void testDrawShape(List<Shape> shapes) {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}

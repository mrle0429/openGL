package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Icosahedron {

    public Icosahedron() {
    }

    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    // remember to pre calculate out X and Z
    public void DrawIcosahedron() {
        // Calculate the golden ratio
        float phi = (1.0f + (float) Math.sqrt(5.0)) / 2.0f;
        float a = 1.0f;
        float b = 1.0f / phi;

        // Define the vertices of the icosahedron
        Point4f vertices[] = {
                new Point4f(0, b, -a, 1), new Point4f(b, a, 0, 1),
                new Point4f(-b, a, 0, 1), new Point4f(0, b, a, 1),
                new Point4f(0, -b, a, 1), new Point4f(-a, 0, b, 1),
                new Point4f(0, -b, -a, 1), new Point4f(a, 0, -b, 1),
                new Point4f(a, 0, b, 1), new Point4f(-a, 0, -b, 1),
                new Point4f(b, -a, 0, 1), new Point4f(-b, -a, 0, 1)
        };

        // Define the faces of the icosahedron, each face consists of three vertices
        int[][] faces = {
                {0, 1, 2}, {3, 2, 1}, {3, 4, 5}, {3, 8, 4}, {0, 6, 7},
                {0, 9, 6}, {4, 10, 11}, {6, 11, 10}, {2, 5, 9}, {11, 9, 5},
                {1, 7, 8}, {10, 8, 7}, {3, 5, 2}, {3, 1, 8}, {0, 2, 9},
                {0, 7, 1}, {6, 9, 11}, {6, 10, 7}, {4, 11, 5}, {4, 8, 10}
        };

        GL11.glBegin(GL11.GL_TRIANGLES);
        for (int[] face : faces) {
            // Calculate the normal vector for the current face
            Vector4f v = vertices[face[1]].MinusPoint(vertices[face[0]]);
            Vector4f w = vertices[face[2]].MinusPoint(vertices[face[0]]);
            Vector4f normal = v.cross(w).Normal();
            GL11.glNormal3f(normal.x, normal.y, normal.z);

            // Draw the three vertices of the current face
            for (int i = 0; i < 3; i++) {
                GL11.glVertex3f(vertices[face[i]].x, vertices[face[i]].y, vertices[face[i]].z);
            }
        }
        GL11.glEnd();
    }
}

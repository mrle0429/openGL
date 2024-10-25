package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Octahedron {

	public Octahedron() {
	}
	
	public void DrawOctahedron() {
		// Define the vertices of the octahedron
		Point4f vertices[] = {
			new Point4f( 1.0f,  0.0f,  0.0f, 0.0f),
			new Point4f(-1.0f,  0.0f,  0.0f, 0.0f),
			new Point4f( 0.0f,  1.0f,  0.0f, 0.0f),
			new Point4f( 0.0f, -1.0f,  0.0f, 0.0f),
			new Point4f( 0.0f,  0.0f,  1.0f, 0.0f),
			new Point4f( 0.0f,  0.0f, -1.0f, 0.0f)
		};

		// Define the faces of the octahedron, each face consists of three vertices
		int[][] faces = {
			{0, 2, 4}, {0, 4, 3}, {0, 3, 5}, {0, 5, 2},
			{1, 2, 5}, {1, 5, 3}, {1, 3, 4}, {1, 4, 2}
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

package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Cube {

	public Cube() {
	}
	
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCube() {
        // Define the vertices of the cube
        Point4f vertices[] = {
            new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),
            new Point4f(-1.0f, -1.0f,  1.0f, 0.0f),
            new Point4f(-1.0f,  1.0f,  1.0f, 0.0f),
            new Point4f(-1.0f,  1.0f, -1.0f, 0.0f),
            new Point4f( 1.0f, -1.0f, -1.0f, 0.0f),
            new Point4f( 1.0f, -1.0f,  1.0f, 0.0f),
            new Point4f( 1.0f,  1.0f,  1.0f, 0.0f),
            new Point4f( 1.0f,  1.0f, -1.0f, 0.0f)
        };

        // Define the faces of the cube, each face consists of four vertices
        int[][] faces = {
            {0, 1, 2, 3}, // left
            {4, 7, 6, 5}, // right
            {0, 4, 5, 1}, // bottom
            {2, 6, 7, 3}, // top
            {0, 3, 7, 4}, // front
            {1, 5, 6, 2}  // back
        };

        // Start drawing the quadrilaterals that make up the faces of the cube
        GL11.glBegin(GL11.GL_QUADS);
        for (int face = 0; face < 6; face++) {
            Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v.cross(w).Normal();
            GL11.glNormal3f(normal.x, normal.y, normal.z);

            // Draw the four vertices of each face
            for (int i = 0; i < 4; i++) {
                GL11.glVertex3f(vertices[faces[face][i]].x, vertices[faces[face][i]].y, vertices[faces[face][i]].z);
            }
        }
        GL11.glEnd();
    }
	
	
	
}
 
	/*
	 
	 
}

	*/
	 

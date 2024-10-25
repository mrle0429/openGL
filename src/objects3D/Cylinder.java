package objects3D;

import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Cylinder {


    public Cylinder() {
    }

    // remember to use Math.PI isntead PI
    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    // Parameters:
    // - radius: the radius of the cylinder
    // - height: the height of the cylinder
    // - nSegments: the number of segments to approximate the cylinder
    public void DrawCylinder(float radius, float height, int nSegments) {
        GL11.glBegin(GL11.GL_TRIANGLES);

        // Draw the side faces of the cylinder
        for (int i = 0; i < nSegments; i++) {
            float theta = (float) (2.0f * Math.PI * i / nSegments);
            float nextTheta = (float) (2.0f * Math.PI * (i + 1) / nSegments);

            float x1 = (float) (radius * Math.cos(theta));
            float z1 = (float) (radius * Math.sin(theta));
            float x2 = (float) (radius * Math.cos(nextTheta));
            float z2 = (float) (radius * Math.sin(nextTheta));

            // Calculate and set the normal vector for lighting
            Vector4f normal = new Vector4f(x1, 0, z1, 0).Normal();
            GL11.glNormal3f(normal.x, normal.y, normal.z);

            // Draw the two triangles that form the side face
            GL11.glVertex3f(x1, -height / 2, z1);
            GL11.glVertex3f(x1, height / 2, z1);
            GL11.glVertex3f(x2, height / 2, z2);

            GL11.glVertex3f(x2, height / 2, z2);
            GL11.glVertex3f(x2, -height / 2, z2);
            GL11.glVertex3f(x1, -height / 2, z1);
        }

        // Draw the top and bottom faces of the cylinder
        for (int i = 0; i < nSegments; i++) {
            float theta = (float) (2.0f * Math.PI * i / nSegments);
            float nextTheta = (float) (2.0f * Math.PI * (i + 1) / nSegments);

            float x1 = (float) (radius * Math.cos(theta));
            float z1 = (float) (radius * Math.sin(theta));
            float x2 = (float) (radius * Math.cos(nextTheta));
            float z2 = (float) (radius * Math.sin(nextTheta));

            // Draw the top
            GL11.glNormal3f(0, 1, 0);
            GL11.glVertex3f(0, height / 2, 0);
            GL11.glVertex3f(x1, height / 2, z1);
            GL11.glVertex3f(x2, height / 2, z2);

            // Draw the bottom
            GL11.glNormal3f(0, -1, 0);
            GL11.glVertex3f(0, -height / 2, 0);
            GL11.glVertex3f(x2, -height / 2, z2);
            GL11.glVertex3f(x1, -height / 2, z1);
        }

        GL11.glEnd();
    }
}

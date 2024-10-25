package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Sphere {

    public Sphere() {
    }
    
    public void DrawSphere(float radius, float nSlices, float nSegments) {
        GL11.glBegin(GL11.GL_TRIANGLES);

        // Loop through each slice
        for (int i = 0; i < nSlices; i++) {
            // Calculate latitude angles and corresponding z and zr values for the current and next slice
            double lat0 = Math.PI * (-0.5 + (double) (i) / nSlices);
            double z0  = Math.sin(lat0);
            double zr0 = Math.cos(lat0);

            double lat1 = Math.PI * (-0.5 + (double) (i+1) / nSlices);
            double z1 = Math.sin(lat1);
            double zr1 = Math.cos(lat1);

            // Loop through each segment
            for (int j = 0; j < nSegments; j++) {
                // Calculate longitude angles and corresponding x and y values for the current and next segment
                double lng0 = 2 * Math.PI * (double) (j) / nSegments;
                double x0 = Math.cos(lng0);
                double y0 = Math.sin(lng0);

                double lng1 = 2 * Math.PI * (double) (j+1) / nSegments;
                double x1 = Math.cos(lng1);
                double y1 = Math.sin(lng1);

                // Define four vertices of the current quad
                Point4f v0 = new Point4f((float)(x0 * zr0), (float)(y0 * zr0), (float)z0, 1);
                Point4f v1 = new Point4f((float)(x0 * zr1), (float)(y0 * zr1), (float)z1, 1);
                Point4f v2 = new Point4f((float)(x1 * zr0), (float)(y1 * zr0), (float)z0, 1);
                Point4f v3 = new Point4f((float)(x1 * zr1), (float)(y1 * zr1), (float)z1, 1);

                // Draw the first triangle of the quad if not at the first slice
                if (i != 0) {
                    Vector4f normal = v0.MinusPoint(new Point4f(0,0,0,1)).Normal();
                    GL11.glNormal3f(normal.x, normal.y, normal.z);
                    GL11.glVertex3f(v0.x * radius, v0.y * radius, v0.z * radius);
                    normal = v2.MinusPoint(new Point4f(0,0,0,1)).Normal();
                    GL11.glNormal3f(normal.x, normal.y, normal.z);
                    GL11.glVertex3f(v2.x * radius, v2.y * radius, v2.z * radius);
                    normal = v1.MinusPoint(new Point4f(0,0,0,1)).Normal();
                    GL11.glNormal3f(normal.x, normal.y, normal.z);
                    GL11.glVertex3f(v1.x * radius, v1.y * radius, v1.z * radius);
                }

                // Draw the second triangle of the quad if not at the last slice
                if (i != (nSlices-1)) {
                    Vector4f normal = v2.MinusPoint(new Point4f(0,0,0,1)).Normal();
                    GL11.glNormal3f(normal.x, normal.y, normal.z);
                    GL11.glVertex3f(v2.x * radius, v2.y * radius, v2.z * radius);
                    normal = v3.MinusPoint(new Point4f(0,0,0,1)).Normal();
                    GL11.glNormal3f(normal.x, normal.y, normal.z);
                    GL11.glVertex3f(v3.x * radius, v3.y * radius, v3.z * radius);
                    normal = v1.MinusPoint(new Point4f(0,0,0,1)).Normal();
                    GL11.glNormal3f(normal.x, normal.y, normal.z);
                    GL11.glVertex3f(v1.x * radius, v1.y * radius, v1.z * radius);
                }
            }
        }
        
        GL11.glEnd();
    }
}

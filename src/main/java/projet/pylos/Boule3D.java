package projet.pylos;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Boule3D  {

    String couleurbase = "#FFFFFF";

    boolean objectMatrix[][][];
    float dx=0.001f;
    private float vertices[] = {
            0.0f+dx, 0.0f+dx, 0.0f+dx, // 0
            1.0f-dx, 0.0f+dx, 0.0f+dx, // 1
            1.0f-dx, 1.0f-dx, 0.0f+dx, // 2
            0.0f+dx, 1.0f-dx, 0.0f+dx, // 3

            0.0f+dx, 0.0f+dx, 1.0f-dx, // 4
            1.0f-dx, 0.0f+dx, 1.0f-dx, // 5
            1.0f-dx, 1.0f-dx, 1.0f-dx, // 6
            0.0f+dx, 1.0f-dx, 1.0f-dx  // 7
    };

    private float lineVertices[] = {
            0.0f, 0.0f, 0.0f, // 0
            1.0f, 0.0f, 0.0f, // 1
            1.0f, 1.0f, 0.0f, // 2
            0.0f, 1.0f, 0.0f, // 3

            0.0f, 0.0f, 1.0f, // 4
            1.0f, 0.0f, 1.0f, // 5
            1.0f, 1.0f, 1.0f, // 6
            0.0f, 1.0f, 1.0f,  // 7
    };

    private short[] indices = { 3, 1, 0, 3, 2, 1, 4, 5, 7, 7, 5, 6, 0, 1, 5, 5, 4, 0, 6, 2, 3, 7, 6, 3, 1, 2, 5, 6, 5, 2, 4, 3, 0, 3, 4, 7 };

    private short[] lineIndices = { 0, 1, 1, 2, 2, 3, 3, 0, 4, 5, 5, 6, 6, 7, 7, 4, 0, 4, 1, 5, 2, 6, 3, 7 };

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    private FloatBuffer lineIertexBuffer;
    private ShortBuffer lineIndexBuffer;

    String lineColor = "#FFFFFF";
    private FloatBuffer colorBuffer;
    private FloatBuffer colorLineBuffer;

    public Boule3D(){
        init();
        colorBuffer = null;
    }

    public Boule3D(String color) {
        init();
        this.couleurbase = color;
        colorBuffer = floatToFloatBuffer(convertColor(color, vertices.length + vertices.length / 3));
    }

    private void init() {
        vertexBuffer = floatToFloatBuffer(vertices);
        indexBuffer = shortToShortBuffer(indices);

        lineIertexBuffer = floatToFloatBuffer(lineVertices);
        lineIndexBuffer = shortToShortBuffer(lineIndices);

        colorLineBuffer = floatToFloatBuffer(convertColor(lineColor, vertices.length + vertices.length / 3));
        objectMatrix = createFalsMatrix(1);
        objectMatrix[0][0][0] = true;
    }


    public void draw(GL10 gl) {
        float	angleA, angleB;
        float	cos, sin;
        float	r1, r2;
        float	h1, h2;
        float	step = 2.0f;
        float[][] v = new float[32][3];
        ByteBuffer vbb;
        FloatBuffer vBuf;


        vbb = ByteBuffer.allocateDirect(v.length * v[0].length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vBuf = vbb.asFloatBuffer();

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        if(GameBoardPylos.currentPlayer  == TheApplication.player1) {
            //currentObject = currentObjectjoueur;
            gl.glColor4f(1000.5f, 0.5f, 1.0f, 1.0f);        }
        else {
            gl.glColor4f(0.5f, 2050f, 1.0f, 1.0f);
        }

        for (angleA = -90.0f; angleA <90.0f; angleA += step ) { // OPACITE DES BOULES
            int	n = 0;

            r1 = (float)Math.cos( angleA  * Math.PI / 180.0);
            r2 = (float)Math.cos(  (angleA + step ) * Math.PI / 180.0);// CHANGE SKIN


            h1 = (float)Math.sin(angleA * Math.PI / 180.0);
            h2 = (float)Math.sin((angleA + step ) * Math.PI / 180.0);

            // Fixed latitude, 360 degrees rotation to traverse a weft
            for (angleB = 0.0f; angleB <= 360.0f; angleB += step  ) {

                cos = (float)Math.cos(angleB * Math.PI / 180.0);
                sin = -(float)Math.sin(angleB * Math.PI / 180.0);

                v[n][0] = (r2 * cos );
                v[n][1] = (h2);
                v[n][2] = (r2 * sin);
                v[n + 1][0] = (r1 * cos);
                v[n + 1][1] = (h1);
                v[n + 1][2] = (r1 * sin);

                vBuf.put(v[n]);
                vBuf.put(v[n + 1]);

                n += 2;

                if(n>31){
                    vBuf.position(0);

                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
                    gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);

                    n = 0;
                    angleB -= step;
                }

            }
            vBuf.position(0);



            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
            gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

    }















    public boolean[][][] getObjectMatrix() {
        return objectMatrix;
    }

    public String getCouleurbase() {
        return couleurbase;
    }

    public int getXsize() {
        return getXsize(objectMatrix);
    }


    public int getYsize() {
        return getYsize(objectMatrix);
    }


    public int getZsize() {
        return getZsize(objectMatrix);
    }


    public void rotate(int axis) {
        switch(axis){
            case 1:
            case 'x':
                objectMatrix = rotateX(objectMatrix); break;
            case 2:
            case 'y':
                objectMatrix = rotateY(objectMatrix); break;
            case 3:
            case 'z':
                objectMatrix = rotateZ(objectMatrix); break;
        }
    }


    public boolean[][] rot(boolean[][] a) {
        boolean rotatedMatrix[][] = new boolean[a.length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++)
                rotatedMatrix[a.length - (j + 1)][i] = a[i][j];
        return rotatedMatrix;
    }





    public boolean[][][] align(boolean rotatedMatrix[][][]) {
        rotatedMatrix = alignI(rotatedMatrix);
        rotatedMatrix = alignJ(rotatedMatrix);
        rotatedMatrix = alignK(rotatedMatrix);
        return rotatedMatrix;
    }

    public boolean[][][] alignI(boolean rotatedMatrix[][][]) {
        int rNum = 0;
        for (int i = 0; i < rotatedMatrix.length; i++) {
            boolean remove = true;
            for (int k = 0; k < rotatedMatrix.length; k++){
                for (int j = 0; j < rotatedMatrix.length; j++)
                    if (rotatedMatrix[i][j][k] == true){
                        remove = false;
                        break;
                    }
                if(remove==false) break;
            }
            if (remove) rNum++;
            else break;
        }
        for (int i = rNum; i < rotatedMatrix.length; i++)
            for (int k = 0; k < rotatedMatrix.length; k++)
                for (int j = 0; j < rotatedMatrix.length; j++)
                    rotatedMatrix[i-rNum][j][k] = rotatedMatrix[i][j][k];

        for (int i = rotatedMatrix.length - 1; i > rotatedMatrix.length - 1 - rNum; i--)
            for (int k = 0; k < rotatedMatrix.length; k++)
                for (int j = 0; j < rotatedMatrix.length; j++)
                    rotatedMatrix[i][j][k] = false;
        return rotatedMatrix;
    }

    public boolean[][][] alignJ(boolean rotatedMatrix[][][]) {
        int rNum = 0;
        for (int j = 0; j < rotatedMatrix.length; j++) {
            boolean remove = true;
            for (int i = 0; i < rotatedMatrix.length; i++)
                for (int k = 0; k < rotatedMatrix.length; k++){
                    if (rotatedMatrix[i][j][k] == true){
                        remove = false;
                        break;
                    }
                    if(remove==false) break;
                }
            if (remove) rNum++;
            else break;
        }
        for (int j = rNum; j < rotatedMatrix.length; j++)
            for (int i = 0; i < rotatedMatrix.length; i++)
                for (int k = 0; k < rotatedMatrix.length; k++)
                    rotatedMatrix[i][j-rNum][k] = rotatedMatrix[i][j][k];

        for (int j = rotatedMatrix.length - 1; j > rotatedMatrix.length - 1 - rNum; j--)
            for (int i = 0; i < rotatedMatrix.length; i++)
                for (int k = 0; k < rotatedMatrix.length; k++)
                    rotatedMatrix[i][j][k] = false;
        return rotatedMatrix;
    }

    public boolean[][][] alignK(boolean rotatedMatrix[][][]) {
        int rNum = 0;
        for (int k = 0; k < rotatedMatrix.length; k++) {
            boolean remove = true;
            for (int i = 0; i < rotatedMatrix.length; i++){
                for (int j = 0; j < rotatedMatrix.length; j++)
                    if (rotatedMatrix[i][j][k] == true){
                        remove = false;
                        break;
                    }
                if(remove==false) break;
            }
            if (remove) rNum++;
            else break;
        }
        for (int k = rNum; k < rotatedMatrix.length; k++)
            for (int i = 0; i < rotatedMatrix.length; i++)
                for (int j = 0; j < rotatedMatrix.length; j++)
                    rotatedMatrix[i][j][k-rNum] = rotatedMatrix[i][j][k];

        for (int k = rotatedMatrix.length - 1; k > rotatedMatrix.length - 1 - rNum; k--)
            for (int i = 0; i < rotatedMatrix.length; i++)
                for (int j = 0; j < rotatedMatrix.length; j++)
                    rotatedMatrix[i][j][k] = false;

        return rotatedMatrix;
    }


    public void draw(GL10 gl, FloatBuffer vertexBuffer,
                     ShortBuffer indexBuffer, short[] indices) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    public void draw(GL10 gl, FloatBuffer vertexBuffer,
                     ShortBuffer indexBuffer, short[] indices, FloatBuffer colorBuffer) {

        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);

    }

    public void drawLines(GL10 gl, FloatBuffer vertexBuffer,
                          ShortBuffer indexBuffer, short[] indices, FloatBuffer colorBuffer) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glLineWidth(10);////////////////////////////////////////////////////////////////////// LARGEUR CASE
        gl.glDrawElements(GL10.GL_LINES, indices.length  ,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }

    public FloatBuffer floatToFloatBuffer(float[] vertices) {
        FloatBuffer vertexBuffer;
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        return vertexBuffer;
    }

    public ShortBuffer shortToShortBuffer(short[] indices) {
        ShortBuffer indexBuffer;
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
        return indexBuffer;
    }

    public float[] convertColor(String color, int colorArrayLength) {
        float colors[] = new float[colorArrayLength];
        float cR = Integer.valueOf(color.substring(1, 3), 16) / 255.0f;
        float cG = Integer.valueOf(color.substring(3, 5), 16) / 255.0f;
        float cB = Integer.valueOf(color.substring(5, 7), 16) / 255.0f;

        for (int i = 0; i < colorArrayLength; i += 4) {
            colors[i] = cR;
            colors[i + 1] = cG;
            colors[i + 2] = cB;
            colors[i + 3] = 1.0f;
        }
        return colors;
    }

    public boolean[][][] createFalsMatrix(int n) {
        boolean objectMatrix[][][] = new boolean[n][n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    objectMatrix[i][j][k] = false;
        return objectMatrix;

    }

    public int getXsize(boolean[][][] matrix) {
        int object_x_size = 0;
        for (int i = 0; i < matrix.length; i++) {
            boolean ok = false;
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    if (matrix[i][j][k] == true)
                        ok = true;
                }
            }
            if (ok) {
                object_x_size++;
            }
        }
        return object_x_size;
    }

    public int getYsize(boolean[][][] matrix) {
        int object_y_size = 0;
        for (int j = 0; j < matrix.length; j++) {
            boolean ok = false;
            for (int i = 0; i < matrix.length; i++) {
                for (int k = 0; k < matrix.length; k++) {
                    if (matrix[i][j][k] == true)
                        ok = true;
                }
            }
            if (ok) {
                object_y_size++;
            }
        }
        return object_y_size;
    }

    public int getZsize(boolean[][][] matrix) {
        int object_z_size = 0;
        for (int k = 0; k < matrix.length; k++) {
            boolean ok = false;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j][k] == true)
                        ok = true;
                }
            }
            if (ok) {
                object_z_size++;
            }
        }
        return object_z_size;
    }



    public boolean[][][] rotateX(boolean objectMatrix[][][]) {
        if(GameBoardPylos.getCurrentObjectX()+GameBoardPylos.getCurrentObject().getZsize()>=GameBoardPylos.getGridSize()){
            return objectMatrix;
        }
        boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
        for (int j = 0; j < objectMatrix.length; j++) {
            boolean temp[][] = new boolean[objectMatrix.length][objectMatrix.length];
            for (int i = 0; i < objectMatrix.length; i++)
                for (int k = 0; k < objectMatrix.length; k++)
                    temp[i][k] = objectMatrix[i][j][k];
            temp = rot(temp);
            for (int i = 0; i < objectMatrix.length; i++)
                for (int k = 0; k < objectMatrix.length; k++)
                    rotatedMatrix[i][j][k] = temp[i][k];
        }
        return align(rotatedMatrix);
    }

    public boolean[][][] rotateY(boolean objectMatrix[][][]) {
        if(GameBoardPylos.getCurrentObjectY()+GameBoardPylos.getCurrentObject().getZsize()>=GameBoardPylos.getGridSize()){
            return objectMatrix;
        }
        boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
        for (int i = 0; i < objectMatrix.length; i++) {
            boolean temp[][] = new boolean[objectMatrix.length][objectMatrix.length];
            for (int j = 0; j < objectMatrix.length; j++)
                for (int k = 0; k < objectMatrix.length; k++)
                    temp[j][k] = objectMatrix[i][j][k];
            temp = rot(temp);
            for (int j = 0; j < objectMatrix.length; j++)
                for (int k = 0; k < objectMatrix.length; k++)
                    rotatedMatrix[i][j][k] = temp[j][k];
        }
        return align(rotatedMatrix);
    }

    public boolean[][][] rotateZ(boolean objectMatrix[][][]) {
        if(GameBoardPylos.getCurrentObjectY()+GameBoardPylos.getCurrentObject().getXsize()>GameBoardPylos.getGridSize()){
            return objectMatrix;
        }
        if(GameBoardPylos.getCurrentObjectX()+GameBoardPylos.getCurrentObject().getYsize()>GameBoardPylos.getGridSize()){
            return objectMatrix;
        }
        boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
        for (int k = 0; k < objectMatrix.length; k++) {
            boolean temp[][] = new boolean[objectMatrix.length][objectMatrix.length];
            for (int i = 0; i < objectMatrix.length; i++)
                for (int j = 0; j < objectMatrix.length; j++)
                    temp[i][j] = objectMatrix[i][j][k];
            temp = rot(temp);
            for (int i = 0; i < objectMatrix.length; i++)
                for (int j = 0; j < objectMatrix.length; j++)
                    rotatedMatrix[i][j][k] = temp[i][j];
        }
        return align(rotatedMatrix);
    }

}
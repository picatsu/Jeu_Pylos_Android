package projet.pylos;

import javax.microedition.khronos.opengles.GL10;


import android.opengl.GLU;


public class OpenGlRenderer extends AbstractOpenGlRenderer {

    public OpenGlRenderer(){

        super();
    }
    @Override
    public void onDrawFrame(GL10 gl, boolean firstDraw) {
        GLU.gluLookAt(gl, GameBoardPylos.getCameraX(), GameBoardPylos.getCameraY(), GameBoardPylos.getCameraZ(), 0, 0, 0, 0, 0, 1);

        new Coords( GameBoardPylos.getGridSize(),GameBoardPylos.getGameHeight()).draw(gl );
        new Grid( GameBoardPylos.getGridSize()).draw(gl );


        if (firstDraw) newShpe();

        dropDown();
       // removeFullRows();
        printAllObjects(gl);
        printCurrentObject(gl);
    }

    private void dropDown() {
        if(GameBoardPylos.isEnd()) return;
        if (getOneSec() != 0) return;
        boolean ret = GameBoardPylos.setCurrentObjectPositionDown();
        if(GameBoardPylos.isDropFast())
            while(GameBoardPylos.setCurrentObjectPositionDown()){

            }
        if (!ret) {
            GameBoardPylos.savePositionToBoolMatrix(!ret);
            if (GameBoardPylos.checkEnd() == false) {
                newShpe();
            }
        }
    }

    private void removeFullRows() {
        GameBoardPylos.removeFullRows();
    }

    private void printAllObjects(GL10 gl) {
        for (int i = 0; i < GameBoardPylos.getGridSize(); i++)
            for (int j = 0; j < GameBoardPylos.getGridSize(); j++)
                for (int k = 0; k < GameBoardPylos.getGameHeight(); k++)
                    if (GameBoardPylos.getGameBoolMatrix()[i][j][k]) {
                        gl.glPushMatrix();
                        Boule3D ccc = new Boule3D(
                                GameBoardPylos.getGameColorMatrix()[i][j][k]);


                                  gl.glTranslatef(i, j, k);/////////////////////////////////////////////////////// ICI DECALAGE

                          ccc.draw(gl);
                        gl.glPopMatrix();
                    }
    }

    private void printCurrentObject(GL10 gl){
        gl.glPushMatrix();
        gl.glTranslatef(GameBoardPylos.getCurrentObjectX(), GameBoardPylos.getCurrentObjectY() , GameBoardPylos.getCurrentObjectZ() );
        GameBoardPylos.getCurrentObject().draw(gl);
        gl.glPopMatrix();
    }

    private void newShpe() {

        GameBoardPylos.setCurrentObject(new Boule3D());
        GameBoardPylos.setCurrentPosition(GameBoardPylos.getStartX(), GameBoardPylos.getStartY(), GameBoardPylos.getGameHeight());
    }




}

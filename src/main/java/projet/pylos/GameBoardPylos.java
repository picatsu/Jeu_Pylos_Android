package projet.pylos;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;

public class GameBoardPylos{



    private static float cameraR, cameraH;
    private static float cameraX, cameraY, cameraZ;

    private static int gameHeight;
    private static int gridSize;
    private static int startX, startY;
    public static int numeroetagecourant =0;

    public static Boule3D currentObjectjoueur;
    public static Boule3D currentObjectmachine ;

    public static Boule3D currentObject ;

    public static int currentObjectX, currentObjectY, currentObjectZ;

    private static boolean gameBoolMatrix[][][];
    private static String gameColorMatrix[][][];
    private static String gameColorMatrix2[][][];

    private static boolean dropFast;
    private static boolean end;
    public static Player currentPlayer;
    public static TheApplication app;

    public static void init(Context _c) {
        app = new TheApplication();
        currentPlayer = TheApplication.player1;

        currentObjectjoueur = new Boule3D(TheApplication.player1.getColor());
        currentObjectmachine = new Boule3D(TheApplication.player1.getColor());
        currentObject = currentObjectjoueur;

        gameHeight = 10;
        gridSize = 4;// Taille de la map
        restartGameBoolMatrix();
        setCamera(-65, 10);
        end = false;
        startX = 2;
        startY = 2;
    }


    public static void setCamera(float r, float h) {
        GameBoardPylos.cameraR = r;
        GameBoardPylos.cameraH = h;
        calculateCamera();
    }

    private static void calculateCamera() {
        GameBoardPylos.cameraX = 15 * (float) Math.cos(Math
                .toRadians(GameBoardPylos.cameraR));
        GameBoardPylos.cameraY = 15 * (float) Math.sin(Math
                .toRadians(GameBoardPylos.cameraR));
        GameBoardPylos.cameraZ = GameBoardPylos.cameraH;
    }

    public static float getCameraX() {
        calculateCamera();
        return cameraX;
    }

    public static float getCameraY() {
        calculateCamera();
        return cameraY;
    }

    public static float getCameraZ() {
        calculateCamera();
        return cameraZ;
    }

    public static float getCameraR() {
        return cameraR;
    }

    public static void setCameraR(float cameraR) {
        GameBoardPylos.cameraR = cameraR;
        calculateCamera();
    }

    public static float getCameraH() {
        return cameraH;
    }

    public static void setCameraH(float cameraH) {
        GameBoardPylos.cameraH = cameraH;
        calculateCamera();
    }


    public static void setGameBoolMatrix(int x, int y, int z) {
        if (gameBoolMatrix == null) {
            restartGameBoolMatrix();
        }
        gameBoolMatrix[x][y][z] = true;
    }

    private static void restartGameBoolMatrix() {
        int objectBuffer = 5;
        gameBoolMatrix = new boolean[gridSize][gridSize][gameHeight
                + objectBuffer];

        gameColorMatrix = new String[gridSize][gridSize][gameHeight
                + objectBuffer];


        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                for (int k = 0; k < gameHeight + objectBuffer; k++) {

                    gameBoolMatrix[i][j][k] = false;
                    gameColorMatrix[i][j][k] = "#FF00FF";
                }
    }

    public static boolean[][][] getGameBoolMatrix() {
        return gameBoolMatrix;
    }

    public static String[][][] getGameColorMatrix() {
        return gameColorMatrix;
    }

    public static void setGameColorMatrix(String[][][] gameColorMatrix) {
        GameBoardPylos.gameColorMatrix = gameColorMatrix;
    }

    public static int getGridSize() {
        return gridSize;
    }

    public static void setGridSize(int gridSize) {
        GameBoardPylos.gridSize = gridSize;
    }

    public static Boule3D getCurrentObject() {
        return currentObject;
    }

    public static void setCurrentObject(Boule3D currentObject) {
        GameBoardPylos.currentObject = currentObject;
    }

    public static void setCurrentPosition(int x, int y, int z) {
        currentObjectX = x;
        currentObjectY = y;
        currentObjectZ = z;
    }

    public static boolean setCurrentXPositionPos() {
        if (GameBoardPylos.isEnd())
            return false;


        if (currentObjectX + currentObject.getXsize()  < gridSize) {
            if (isAvailable(1))
                currentObjectX++;
            return true;
        }
        return false;
    }

    public static boolean setCurrentXPositionNeg() {
        if (GameBoardPylos.isEnd())
            return false;
        if (currentObjectX - 1 >= 0) {
            if (isAvailable(2))
                currentObjectX--;
            return true;
        }
        return false;
    }

    public static boolean setCurrentYPositionPos() {
        if (GameBoardPylos.isEnd())
            return false;

        if (currentObjectY + currentObject.getYsize()  < gridSize) {
            if (isAvailable(3))
                currentObjectY++;
            return true;
        }
        return false;
    }

    public static boolean setCurrentYPositionNeg() {
        if (GameBoardPylos.isEnd())
            return false;
        if (currentObjectY - 1 >= 0) {
            if (isAvailable(4))
                currentObjectY--;
            return true;
        }
        return false;
    }

    public static boolean isAvailable(int direction) {
        for (int i = 0; i < currentObject.getObjectMatrix().length; i++)
            for (int j = 0; j < currentObject.getObjectMatrix().length; j++)
                for (int k = 0; k < currentObject.getObjectMatrix().length; k++)
                    if (currentObject.getObjectMatrix()[i][j][k] == true)
                        switch (direction) {
                            case 1:
                                if (gameBoolMatrix
                                        [i + currentObjectX + 1]
                                        [j+ currentObjectY]
                                        [k + currentObjectZ]
                                        == true)

                                    return false;
                                break;
                            case 2:
                                if (gameBoolMatrix
                                        [i + currentObjectX - 1]
                                        [j+ currentObjectY]
                                        [k + currentObjectZ]
                                        == true)

                                    return false;
                                break;
                            case 3:
                                if (gameBoolMatrix
                                        [i + currentObjectX]
                                        [j + currentObjectY + 1]
                                        [k + currentObjectZ]
                                        == true)

                                    return false;
                                break;
                            case 4:
                                if (gameBoolMatrix
                                        [i + currentObjectX]
                                        [j+ currentObjectY - 1]
                                        [k + currentObjectZ]
                                        == true)

                                    return false;
                                break;
                        }


        return true;
    }

    public static boolean setCurrentObjectPositionDown() {
        if (currentObjectZ <= 0) {
            return false;
        }

        for (int i = 0; i < currentObject.getObjectMatrix().length; i++)
            for (int j = 0; j < currentObject.getObjectMatrix().length; j++)
                for (int k = 0; k < currentObject.getObjectMatrix().length; k++)
                    if (currentObject.getObjectMatrix()[i][j][k] == true)
                        if (gameBoolMatrix[i + currentObjectX][j
                                + currentObjectY][currentObjectZ - 1] == true) {
                            if (k != 0)
                                currentObjectZ--;

                            return false;
                        }

        currentObjectZ--;
        return true;
    }

    public static void savePositionToBoolMatrix(boolean state) {
        for (int i = 0; i < currentObject.getObjectMatrix().length; i++){
            for (int j = 0; j < currentObject.getObjectMatrix().length; j++){
                for (int k = 0; k < currentObject.getObjectMatrix().length
                        && k < gameHeight; k++) {
                    if ((k + currentObjectZ) < gameHeight
                            //&& currentObject.getObjectMatrix()[i][j][k] == true
                      ){
                        if(TheApplication.moteur.canPut(new Boule(currentPlayer.getColor(), 10), new Coordonne(currentObjectX, currentObjectY, currentObjectZ))) {
                            gameBoolMatrix[i + currentObjectX][j + currentObjectY][k + currentObjectZ] = state;
                            gameColorMatrix[i + currentObjectX][j + currentObjectY][k + currentObjectZ] = "#800000";

                            TheApplication.moteur.put(new Boule(currentPlayer.getColor(), 10), new Coordonne(currentObjectX, currentObjectY, currentObjectZ));
                            numeroetagecourant = currentObjectZ;
                            //System.out.println(app.moteur);

                        } else {

                            gameBoolMatrix[i + TheApplication.moteur.rudimentaryAI().getx()]
                                    [j + TheApplication.moteur.rudimentaryAI().gety()]
                                    [k + TheApplication.moteur.rudimentaryAI().getz()] = state;
                            TheApplication.moteur.put(new Boule(currentPlayer.getColor(), 10), new Coordonne(currentObjectX, currentObjectY, currentObjectZ));
                            numeroetagecourant = currentObjectZ;
                        }
                        System.out.println("AM I HERE? HELPPP MEE IM TRAPPED !!!!!!! ");
                        currentPlayer.decrementNumberOfBalls();
                        System.out.println(currentPlayer.getColor() + " number of balls left: " + currentPlayer.getNombreDeBoules());
                        if(currentPlayer == TheApplication.player1) {
                            //currentObject = currentObjectjoueur;
                            currentPlayer = TheApplication.player2;
                        }
                        else currentPlayer = TheApplication.player1;
                    }
                }
            }
        }

    }

    public static int getCurrentObjectX() {
        return currentObjectX;
    }

    public static void setCurrentObjectX(int currentObjectX) {
        GameBoardPylos.currentObjectX = currentObjectX;
    }

    public static int getCurrentObjectY() {
        return currentObjectY;
    }

    public static void setCurrentObjectY(int currentObjectY) {
        GameBoardPylos.currentObjectY = currentObjectY;
    }

    public static int getCurrentObjectZ() {
        return currentObjectZ;
    }

    public static void setCurrentObjectZ(int currentObjectZ) {
        GameBoardPylos.currentObjectZ = currentObjectZ;
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameHeight(int gameHeight) {
        GameBoardPylos.gameHeight = gameHeight;
    }

    public static boolean isEnd() {
        return end;
    }

    public static boolean checkEnd() {
        //end = gameBoolMatrix[startX][startY][gameHeight - 1];// CE QU4IL DE BASE
        end = gameBoolMatrix[0][0][3];
        if (end){
            if(TheApplication.player1.getNombreDeBoules() == 0 || TheApplication.player2.getNombreDeBoules() == 0){
                end = true;
                PYLOS_View.showfin();
            }
            PYLOS_View.scoremission1++;

            Log.d("GAME", "---END---");
        }
        return end;
    }

    public static int getStartX() {
        return startX;
    }

    public static void setStartX(int startX) {
        GameBoardPylos.startX = startX;
    }

    public static int getStartY() {
        return startY;
    }

    public static void setStartY(int startY) {
        GameBoardPylos.startY = startY;
    }

    public static boolean isDropFast() {
        boolean temp = dropFast;
        dropFast = false;
        return temp;
    }

    public static void setDropFast() {
        GameBoardPylos.dropFast = true;
    }

    public static boolean removeFullRows() {
        ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();
        for (int k = gameHeight; k >= 0; k--) {
            boolean remove = true;
            for (int i = 0; i < gridSize; i++)
                for (int j = 0; j < gridSize; j++)
                    if (gameBoolMatrix[i][j][k] == false)
                        remove = false;
            if (remove)
                rowsToRemove.add(k);
        }
        if (!rowsToRemove.isEmpty()) {
            removeRows(rowsToRemove);
            return true;
        }
        return false;
    }

    private static void removeRows(ArrayList<Integer> rowsToRemove) {
        for (Integer x : rowsToRemove) {
            for (int k = x; k < gameHeight; k++)
                for (int i = 0; i < gridSize; i++)
                    for (int j = 0; j < gridSize; j++)
                        if (x == gameHeight - 1){
                            gameBoolMatrix[i][j][k] = false;
                            gameColorMatrix[i][j][k] = "";
                        }else{
                            gameBoolMatrix[i][j][k] = gameBoolMatrix[i][j][k + 1];
                            gameColorMatrix[i][j][k] = gameColorMatrix[i][j][k + 1];
                        }
        }
    }
}

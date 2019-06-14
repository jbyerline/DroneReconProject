package com.dronerecon.ws;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Your Name Here :-)
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        // ##############################

        String areaID = request.getParameter("area_id");
        int xPOS = Integer.parseInt(request.getParameter("tilex"));
        int yPOS = Integer.parseInt(request.getParameter("tiley"));
        String totalcols = request.getParameter("totalcols");
        String totalrows = request.getParameter("totalrows");
        int r = Integer.parseInt(request.getParameter("r"));
        int g = Integer.parseInt(request.getParameter("g"));

        URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=" + areaID + "&tilex=" + xPOS + "&tiley=" + yPOS + "&r=" + r + "&g=" + g);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String strTemp = "";
        String sServiceReturnJson = "";
        while (null != (strTemp = br.readLine())) {
            sServiceReturnJson += strTemp;
        }


        //url.openConnection();

        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        // ##############################

        String sDirection = "right";

        if (yPOS % 2 == 1) {

            sDirection = "left";

        }


        // ##############################
        // 3. Calculate next drone move.
        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################

        //Even number of rows
        //if (Integer.parseInt(totalrows) % 2 == 0){

            //Current row is even (might be last row)
            //if(yPOS % 2 == 0){

        //Not last column
        if(xPOS != Integer.parseInt(totalcols) - 1 && sDirection.equals("right") ){

            //Move right
            xPOS = xPOS + 1;

        }

        else if(xPOS != 0 && sDirection.equals("left") ){

            //Move left
            xPOS = xPOS - 1;

        }

        //In last column
        else{

            //Don't move down
            if(yPOS == Integer.parseInt(totalrows) - 1){

                sDirection = "stop";

            }

            //Move down
            else {

                yPOS = yPOS + 1;

                /*

                //Change direction
                if (sDirection.equals("right")) {

                    sDirection = "left";

                }

                else {

                    sDirection = "right";

                }

                */

            }

        }

            //}

            //Current row is odd (is not last row)
            //else{



           //}

        //}
        //else{

        //}

        /*

        for(int rows = 1; rows <= Integer.parseInt(totalrows); rows++) {

            yPOS = rows;

            //odd

            if( rows % 2 != 0){

                for (int col = 1; col <= Integer.parseInt(totalcols); col++){

                    xPOS = col;


                }
            }
            //even
            else if( rows % 2 == 0){

                for (int col = Integer.parseInt(totalcols); col >= 1; col--){

                    xPOS = col;

                }

            }

        }

        sDirection = "stop";

        */


        // ##############################
        // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
        // ##############################


        String jsonThing = "{\"area_id\":\"" + areaID + "\", \"nextTileX\":\"" + xPOS + "\", \"nextTileY\":\"" + yPOS +  "\", \"direction\":\"" + sDirection + "\"}";

        out.println(jsonThing);

    }
}


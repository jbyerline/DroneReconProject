package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PortalDBService extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        String areaId = request.getParameter("area_id");
        int tilex = Integer.parseInt(request.getParameter("tilex"));
        int tiley = Integer.parseInt(request.getParameter("tiley"));
        int r = Integer.parseInt(request.getParameter("r"));
        int g = Integer.parseInt(request.getParameter("g"));

        DBManager man = new DBManager();

        man.DBLocation = System.getProperty("catalina.base") + "/webapps/dronereconportal/db/" + man.DBLocation;

        man.insertAreaGridTile(areaId, tilex, tiley, r, g);

        out.println("{\"success\":true}");
    }
}


<%@ page import="com.dronerecon.ws.AreaGridTile" %>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList" %>

<html>

  <body>
	
	
      <br />
      <h2 style="text-align:center;background-color:gray;color:white">DRONE RECON</h2>
      
      <% DBManager man = new DBManager();%>
      
      <% man.DBLocation = System.getProperty("catalina.base") + "/webapps/dronereconportal/db/" + man.DBLocation;%>
      
      <% ArrayList<AreaGridTile> list = (ArrayList<AreaGridTile>)man.readAreaGridTiles(request.getParameter("area_id")).clone();%>
      
      <%
      
      
      
      AreaGridTile tempAGT = new AreaGridTile();
      
      tempAGT = list.get(0);
      
      int rMax = tempAGT.r;
      int gMax = tempAGT.g;
      int xG = tempAGT.x;
      int yG = tempAGT.y;
      int xR = tempAGT.x;
      int yR = tempAGT.y;
      
      
      
      for (int i = 1; i < list.size(); i++){
      
      		AreaGridTile temp = list.get(i);
      		
      		if( temp.g > gMax){
      		
      		gMax = temp.g;
      		xG = temp.x;
      		yG = temp.y;
      		
      		}
      		
      		if( temp.r > rMax){
      		
      		rMax = temp.r;
      		xR = temp.x;
      		yR = temp.y;
      		
      		}
      
      }
      
      out.println("Highest R-Value = " + xR + ", " + yR);
      
      out.println("Highest G-Value = " + xG + ", " + yG);
      %>
      
  </body>
</html>
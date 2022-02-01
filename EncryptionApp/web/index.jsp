<%-- Document   : index--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" 
        content="text/html; charset=UTF-8">
  <title>Encryption App</title>
 </head>
 <body>
  <h1>Encryption Filter</h1>
  <form method="POST">
   Write Msg: <textarea name="strings" 
           rows="3" cols="50"></textarea><br/>
   <input type="submit" value="Encrypt Data" /> 
   <input type="reset" value="Reset Data" />
  </form>
 </body>
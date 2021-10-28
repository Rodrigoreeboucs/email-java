<!DOCTYPE html>
<%@page import="br.com.fabrica.model.Cliente"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script>
function confirma(pi){
	
	if(window.confirm("Voce tem certeza que deseja excluir?")){
	location.href="cliente?acao=exc&i=" + pi;
	}
}

</script>


</head>
<body>

<div>

<%
Object msg = request.getAttribute("msg");
if (msg!=null) {
	String msgStr = (String)msg;
	out.print(msg);
}

Cliente cli = (Cliente) request.getAttribute("cli");

Object iCli = request.getAttribute("iCli");

%>
</div>


<form method="post" action="cliente">
     <input type="hidden" name="i" value="<%=iCli%>">
     e-mail: 
     <input type="text" value="<%=cli.getEmail()%>"  name="email"/>
     <input type="submit" value="Save">
     
<table border="1">  

    <tr>
      <th> E-mail</th>
      <th> Ação</th>
      
    </tr>  

<c:set var="i" value="0"/>
<c:forEach items="${requestScope.lista}" var="c">
   <tr>
   <td>
    ${c.email}
   </td>
   
   <td> 	
	        <a href="javascript:confirma(${i})" >  excluir </a> /
	        <a href="cliente?i=${i}&acao=edit"> Editar</a><br/>
	</td>
	
	</tr>
	<c:set var="i"  value="${i+1}"/>
</c:forEach>	

</table> 


</form>
</body>
</html>
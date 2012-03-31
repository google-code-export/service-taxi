<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link type="text/css" rel="stylesheet" href="../css/frame.css"/>
		<link type="text/css" rel="stylesheet" href="../css/foo.css"/> 
		<title>ServiceTaxi</title>
	</head>
	<body id="corpo">
		<div id="geral">
			
    	  	<div id="topo"><%@ include file="/topo.jsp" %></div>
    	  	<div id="menu"><%@ include file="/menu.jsp" %></div> 
      		
      		<div id="conteudo">
				<div id="esquerda"> 
					<div id="info"><%@ include file="/info.jsp" %></div> 
				</div>
	
	      		<div id="sub-conteudo"><jsp:include page="conteudo.jsp"/></div> 
	      		
				<div id="direita"> 
					<div id="top-filme"><%@ include file="/topFilme.jsp" %></div>
					<div id="top-usuario"><%@ include file="/topUsuario.jsp" %></div>
				</div>
			</div>
			
	      	<div id="rodape"><%@ include file="/rodape.jsp" %></div>
    	</div>
	</body>
</html>
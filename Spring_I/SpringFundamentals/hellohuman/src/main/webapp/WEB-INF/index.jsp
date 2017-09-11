<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome!</title>
	<style>
	#main{
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	</style>
</head>
<body>
<div id='main'>
<h1>Hello <c:out value="${name}"/>!</h1>
<p>Welcome to SpringBoot!</p>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hash Demo</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<%
		String[] values = (String[]) request.getAttribute("values");
		
		if (values != null) {
			String password = values[0];
			String salt = values[1];
			String hpass = values[2];
			String dbpass = values[3];
			
			String pass = (String) request.getAttribute("pass");
	%>
	<div class="container container-table">
		<div class="row vertical-center-row">
			<div class="col-md-12">
				<%
					if (pass.equals("1")) {
				%>
				<div class="alert alert-success">
  					<strong>Login Success!</strong>
				</div>
				<%
					} else {
				%>
				<div class="alert alert-danger">
  					<strong>Login Fail!</strong>
				</div>
				<%
					}
				%>
				<div class="panel panel-default">
			   		<div class = "panel-heading">
			   			<h3 class="panel-title">Login</h3>
				   	</div>
				   	
				   	<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered">
								<thead>
									<tr>
										<th></th>
										<th>Value</th>
									</tr>
								</thead>
								
								<tbody>
									<tr>
										<td>Password (From input)</td>
										<td><%=password %></td>
									</tr>
									
									<tr>
										<td>Salt (From DB)</td>
										<td><%=salt %></td>
									</tr>
									
									<tr>
										<td>Hashed password (From input)</td>
										<td><%=hpass %></td>
									</tr>
									
									<tr>
										<td>Hashed password (From DB)</td>
										<td><%=dbpass %></td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<a class="btn btn-default pull-right" href="index.html" role="button">OK</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>
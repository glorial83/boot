<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <script>
	    $().ready(function(){
			$("#dbtest").click(function(e){
				$.ajax({
	        		url: "/boot/getsession",
	                type: "POST",
	                dataType: "json",
	                contentType: "application/json",
	                async: false,
	                success: function (data) {
	                	console.dir(data);
	                	$("#errMsg").text($("#errMsg").text() + '\r\n' + data.sessionId +'\r\n');
	                },
	                error: function(err){
	                	console.dir(err);
	                }
	            });
	    	});

			$("#afterlogin2").click(function(e){
				location.href='/boot/afterlogin2';
			});
	    });
    </script>
  </head>
  <body>
	<div class="container">
		<h3 class="form-title">서버세팅 테스트용</h3>
		<div class="form-group">
			<div class="col-md-9">
				<div class="form-group col-md-offset-9">
					<button type="submit" class="btn btn-success pull-center" name="dbtest" id="dbtest">DB서버테스트</button>
					<button type="submit" class="btn btn-success pull-center" name="afterlogin2" id="afterlogin2">afterlogin2</button>
				</div>
				<div class="form-group col-md-offset-9">
					<button type="submit" class="btn btn-success pull-center" name="sardine" id="sardine">sardine</button>
				</div>
			</div>
		</div>
		<div class="form-group">
               <span class="errMsg" id="errMsg"></span>
           </div>
	</div>
</body></html>
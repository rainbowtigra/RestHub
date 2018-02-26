<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7" style="background: white">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="panel-heading"> <strong class="">Register</strong></div>
                        <form:form method="POST" class="form-horizontal" action="/registration" modelAttribute="registration">
        <div class="form-group">
            <div class="col-sm-9">
                <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
                <input type="email" class="form-control" id="inputEmail3"
                       placeholder="Email"  path="email"  required="">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9">
                <label for="inputPassword3" class="col-sm-3 control-label">Password</label>
                <input type="password" class="form-control" id="inputPassword3" path="password" placeholder="Password"name="password" required="">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9">
                <label for="inputName3" class="col-sm-3 control-label">Name</label>
                <input type="text" class="form-control" id="inputName3" path="name" placeholder="Name" name="password" required="">
            </div>
        </div>
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-success">Register</button>
            </div>
        </div>
    </form:form>
    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<style>
    body {
        background: url('/images/sezar.jpg') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }

    .panel-default {
        opacity: 0.9;
        margin-top:30px;
        background: white;
        height: 70%;
    }
    .form-group.last {
        margin-bottom:0px;
    }
</style>

</body>
</html>

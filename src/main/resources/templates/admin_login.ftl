<html>
 <#include "common/header.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center text-success">
               管理员登录页面
            </h3>
            <form role="form" method="post" action="/sell/seller/admin/dologin">
                <div class="form-group">
                    <label for="exampleInputEmail1">管理员用户名</label><input type="text" name="username" class="form-control" id="exampleInputEmail1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">管理员密码</label><input type="password" name="password" class="form-control" id="exampleInputPassword1" />
                </div>
                <button type="submit" class="btn btn-default btn-primary btn-block active">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<html>
 <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--sidebar-->
  <#include "../common/nav.ftl">
<#--content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form"   action="/sell/seller/category/save" method="post">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control"
                                   value="${(category.categoryName)!""}"
                            />
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input name="categoryType" type="number" class="form-control"
                                   value="${(category.categoryType)!""}"
                            />
                        </div>
                        <input type="hidden" name="categoryId" value="${(category.categoryId)!""}"/>
                        <input type="submit" class="btn btn-default" value="提交"></input>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

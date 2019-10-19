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
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>
                                类目Id
                            </th>
                            <th>
                                名称
                            </th>
                            <th>
                                type
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th colspan="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                <#list categoryList.content as category>
                <br>
                <tr class="success">
                    <td>
                        ${category.categoryId}
                    </td>
                    <td>
                        ${category.categoryName}
                    </td>
                    <td>
                        ${category.categoryType}
                    </td>
                    <td>
                        ${category.createTime}
                    </td>
                    <td>
                        ${category.updateTime}
                    </td>
                    <td>
                        <a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
            <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
            <#else>
                    <li>
                        <a href="/sell/seller/category/list?page=${currentPage-1}"> 上一页</a>
                    </li>
            </#if>
<#list 1..categoryList.getTotalPages() as index>
    <#if currentPage==index>
                    <li class="disabled">
                        <a href="#"> ${index}</a>
                    </li>
    <#else>
                    <li>
                        <a href="/sell/seller/category/list?page=${index}"> ${index}</a>
                    </li>
    </#if>
</#list>
 <#if currentPage gte categoryList.getTotalPages()>
                <li class="disabled"><a href="#">下一页</a></li>
 <#else>
 <li><a href="/sell/seller/category/list?page=${currentPage+1}">下一页</a></li>
 </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

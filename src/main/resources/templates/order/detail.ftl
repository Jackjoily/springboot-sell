<html>
 <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>
                            ${dtos.orderId}
                            </td>
                            <td>
                            ${dtos.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                <#list dtos.orderDetailList as detail>
                <tr class="success">
                    <td>
                        ${detail.productId}
                    </td>
                    <td>
                        ${detail.productName}
                    </td>
                    <td>
                        ${detail.productPrice}
                    </td>
                    <td>
                        ${detail.productQuantity}
                    </td>
                    <td>
                        ${detail.productPrice*detail.productQuantity}
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
            <#if dtos.getOrderStatusEnum().msg =="新订单">
                <a href="/sell/seller/order/finish?orderId=${dtos.orderId}"  type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${dtos.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
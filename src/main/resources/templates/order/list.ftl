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
                                订单Id
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>
                                金额
                            </th>
                            <th>
                                订单状态
                            </th>
                            <th>
                                支付状态
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th colspan="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                <#list dtos.content as orderDto>
                <br>
                <tr class="success">
                    <td>
                        ${orderDto.orderId}
                    </td>
                    <td>
                        ${orderDto.buyerName}
                    </td>
                    <td>
                        ${orderDto.buyerPhone}
                    </td>
                    <td>
                        ${orderDto.buyerAddress}
                    </td>
                    <td>
                        ${orderDto.orderAmount}
                    </td>
                    <td>
                        ${orderDto.getOrderStatusEnum().msg}
                    </td>
                    <td>
                        ${orderDto.getPayStatusEnum().msg}
                    </td>
                    <td>
                        ${orderDto.createTime}
                    </td>
                    <td>
                        <a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a>
                    </td>
                    <td>
                        <#if orderDto.getOrderStatusEnum().msg !="已取消">
                            <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            <#--分页组件-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
            <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
            <#else>
                    <li>
                        <a href="/sell/seller/order/list?page=${currentPage-1}"> 上一页</a>
                    </li>
            </#if>
<#list 1..dtos.getTotalPages() as index>
    <#if currentPage==index>
                    <li class="disabled">
                        <a href="#"> ${index}</a>
                    </li>
    <#else>
                    <li>
                        <a href="/sell/seller/order/list?page=${index}"> ${index}</a>
                    </li>
    </#if>
</#list>
 <#if currentPage gte dtos.getTotalPages()>
                <li class="disabled"><a href="#">下一页</a></li>
 <#else>
 <li><a href="/sell/seller/order/list?page=${currentPage+1}">下一页</a></li>
 </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button type="button" onclick="javascript:document.getElementById('notice').pause();" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button  onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>
<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg"/>
</audio>
<script>
    var websocket = null;
    if ('WebSocket' in
            window
    ) {
        websocket = new WebSocket('ws://127.0.0.1:8080/sell/webSocket');
    }
    else {
        alert("该浏览器不支持websocket");
    }
    websocket.onopen = function (event) {
        console.log('建立连接');
    }
    websocket.onclose = function (event) {
        console.log('连接关闭');
    }
    websocket.onmessage = function (event) {
        console.log('收到消息' + event.data);
        //收到消息之后，弹窗提醒，播放音乐
        $("#myModal").modal('show');
        document.getElementById('notice').play();
    }
    websocket.onerror = function (event) {
        alert("websocket通信发生错误");
    }
    window.onbeforeunload = function (ev) {
        websocket.close();
    }
</script>
</body>
</html>

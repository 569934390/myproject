<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<br>
<title>html5简介</title>
<meta charset="UTF-8" />
<script>
    function openUrl(url){
        window.open(window.location.href+"/"+url);
    }
</script>

<br>
语义化:</br>
<a href="javascript:openUrl('semantic')">新增标签</a></br>
<a href="javascript:openUrl('form')">增强表单控件</a></br>
<a href="javascript:openUrl('microdata')">微数据</a></br></br>

离线存储:</br>
<a href="javascript:openUrl('storage')">离线存储</a></br>
<a href="javascript:openUrl('storage/appcache_offline.html')">离线缓存</a></br></br>

拖放与文件处理:</br>
<a href="javascript:openUrl('draggable')">拖放与文件处理</a></br></br>

连接:</br>
<a href="javascript:openUrl('websockets')">websocket</a></br>
<a href="javascript:openUrl('WebRTC')">WebRTC</a></br></br>
多媒体:</br>
<a href="javascript:openUrl('video')">video</a></br></br>

三维、图形与特效:</br>
<a href="javascript:openUrl('canvas')">canvas</a></br></br>

性能与集成:</br>
<a href="javascript:openUrl('performance')">xmlhttprequest2</a></br>
<a href="javascript:openUrl('performance/postMessage.html')">postMessage</a></br>
<a href="javascript:openUrl('performance/webworker.html')">Web Workers</a></br>
<a href="javascript:openUrl('performance/data.html')">Custom data-* attributes</a></br>


</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="ft" class="qing_foot cl"> 
   <div class="wp cl"> 
    <div id="flk" class="z"> 
     <p>
     <a href="${path}/common/topcorm.do?ismobile=1&pathlocation=${requestScope['javax.servlet.forward.request_uri']}">触屏版</a><span class="pipe">|</span><a href="${path}/common/topcorm.do?ismobile=0&pathlocation=${requestScope['javax.servlet.forward.request_uri']}">电脑版</a>
      </p> 
    </div> 
    <div id="frt" class="y"> 
     <p>技术支持 <a href="http://bbs.javaee.cc" target="_blank">JAVAEE</a> <em>V2.0</em> &copy; 2016-2017
     <script src="https://s95.cnzz.com/z_stat.php?id=1261861747&web_id=1261861747" language="JavaScript"></script>
     </p> 
    </div> 
   </div> 
  </div> 
  <div id="scrolltop" class="js_scrolltop"> 
   <a title="返回顶部" class="scrolltopa" id="scrolltopa" style="display: none;"> <b>返回顶部</b> </a> 
  </div> 
  <script type="text/javascript">
  function updateuseronlinetime(){
	  $.ajax({
		   type: "POST",
		   url: "${path}/common/updateuseronlinetime.do",
		   async: true,
		   success: function(data){
		   }
	 });
  }
  setInterval("updateuseronlinetime()",30000);
</script>
<%@ include file="/WEB-INF/inc/commonfoot.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

좌측메뉴
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
  <title>Fancytree - Example</title>

  <script src="<%=request.getContextPath()%>/resources/js/lib/jquery.js"></script>
  <link href="<%=request.getContextPath()%>/resources/js/src/skin-win8/ui.fancytree.css" rel="stylesheet">
  <script src="<%=request.getContextPath()%>/resources/js/src/jquery-ui-dependencies/jquery.fancytree.ui-deps.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/src/jquery.fancytree.js"></script>

  <!-- (Irrelevant source removed.) -->

  <script type="text/javascript">
    $(function(){
      // using default options
      $("#tree").fancytree();
    });
  </script>
</head>

<body class="example">

  <div>
    <label for="skinswitcher">Skin:</label> <select id="skinswitcher"></select>
  </div>
  <div id="tree">
    <ul id="treeData" style="display: none;">
<!-- -------------------------------------------------------- -->     
      <li id="id1" class="folder">dummyDynamic
      	<ul>
      		<li id="id1.1" class="folder">Java Resources
      		<li id="id1.2" class="folder">src
      		<li id="id1.3" class="folder">webapp
      	</ul>      	
<!-- -------------------------------------------------------- -->      	
	  <li id="id2" class="folder">dummyProject
	    <ul>
      		<li id="id2.1" class="folder">Java Resources
      		<li id="id2.1" class="folder">WebContent
	      		<ul>
	              <li id="id2.1.1" class="folder">META-INF
	              <li id="id2.1.2" class="folder">WEB-INF
	              <li id="id2.1.3">index.html
	            </ul>
      	</ul>
<!-- -------------------------------------------------------- --> 
      <li id="id3" class="folder">dummyStandalone
        <ul>
          <li id="id3.1" class="folder">src/main/java
            <ul>
              <li id="id3.1.1" class="folder">kr.or.ddit
              	<ul>
              		<li id="id3.1.1.1">HelloMaven.java
              	</ul>
            </ul>
          <li id="id3.2" class="folder">src/main/resources
          <li id="id3.3" class="folder">src/test/resources
          <li id="id3.4" class="folder">src/test/resources
          <li id="id3.5" class="folder">src
          <li id="id3.6" class="folder">target
        </ul>
<!-- -------------------------------------------------------- -->        
      <li id="id4" class="folder">dummyTomcat7
        <ul>
          <li id="id4.1" class="folder">Java Resources
          <li id="id4.2" class="folder">WebContent
        </ul>
<!-- -------------------------------------------------------- --> 
      <li id="id5" class="folder">languageLab
        <ul>
          <li id="id5.1" class="folder">src
          <li id="id5.2" class="folder">res
          <li id="id5.2" class="folder">lib
        </ul>
<!-- -------------------------------------------------------- -->         
      <li id="id6" class="folder">Servers
        <ul>
          <li id="id6.1" class="folder">Tomcat v8.5 Server at localhost
        </ul>
<!-- -------------------------------------------------------- -->         
      <li id="id7" class="folder">webStudy01
        <ul>
          <li id="id7.1" class="folder">Java Resources
          <li id="id7.2" class="folder">webapp
        </ul>
<!-- -------------------------------------------------------- -->         
    </ul>
  </div>

  <!-- (Irrelevant source removed.) -->
</body>
</html>
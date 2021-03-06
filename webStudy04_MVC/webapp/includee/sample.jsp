<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
  <title>Fancytree - Example</title>

  <script src="${pageContext.request.contextPath }/resources/js/lib/jquery.js"></script>
  <link href="${pageContext.request.contextPath }/resources/js/src/skin-win8/ui.fancytree.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath }/resources/js/src/jquery-ui-dependencies/jquery.fancytree.ui-deps.js"></script>
  <script src="${pageContext.request.contextPath }/resources/js/src/jquery.fancytree.js"></script>

  <!-- (Irrelevant source removed.) -->

  <script type="text/javascript">
    $(function(){
      // using default options
      $("#tree").fancytree();
    });
  </script>
</head>

<body class="example">
  <h1>Example: Default</h1>
  <div class="description">
    This tree uses default options.<br>
    It is initialized from a hidden &lt;ul> element on this page.
  </div>
  <div>
    <label for="skinswitcher">Skin:</label> <select id="skinswitcher"></select>
  </div>
  <div id="tree">
    <ul id="treeData" style="display: none;">
      <li id="id1" title="Look, a tool tip!">item1 with key and tooltip
      <li id="id2">item2
      <li id="id3" class="folder">Folder <em>with some</em> children
        <ul>
          <li id="id3.1">Sub-item 3.1
            <ul>
              <li id="id3.1.1">Sub-item 3.1.1
              <li id="id3.1.2">Sub-item 3.1.2
              <li id="id3.1.3">Sub-item 3.1.3
            </ul>
          <li id="id3.2">Sub-item 3.2
            <ul>
              <li id="id3.2.1">Sub-item 3.2.1
              <li id="id3.2.2">Sub-item 3.2.2
            </ul>
        </ul>
      <li id="id4" class="expanded">Document with some children (expanded on init)
        <ul>
          <li id="id4.1"  class="active focused">Sub-item 4.1 (active and focus on init)
            <ul>
              <li id="id4.1.1">Sub-item 4.1.1
              <li id="id4.1.2">Sub-item 4.1.2
            </ul>
          <li id="id4.2">Sub-item 4.2
            <ul>
              <li id="id4.2.1">Sub-item 4.2.1
              <li id="id4.2.2">Sub-item 4.2.2
            </ul>
        </ul>
    </ul>
  </div>

  <!-- (Irrelevant source removed.) -->
</body>
</html>
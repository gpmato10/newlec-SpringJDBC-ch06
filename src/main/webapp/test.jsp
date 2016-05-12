<%--
  Created by IntelliJ IDEA.
  User: dw
  Date: 2016. 5. 10.
  Time: 오전 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<%--<form action="test" method="post">--%>
    <%--<input type="text" name="file">--%>
    <%--<input type="text" name="file">--%>
    <%--<input type="submit" value="send">--%>
<%--</form>--%>
<%----%>
<form action="/222.htm" method="post" enctype="multipart/form-data">
    <div id="notice-article-detail" class="article-detail margin-large" >
        <dl class="article-detail-row">
            <dt class="article-detail-title">
                제목
            </dt>
            <dd class="article-detail-data">
                &nbsp;<input name="title"/>
            </dd>
        </dl>

        <dl class="article-detail-row">
            <dt class="article-detail-title">
                첨부파일
            </dt>
            <dd class="article-detail-data">
                <%--&nbsp;<input type="file" id="txtFile" name="file" />--%>
                &nbsp;<input type="file" name="file[0]" />
            </dd>
        </dl>
        <dl class="article-detail-row">
            <dt class="article-detail-title">
                첨부파일
            </dt>
            <dd class="article-detail-data">
                <%--&nbsp;<input type="file" id="txtFile" name="file" />--%>
                &nbsp;<input type="file" name="file[1]" />
            </dd>
        </dl>

        <div class="article-content" >
            <textarea id="txtContent" class="txtContent" name="content"></textarea>
        </div>

    </div>
    <p class="article-comment margin-small">
        <input class="btn-save button" type="submit" value="저장" />
        <a class="btn-cancel button" href="notice.jsp">취소</a>
    </p>
</form>
</body>
</html>

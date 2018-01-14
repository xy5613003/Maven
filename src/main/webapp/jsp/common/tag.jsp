<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="resources.messages" var="messagesBundle"/> <!-- 加载资源包 resource路径下 -->
<c:set var="baseurl" value="${pageContext.request.contextPath}/"></c:set>
<%@ page  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${p.dataList}" var="o">
  ${o.simplePinyin}_${o.corpName}_${o.partyId}
</c:forEach>

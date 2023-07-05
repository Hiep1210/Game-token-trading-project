<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <!-- Sidebar Category -->
    <details class="sidebar-category">
        <summary>User Profile</summary>
        <ul class="nopadding">
            <a href="UserProfileController">
                <li>Profile</li>
            </a>
            <a href="topUpRequest.jsp">
                <li>Top up</li>
            </a>
            <a href="ChangePassword.jsp">
                <li>Change Password</li>
            </a>
            <a href="#">
                <li>Create auction</li>
            </a>
        </ul>
    </details>
    <!-- Sidebar Category -->
    <c:if test="${(sessionScope.user != null) && (sessionScope.user.roleid eq 2)}">  
        <details class="sidebar-category">
            <summary>Site Mananage</summary>

            <a href="GetPaymentRequestController">
                <li>Process top up request</li>
            </a>
            <a href="sendAdminNotification.jsp">
                <li>Send notification to all users</li>
            </a>
            <a href="GetProcessItemController">
                <li>Process item</li>
            </a>
            <a href="ListUser.jsp">
                <li>View user list</li>
            </a>
        </details>
    </c:if> 
    <!-- Sidebar Category -->
    <details class="sidebar-category">
        <summary>History</summary>
    </details>
</div>

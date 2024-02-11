<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>State Information Result - ${stateName}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            text-align: center;
        }
        img {
            max-width: 100%;
            height: auto;
        }
        .container {
            margin: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>State Information for ${stateName}</h2>

    <c:if test="${not empty population}">
        <p>Population: ${population}</p>
    </c:if>

    <c:if test="${not empty stateFlagUrl}">
        <p>State Flag:</p>
        <img src="${stateFlagUrl}" alt="State Flag of ${stateName}">
    </c:if>

    <c:if test="${not empty stateNickname}">
        <p>State's Nickname: ${stateNickname}</p>
    </c:if>

    <c:if test="${not empty stateMotto}">
        <p>State's Motto: ${stateMotto}</p>
    </c:if>

    <c:if test="${not empty fetchAnthem}">
        <p>State's ${fetchAnthem}</p>
    </c:if>

    <c:if test="${not empty fetchMap}">
        <img src="${fetchMap}" alt="State Flag of ${fetchMap}">
        <p>Map of the United States with ${stateName} highlighted</p>
    </c:if>

    <c:if test="${not empty error}">
        <p>Error: ${error}</p>
    </c:if>

    <c:if test="${empty population and empty error}">
        <p>Please select a state to view its information.</p>
    </c:if>
</div>
<a href="index.jsp">Select another state</a>
</body>
</html>


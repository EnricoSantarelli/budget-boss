<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BudgetBoss</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="shortcut icon" href="./assets/budget_boss_logo.svg" />
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./styles/styles.css">
    <script src="https://cdn.tailwi	ndcss.com"></script>
</head>
<body>
	<c:import url="./header.jsp"/>
	 <main class="w-full max-w-screen-2xl flex flex-col items-center py-9">
            <h1 class="gray font-normal text-2xl sm:text-3xl md:text-4xl">Olá, <strong class="black font-bold text-3xl sm:text-4xl md:text-5xl">Enrico</strong></h1>
            <div class="elevation rounded-2xl flex flex-col align-start p-4 my-10 w-2/3 max-w-xl">
                <span class="gray font-normal text-xs sm:text-sm md:text-base py-4"><a href="#" class="hover:drop-shadow-xl">Contas parentais</a></span>
                <div class="bg-gray w-full h-0.5"></div>
                <span class="gray font-normal text-xs sm:text-sm md:text-base py-4"><a href="#" class="hover:drop-shadow-xl">Configurações</a></span>
                <div class="bg-gray w-full h-0.5"></div>
                <span class="gray font-normal text-xs sm:text-sm md:text-base py-4"><a href="#" class="hover:drop-shadow-xl">Avalie o BudgetBoss</a></span>
                <div class="bg-gray w-full h-0.5"></div>
                <span class="gray font-normal text-xs sm:text-sm md:text-base py-4"><a href="#" class="hover:drop-shadow-xl">Termos de uso</a></span>
                <div class="bg-gray w-full h-0.5"></div>
                <span class="gray font-normal text-xs sm:text-sm md:text-base py-4"><a href="login" class="hover:drop-shadow-xl">Sair</a></span>
                <div class="bg-gray w-full h-0.5"></div>
            </div>
        </main>
        <c:import url="./footer.jsp"/>
</body>
</html>
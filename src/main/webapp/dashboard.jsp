<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BudgetBoss</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="shortcut icon" href="./assets/budget_boss_logo.svg" />
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./styles/styles.css">
    <script src="https://cdn.tailwi	ndcss.com"></script>
</head>
<body class="flex flex-col items-center">
        <c:import url="./header.jsp"/>
        <main class="w-full max-w-screen-2xl flex flex-col items-center py-9">
            <span class="gray font-normal text-xs sm:text-sm md:text-base">Saldo atual</span>
            <div class="black font-bold text-3xl sm:text-4xl md:text-5xl show">
            		R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${usuario.getConta().getSaldo()}" />
            </div>         	
           	<div  class="text-center black font-bold text-3xl sm:text-4xl md:text-5xl hide">
           		--
           	</div>
           	<div id="toggle">
           		<i class="cursor-pointer ph ph-eye gray text-xs sm:text-sm md:text-base hover:drop-shadow-xl show"></i>
            	<i class="ph ph-eye-closed cursor-pointer ph ph-eye gray text-xs sm:text-sm md:text-base hover:drop-shadow-xl hide"></i>
           	</div>  
            <div class="flex flex-row gap-6 sm:gap-24 md:gap-48">
                <div class="elevation rounded-2xl flex flex-col align-start p-4">
                    <span class="gray font-normal text-xs sm:text-sm md:text-base">Receitas</span>
                    <div class="black font-bold text-xl sm:text-2xl md:text-3xl light-green show">
		            		R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${usuario.getConta().getReceita()}" />
		            </div>         	
		           	<div  class="text-center black font-bold text-3xl sm:text-4xl md:text-5xl hide">
		           		--
		           	</div>
                </div>
                <div class="elevation rounded-2xl flex flex-col align-start p-4">
                    <span class="gray font-normal text-xs sm:text-sm md:text-base">Despesas</span>
                    <div class="black font-bold text-xl sm:text-2xl md:text-3xl light-green show">
		            		R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${usuario.getConta().getDespesa()}" />
		            </div>         	
		           	<div  class="text-center black font-bold text-3xl sm:text-4xl md:text-5xl hide">
		           		--
		           	</div>
                </div>
            </div>
            <div class="elevation rounded-2xl flex flex-col align-start p-4 my-10 w-2/3 max-w-xl">
            <div class="flex justify-between">
                <span class="gray font-normal text-xs sm:text-sm md:text-base">Contas parentais</span>
                <p class="gray font-normal text-xs sm:text-sm md:text-base cursor-pointer" id="adicionar">+ Adicionar conta</p>
            </div>
                <c:if test="${empty usuariosFilhos}">
                	<div class="gray font-normal text-xs sm:text-sm md:text-base text-center p-6">Não há contas parentais cadastradas</div>
                </c:if>
                <c:if test="${not empty usuariosFilhos}">
                	<c:forEach items="${usuariosFilhos}" var="u">
		                	<h2 class="black font-bold text-xl sm:text-2xl md:text-3xl pt-4 pb-2">${u.getNome()}</h2>
		                <div class="flex flex-row align-center justify-between pb-1">
		                    <span class="gray font-normal text-xs sm:text-sm md:text-base">Saldo atual</span>
		                    <div class="black font-bold text-xs sm:text-sm md:text-base show">
		            		R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${u.getConta().getSaldo()}" />
				            </div>         	
				           	<div  class="black font-bold text-xs sm:text-sm md:text-base hide">
				           		--
				           	</div>
		                </div>
		                <div class="flex flex-row align-center justify-between pb-1">
		                    <span class="gray font-normal text-xs sm:text-sm md:text-base">Receitas</span>
		                    <div class="green font-bold text-xs sm:text-sm md:text-base show">
		            		R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${u.getConta().getReceita()}" />
				            </div>         	
				           	<div  class="black font-bold text-xs sm:text-sm md:text-base hide">
				           		--
				           	</div>
		                </div>
		                <div class="flex flex-row align-center justify-between">
		                    <span class="gray font-normal text-xs sm:text-sm md:text-base">Despesas</span>
		                    <div class="red font-bold text-xs sm:text-sm md:text-base pb-4 show">
		            		R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${u.getConta().getDespesa()}" />
				            </div>         	
				           	<div  class="black font-bold text-xs sm:text-sm md:text-base pb-4 hide">
				           		--
				           	</div>
		                </div>
		               </c:forEach>
                </c:if>
                </div>
            <div class="absolute bg-white elevation flex-col rounded-2xl p-4 my-10 w-2/3 max-w-xl" id="container">
            	<i class="ph ph-x self-end cursor-pointer gray font-normal text-xs sm:text-sm md:text-base pb-1" id="xis"></i>
            	<form action="dashboard" class="flex flex-col items-center" method="post">
            		<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="E-mail" type="email" name="email"/>
                	<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Senha" type="password" name="senha"/>
            		<input class="cursor-pointer green font-bold text-center text-sm md:text-base flex items-center justify-center bg-white w-full h-10 rounded-2xl max-w-xl mt-16 mb-2 mx-auto elevation hover:drop-shadow-xl button" type="submit" value="Adicionar"/>
            		<span class=" red text-sm md:text-base">
            			${erro}
            		</span>
            	</form>
            </div>
            
        </main>
            <c:import url="./footer.jsp"/>
        
    <script src="./script/dashboard.js"></script>
</body>
</html>
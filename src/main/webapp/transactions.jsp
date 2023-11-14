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
<body class="flex flex-col items-center">
        <c:import url="./header.jsp"/>
        <main class="w-full max-w-screen-2xl flex flex-col items-center py-9">
            <div class="flex flex-row gap-6 sm:gap-24 md:gap-48">
                <div class="flex flex-col items-center gap-2">
                    <div class="elevation rounded-2xl flex flex-col p-4">
                        <span class="gray font-normal text-xs sm:text-sm md:text-base">Receitas</span>
                         <div class="green font-bold text-xl sm:text-2xl md:text-3xl">
            				R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${usuario.getConta().getReceita()}" />
            			</div>
                    </div>
                    <p id="abrirReceita" class="hover:drop-shadow-xl cursor-pointer"><span class="gray font-normal text-xs sm:text-sm md:text-base">+ Adicionar receita</span></p>
                </div>
                <div class="flex flex-col items-center gap-2">
                    <div class="elevation rounded-2xl flex flex-col p-4">
                        <span class="gray font-normal text-xs sm:text-sm md:text-base">Despesas</span>
                        <div class="red font-bold text-xl sm:text-2xl md:text-3xl">
            				R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${usuario.getConta().getDespesa()}" />
            			</div>
                    </div>
                    <p id="abrirDespesa" class="hover:drop-shadow-xl cursor-pointer"><span class="gray font-normal text-xs sm:text-sm md:text-base">+ Adicionar despesa</span></p>
                </div>
            </div>
            <div class="elevation rounded-2xl flex flex-col align-start p-4 my-10 md:w-1/2 max-w-xl">
            <div class="flex items-center justify-between">
            	<span class="gray font-normal text-xs sm:text-sm md:text-base">Visão geral</span>
            	<a class="ml-auto cursor-pointer" href="delete"><i class="ph ph-trash"></i></a>
            </div>        
                    <div class="flex flex-row">
                        <div class="w-1/2">
                            <h2 class="black font-bold text-xl sm:text-2xl md:text-3xl pt-4 pb-2">Receitas</h2>
                            <c:if test="${empty receitas}">
                	<div class="gray font-normal text-xs sm:text-sm md:text-base text-center p-6">Não há receitas cadastradas</div>
               		 </c:if>
                	<c:if test="${not empty receitas}">
                            <c:forEach items="${receitas}" var="r">
                            
                            	
                            	<div class="flex flex-row items-center justify-between pb-1">
                            		<div class="flex flex-col align-center">
                            		<span class="gray font-normal text-xs sm:text-sm md:text-base pr-1">${r.getDate()}</span>
	                                <span class="gray font-normal text-xs sm:text-sm md:text-base pr-1">${r.getDescricao()}</span>
                            		</div>
	                            	<div class="green font-bold text-xs sm:text-sm md:text-base flex justify-center items-center">
		            						R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${r.getValor() }" />
		            				</div>
                            	</div>
                        	</c:forEach>
                        	</c:if>
                            </div>
                        <div class="bg-gray h-100 w-0.5 mx-4"></div>
                        <div class="w-1/2">
                            <h2 class="black font-bold text-xl sm:text-2xl md:text-3xl pt-4 pb-2">Despesas</h2>
                            <c:if test="${empty despesas}">
                	<div class="gray font-normal text-xs sm:text-sm md:text-base text-center p-6">Não há despesas cadastradas</div>
               		 </c:if>
                	<c:if test="${not empty despesas}">
                            <c:forEach items="${despesas}" var="d">
                        		<div class="flex flex-row items-center justify-between pb-1">
                            		<div class="flex flex-col align-center">
                            		<span class="gray font-normal text-xs sm:text-sm md:text-base pr-1">${d.getDate()}</span>
	                                <span class="gray font-normal text-xs sm:text-sm md:text-base pr-1">${d.getDescricao()}</span>
                            		</div>
	                            	<div class="red font-bold text-xs sm:text-sm md:text-base flex justify-center items-center">
		            						R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${d.getValor() }" />
		            				</div>
                            	</div>
                        	</c:forEach>
                        	</c:if>
                        </div>
                    </div>
                </div>
                <div class="absolute bg-white elevation flex-col rounded-2xl p-4 my-10 w-2/3 max-w-xl" id="containerDespesa">
            	<i class="ph ph-x self-end cursor-pointer gray font-normal text-xs sm:text-sm md:text-base pb-1" id="xisDespesa"></i>
            	<h2 class="black font-bold text-xl sm:text-2xl md:text-3xl pt-4 pb-16 self-center">Despesa</h2>
            	<form action="despesa" class="flex flex-col items-center" method="post">
            		<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Valor" type="number" step="0.01" name="valor" max="10000000000"/>
                	<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Descrição" type="text" name="descricao" maxlength="20"/>
                	<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Data" type="date" name="data"/>
            		<input class="cursor-pointer green font-bold text-center text-sm md:text-base flex items-center justify-center bg-white w-full h-10 rounded-2xl max-w-xl mt-16 mb-2 mx-auto elevation hover:drop-shadow-xl button" type="submit" value="Adicionar"/>
            	</form>
            	</div>
            	<div class="absolute bg-white elevation flex-col rounded-2xl p-4 my-10 w-2/3 max-w-xl" id="containerReceita">
            	<i class="ph ph-x self-end cursor-pointer gray font-normal text-xs sm:text-sm md:text-base pb-1" id="xisReceita"></i>
            	<h2 class="black font-bold text-xl sm:text-2xl md:text-3xl pt-4 pb-16 self-center">Receita</h2>
            	<form action="receita" class="flex flex-col items-center" method="post">
            		<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Valor" type="number" step="0.01" name="valor" max="10000000000"/>
                	<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Descrição" type="text" name="descricao" maxlength="20"/>
                	<input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Data" type="date" name="data"/>
            		<input class="cursor-pointer green font-bold text-center text-sm md:text-base flex items-center justify-center bg-white w-full h-10 rounded-2xl max-w-xl mt-16 mb-2 mx-auto elevation hover:drop-shadow-xl button" type="submit" value="Adicionar"/>
            	</form>
            	</div>
                  
        </main>
        <c:import url="./footer.jsp"/>
        
    <script src="./script/transactions.js"></script>
</body>
</html>
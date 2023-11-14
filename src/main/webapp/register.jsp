<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class=" h-full min-h-screen flex flex-col">
		<c:import url="./header.jsp"/>
        <main class="bg-gradient max-w-screen-2xl py-8 w-full h-full grow flex flex-col justify-center">
        	<c:import url="./nav-intro.jsp"/>
                <form class="flex flex-col items-start justify-center my-8 w-2/3 max-w-xl self-center" action="register" method="post">
                <input class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Seu nome" type="text" name="nome"  />
                <input id="email" class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Seu e-mail" type="email" name="email" />
                <input id="emailConfirm" class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Confirme seu e-mail" />
                <input id="senha" class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base hover:drop-shadow-xl" required placeholder="Sua senha" type="password" name="senha" />
                <input id="senhaConfirm" class="flex items-center justify-center bg-white w-full h-10 rounded-2xl px-4 my-2 elevation text-sm md:text-base" required placeholder="Confirme sua senha" type="password" />
            	<input id="submit" class="cursor-pointer green font-bold text-center text-sm md:text-base flex items-center justify-center bg-white w-full h-10 rounded-2xl max-w-xl mt-16 mb-2 mx-auto elevation hover:drop-shadow-xl button" type="submit" value="Cadastrar"/>
            	<span class="self-center white text-center text-sm md:text-base">
            		${mensagem}
            	</span>
            	<span class="self-center text-center red text-sm md:text-base">
            		${erro}
            	</span>
            </form>
        </main>
    <script src="./script/register.js"></script>
</body>
</html>
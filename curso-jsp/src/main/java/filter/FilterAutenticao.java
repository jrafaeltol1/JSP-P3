package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})/*Interceptas todas as requisi�oes que vierem do projeto ou mapeamento*/
public class FilterAutenticao implements Filter {

    public FilterAutenticao() {
    }

    /*Encerra os processo quando o servidor � parado*/
    /*Mataria os processo de conex�o com banco*/
	public void destroy() {
	}

	/*Intercepta as requisicoes e a as respostas no sistema*/
	/*Tudo que fizer no sistema vai fazer por aqui*/
	/*Valida��o de autenticao*/
	/*Dar commit e rolback de transa�oes do banco*/
	/*Validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	     
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath();/*Url que est� sendo acessada*/
		
		/*Validar se est� logado sen�o redireciona para a tela de login*/
		if (usuarioLogado == null  && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/*N�o est� logado*/
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);/**/
			return; /*Para a execu��o e redireciona para o login*/
			
		}else {
			chain.doFilter(request, response);
		}
		
	
	}

	/*Inicia os processo ou recursos quando o servidor sobre o projeto*/
	// inicar a conex�o com o banco
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
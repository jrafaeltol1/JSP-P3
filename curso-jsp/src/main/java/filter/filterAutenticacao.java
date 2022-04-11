package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/principal/*")/*Intercepta todas as requisi��es que vierem do projeto ou mapeamento*/
public class filterAutenticacao implements Filter {

    public filterAutenticacao() {
    }
    
    /*Encerra os processos quando servidor � parado*/
	public void destroy() {
	}

	/*Intercepta as requisi��es e as respostas no sistema*/
	/*TUdo que fizermos no sistema passar� por aqui*/
	/*Dar commit e rolback de transa��es do banco*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String usuarioLogin = (String) session.getAttribute("usuario");
		
		chain.doFilter(request, response);
	}

	
	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

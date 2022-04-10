package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


/*O chamando Controller são as servlets ou ServletLoginController*/
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"}) /*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

  
    public ServletLogin() {
    }


    /*Recebe os dados pela url em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD

=======
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	
	/*Recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		
		System.out.println("Capturado da tela login "+login);
		System.out.println("Capturado da tela senha "+senha);
		
		
		
		
		
		if (login!= null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
		
			System.out.print("Entrou no if");
		ModelLogin usuario = new ModelLogin();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		if(usuario.getLogin().equalsIgnoreCase("admin") && usuario.getLogin().equalsIgnoreCase("admin")) {
			
			request.getSession().setAttribute("usuario", usuario.getLogin());//servira para autenticar uma seção de segurança
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
			redirecionar.forward(request, response);
			
		}else {
			RequestDispatcher redirecionar= request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente");
			redirecionar.forward(request, response);
			
		}
		
		}else {
			System.out.println("Não entrou no if");
			RequestDispatcher redirecionar= request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente");
			redirecionar.forward(request, response);
		}
		
>>>>>>> branch 'master' of https://github.com/jrafaeltol1/ServletsComJSP.git
	}

	
	/*recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (modelLogin.getLogin().equalsIgnoreCase("admin")
					&& modelLogin.getSenha().equalsIgnoreCase("admin")) { /*Simulando login*/
				
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if (url == null || url.equals("null")) {
					url = "principal/principal.jsp";
				}
				
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
				
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
			
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
		
	}}
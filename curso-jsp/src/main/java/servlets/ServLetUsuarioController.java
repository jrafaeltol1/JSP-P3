package servlets;

import java.io.IOException;
import java.sql.SQLException;

import dao.DAOUsuarioRepository;
import dao.DAOloginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/ServLetUsuarioController")
public class ServLetUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private DAOUsuarioRepository daoUsuarioRepository= new DAOUsuarioRepository();
	
       
    public ServLetUsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String msg = "Operação Realizada com sucesso!";
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null && !id.isEmpty()? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		
		
		try {
			
			if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe um usuario com o mesmo login, informe outro login";
			}else {
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
			
		request.setAttribute("msg", msg);
		request.setAttribute("modelLogin", modelLogin);
		RequestDispatcher redireciona = request.getRequestDispatcher("principal/usuario.jsp");
		redireciona.forward(request, response);
	}

}

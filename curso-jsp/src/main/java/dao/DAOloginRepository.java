package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOloginRepository {

	private Connection connection;

	public DAOloginRepository() {
		connection = SingleConnectionBanco.getConnection();

	}

	public Boolean validaAutenticacao(ModelLogin modelLogin) throws SQLException {

		String sql = "select * from model_login where upper(login)= upper(?) and upper(senha) = upper(?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());

		ResultSet resulSet = statement.executeQuery();

		if (resulSet.next()) {
			return true; /* Autenticado */

		}
		return false; /* nao autenticado */
	}

}

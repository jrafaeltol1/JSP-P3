<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>

	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="pageheader.jsp"></jsp:include>
						<!-- Page-header end -->

						<div class="pcoded-inner-content">

							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cad. usu�rio</h4>
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServLetUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">
															<div class="form-group form-default">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Nome</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="email" id="email"
																	class="form-control" required="required"
																	value="${modelLogin.email}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Email</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	value="${modelLogin.login}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Login</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	value="${modelLogin.senha}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Password</label>
															</div>

															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="criarDeleteAjax();">Excluir</button>
																<button type="button" class="btn btn-primary" 
																data-toggle="modal" data-target="#exampleModalUsuario">Consultar</button>

														</form>
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>


									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
	
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usu�rio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      <div class="input-group mb-3">
  <input type="text" class="form-control" placeholder="Nome" aria-label="Nome" id="nomeBusca" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
  </div>
</div>

<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nome</th>
      <th scope="col">ver</th>
    </tr>
  </thead>
  <tbody>
   
  </tbody>
</table>
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>
	
	
	
	
	
	<script type="text/javascript">
	
	
	function buscarUsuario(){
		
		
		var nomeBusca = document.getElementById('nomeBusca').value;
		
		if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){ /*Validando que tem que ter valor pra buscar no banco de dados*/
			//alert(nomeBusca); /*Testar a passagem de parametro*/
			
			var urlAction = document.getElementById('formUser').action;
			
			$.ajax({
				   
				   method : "get",
				   url : urlAction,
				   data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
				   success : function (response){
					   
					   document.getElementById('msg').textContent = response;
				   }
				
				   
			   }).fail(function(xhr, status, errorThrown){
				   alert(' Erro ao consutar usuario por nome' +xhr.responseText);
			   });
			
		}
		
	}
	
   
   function criarDeleteAjax(){
	   
	   if (confirm('Deseja realmente excluir dados?')){
		   var urlAction = document.getElementById('formUser').action;
		   var idUser = document.getElementById('id').value;
		   
		   $.ajax({
			   
			   method : "get",
			   url : urlAction,
			   data : "Id=" + idUser + '&acao=deletarajax',
			   success : function (response){
				   
				   limparForm();
				   document.getElementById('msg').textContent = response;
			   }
			   
		   }).fail(function(xhr, status, errorThrown){
			   alert(' Erro ao deletar o usuario por id ' +xhr.responseText);
		   });
			   
		   }
		   
		   
		   
	   }
	   
   
	
	
	
	function criarDelete(){
	   
	   if (confirm('Deseja realmente excluir os dados ?')){
	   
	   document.getElementById("formUser").method = 'get';
	   document.getElementById("acao").value = 'deletar';
	   document.getElementById("formUser").submit();
       }
   }
   
   
   function limparForm(){
	   
	   var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form */
	   
	   for(p = 0 ; p < elementos.length ; p++){
		   elementos[p].value = '';
		   
	   }
	   
   }
   </script>
</body>

</html>

